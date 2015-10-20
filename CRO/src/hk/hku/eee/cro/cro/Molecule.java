/*
 *  
 *  "Chemical Reaction Optimization" 
 *  
 *  Copyright 2012 The University of Hong Kong ("HKU"). All Rights Reserved. 
 *  
 * 
 *  This file is licensed to you ("You") for academic and non-commercial 
 *  internal research purpose only subject to the terms and conditions of the 
 *  license agreement downloadable from the HKU website (the "License"); You may 
 *  not use this file except in compliance with the License. You may obtain a 
 *  copy of the License at
 *  
 *  	http://cro.eee.hku.hk/license.txt
 *   
 *   
 *  This file is provided on an 'as is' and 'as available" basis without warranty of
 *  any kind, either express or implied, including, but not limited to, the implied
 *  warranties of fitness for a particular purpose, merchantability,
 *  non-infringement or security. 
 *      
 *   
 *  In no event, including (without limitation) negligence, shall HKU, its
 *  subsidiaries, affiliates, agents, officers, directors, employees, partners, or
 *  suppliers be liable to You or any third party for any direct, indirect,
 *  incidental, special, exemplary, or consequential damages (including, but not
 *  limited to, procurement of substitute goods or services; loss of use, data, or
 *  profits; or business interruption) however caused and on any theory of
 *  liability, whether in contract, strict liability, or tort (including negligence
 *  or otherwise) arising in any way out of the use of this file, even if advised of
 *  the possibility of such damage.
 *      
 *   
 *  See the License for the specific language governing permissions and limitations
 *  under the License.
 *  
 *  
 */ 


package hk.hku.eee.cro.cro;

/**
 * This class describes the molecule in RCCRO. An instance of this class represent
 * a molecule in the container. The elementary reactions that a molecule can be
 * involved is also implemented here.
 * @author James
 */
public class Molecule {

    /**
     * The total number of hits.
     */
    private int numHit;
    /**
     * The index of the last local optimum hit.
     */
    private int minHit;
    /**
     * The molecular structure this molecule holds.
     */
    private double molS[];
    /**
     * Two temporary structures.
     */
    private double t1[], t2[];
    /**
     * The potential and kinetic energy this molecule holds.
     */
    private double PE, KE;
    /**
     * The local minimum this molecule previously reached.
     */
    private double localMin;
    /**
     * The problem that this molecule is used to solve.
     */
    Problem problem;
    /**
     * The container that this molecule is in.
     */
    Container container;

    /**
     * This function is the class contructor.
     * @param problem The problem that this molecule is used to solve.
     * @param container The container that this molecule is in.
     */
    Molecule(Problem problem, Container container) {
        this.problem = problem;
        this.container = container;
        molS = new double[problem.getNumVariable()];
        for (int i = 0; i < problem.getNumVariable(); i++) {
            molS[i] = problem.getLowerBound() + (problem.getUpperBound()
                    - problem.getLowerBound()) * Util.r.nextDouble();
        }
        t1 = new double[problem.getNumVariable()];
        t2 = new double[problem.getNumVariable()];
        PE = problem.fitness(molS);
        numHit = 0;
        minHit = 0;
    }

    /**
     * This function is a local update in the molecule-wide range.
     */
    public void update() {
        if (PE < localMin) {
            localMin = PE;
            minHit = numHit;
        }
    }

    /**
     * This function checks the active level of this molecule.
     * @return True if this molecule is so inactive that it meets the threshold
     * of decomposition.
     */
    public boolean decCheck() {
        return (numHit - minHit) > container.getDecThres();
    }

    /**
     * This function checks the kinetic energy of this molecule.
     * @return True if this molecule has so less kinetic energy that it meets the
     * threshold of synthesis.
     */
    public boolean synCheck() {
        return KE < container.getSynThres();
    }

    /**
     * This function is the on-wall ineffective collision.
     * @return The amount of energy in central buffer after the elementary reaction.
     */
    public double onWallIneffective() {
        double result = 0;
        numHit++;
        // Perform neighborhood search.
        Util.copySln(molS, t1);
        container.neighbor(t1);
        double tempPE = problem.fitness(t1);
        // Energy Check.
        double tempBuff = PE + KE - tempPE;
        if (tempBuff >= 0) {
            PE = tempPE;
            KE = tempBuff * (Util.r.nextDouble() * (1.0 - container.getLossRate())
                    + container.getLossRate());
            Util.copySln(t1, molS);
            update();
            result += tempBuff - KE;
        }
        return result;
    }

    /**
     * This function is the decomposition collision.
     * @param newMolecule The new molecule generated to participate in the decomposition.
     * This molecule will not be put into the container if the decomposition fails.
     * @return True if the decomposition is successful.
     */
    public boolean decomposition(Molecule newMolecule) {
        numHit++;
        // Copy original molecular structure to temp structures.
        Util.copySln(molS, t1);
        Util.copySln(molS, t2);
        // Modify the temp structures.
        for (int i = 0; i < problem.getNumVariable() / 2; i++) {
            container.neighbor(t1);
            container.neighbor(t2);
        }
        // Evaluate the temp structures.
        double tempPE1 = problem.fitness(t1);
        double tempPE2 = problem.fitness(t2);
        // Energy check.
        double tempBuff = PE + KE - tempPE1 - tempPE2;
        if ((tempBuff >= 0) || (tempBuff + container.getEnergyBuffer() >= 0)) {
            if (tempBuff >= 0) {
                KE = tempBuff * Util.r.nextDouble();
                newMolecule.KE = tempBuff - KE;
            } else {
                container.setEnergyBuffer(container.getEnergyBuffer() + tempBuff);
                KE = container.getEnergyBuffer() * Util.r.nextDouble() * 
                        Util.r.nextDouble();
                container.setEnergyBuffer(container.getEnergyBuffer() - KE);
                newMolecule.KE = container.getEnergyBuffer() * Util.r.nextDouble()
                        * Util.r.nextDouble();
                container.setEnergyBuffer(container.getEnergyBuffer() - newMolecule.KE);
            }
            minHit = 0;
            numHit = 0;
            // Copy temp structures to the two generated real molecules.
            PE = tempPE1;
            Util.copySln(t1, molS);
            update();
            newMolecule.PE = tempPE2;
            Util.copySln(t2, newMolecule.molS);
            newMolecule.update();
            return true;
        }
        return false;
    }

    /**
     * This function is the inter-molecular ineffective collision.
     * @param otherMolecule The other molecule than this molecule that participates
     * in the collision.
     */
    public void interMolecular(Molecule otherMolecule) {
        numHit++;
        otherMolecule.numHit++;
        // Perform inter-molecular operator.
        Util.copySln(molS, t1);
        Util.copySln(otherMolecule.molS, t2);
        container.neighbor(t1);
        container.neighbor(t2);
        double tempPE1 = problem.fitness(t1);
        double tempPE2 = problem.fitness(t2);
        // Energy Check.
        double tempBuff = PE + KE + otherMolecule.PE + otherMolecule.KE - 
                tempPE1 - tempPE2;
        if (tempBuff >= 0) {
            PE = tempPE1;
            otherMolecule.PE = tempPE2;
            KE = tempBuff * Util.r.nextDouble();
            otherMolecule.KE = tempBuff - KE;
            Util.copySln(t1, molS);
            Util.copySln(t2, otherMolecule.molS);
            update();
            otherMolecule.update();
        }
    }

    /**
     * This function is the synthesis.
     * @param otherMolecule The other molecule than this molecule that participates
     * in the collision.
     * @return True if the synthesis is successful.
     */
    public boolean synthesis(Molecule otherMolecule) {
        numHit++;
        Util.copySln(molS, t1);
        Util.copySln(otherMolecule.molS, t2);
        // Perform synthesis.
        for (int i = 0; i < problem.getNumVariable(); i++) {
            if (Util.r.nextDouble() > 0.5) {
                t1[i] = t2[i];
            }
        }
        double tempPE = problem.fitness(t1);
        // Energy Check.
        double tempBuff = PE + KE + otherMolecule.PE + otherMolecule.KE - tempPE;
        if (tempBuff >= 0) {
            PE = tempPE;
            KE = tempBuff;
            Util.copySln(t1, molS);
            update();
            minHit = 0;
            numHit = 0;
            return true;
        }
        return false;
    }

    public double getPE() {
        return PE;
    }

    public void setKE(double KE) {
        this.KE = KE;
    }
    
}
