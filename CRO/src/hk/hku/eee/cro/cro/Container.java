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

import java.util.ArrayList;

/**
 * This class describes the container in RCCRO. An instance of this class is a
 * container which is filled with molecules and performs chemical reaction.
 *
 * @author James
 */
public class Container {

    /**
     * The problem that this container is evaluating.
     */
    private Problem problem;
    /**
     * The current function evaluation number of this container.
     */
    private int FE;
    /**
     * The initial population size in the container.
     */
    private int iniPopSize;
    /**
     * The current global minimum of this container. This variable is updated
     * during the course of searching.
     */
    private double globalMin;
    /**
     * The energy buffer of this container. This variable is set with a value
     * and is updated during the course of searching.
     */
    private double energyBuffer;
    /**
     * The initial kinetic energy assigned to new molecules.
     */
    private double iniKE;
    /**
     * The collision rate between molecules in the container.
     */
    private double collRate;
    /**
     * The energy loss rate in an on-wall ineffective collision.
     */
    private double lossRate;
    /**
     * The threshold for decomposition to happen.
     */
    private int decThres;
    /**
     * The threshold for synthesis to happen.
     */
    private double synThres;
    /**
     * The perturbation strength, or "Step Size" of the neighborhood function.
     */
    private double stepSize;
    /**
     * The data point values of this container.
     */
    ArrayList<Molecule> population = new ArrayList();

    /**
     * This function is the class constructor.
     *
     * @param problem The problem that this container is going to handle.
     */
    Container(Problem problem) {
        this.problem = problem;
        globalMin = 1E100;
    }

    /**
     * This function initialize the population of the container. It shall be
     * called only after the problem object has initialized the parameter of the
     * container and shall be called only once.
     */
    public void initPopulation() {
        FE = iniPopSize;
        for (int i = 0; i < iniPopSize; i++) {
            population.add(new Molecule(problem, this));
            Molecule m = (Molecule) population.get(i);
            update(m);
        }
        for (int i = 0; i < iniPopSize; i++) {
            Molecule m = (Molecule) population.get(i);
            m.setKE(iniKE);
        }
    }

    /**
     * This function implements the update feature of RCCRO. Whenever a new
     * molecular structure is generated, this function is called to check
     * whether there is a better molecule generated, compared with the
     * best-so-far molecule.
     *
     * @param newMolecule The newly generated molecule.
     */
    private void update(Molecule newMolecule) {
        if (newMolecule.getPE() < globalMin) {
            // Update the global record.
            globalMin = newMolecule.getPE();
        }
    }

    /**
     * This function implements the structure of the iteration phase of RCCRO.
     *
     * @return The final biased global minimum.
     */
    public double run() {
        // Iteration starts.
        while (FE < problem.getFELimit()) {
            if ((Util.r.nextDouble() > collRate) || (population.size() == 1)) {
                // Decomposition.
                int pos = Util.r.nextInt(population.size());
                Molecule p = (Molecule) population.get(pos);
                if (p.decCheck()) {
                    Molecule q = new Molecule(problem, this);
                    if (p.decomposition(q)) {
                        update(p);
                        update(q);
                        population.add(q);
                    }
                    FE += 2;
                } // On-wall.
                else {
                    energyBuffer += p.onWallIneffective();
                    update(p);
                    FE++;
                }
            } else {
                // Synthesis.
                int pos1 = Util.r.nextInt(population.size());
                int pos2 = pos1;
                while (pos2 == pos1) {
                    pos2 = Util.r.nextInt(population.size());
                }
                Molecule p = (Molecule) population.get(pos1);
                Molecule q = (Molecule) population.get(pos2);
                if (p.synCheck() && q.synCheck()) {
                    if (p.synthesis(q)) {
                        update(q);
                        population.remove(pos2);
                    }
                    FE += 2;
                    // Inter-Molecular.
                } else {
                    p.interMolecular(q);
                    update(p);
                    update(q);
                    FE++;
                }
            }
        }
        return globalMin - problem.getBias();
    }

    /**
     * This function is the neighbourhood function in RCCRO. It modifies a
     * molecular structure to perform stochastic search.
     *
     * @param oldSolution The input solution of the neighbourhood function.
     */
    void neighbor(double oldSolution[]) {
        int pos = Util.r.nextInt(problem.getNumVariable());
        double temp = oldSolution[pos] + Util.r.nextGaussian() * stepSize;
        boolean loop = true;
        if ((problem.getProblemIndex() != 7) && (problem.getProblemIndex() != 25)) {
            while (loop) {
                if (temp < problem.getLowerBound()) {
                    temp = 2 * problem.getLowerBound() - temp;
                } else if (temp > problem.getUpperBound()) {
                    temp = 2 * problem.getUpperBound() - temp;
                } else {
                    loop = false;
                }
            }
        }
        oldSolution[pos] = temp;
    }

    public void setCollRate(double collRate) {
        this.collRate = collRate;
    }

    public void setDecThres(int decThres) {
        this.decThres = decThres;
    }

    public void setEnergyBuffer(double energyBuffer) {
        this.energyBuffer = energyBuffer;
    }

    public void setIniKE(double iniKE) {
        this.iniKE = iniKE;
    }

    public void setIniPopSize(int iniPopSize) {
        this.iniPopSize = iniPopSize;
    }

    public void setLossRate(double lossRate) {
        this.lossRate = lossRate;
    }

    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }

    public void setSynThres(double synThres) {
        this.synThres = synThres;
    }

    public int getDecThres() {
        return decThres;
    }

    public double getEnergyBuffer() {
        return energyBuffer;
    }

    public double getSynThres() {
        return synThres;
    }

    public double getLossRate() {
        return lossRate;
    }

}
