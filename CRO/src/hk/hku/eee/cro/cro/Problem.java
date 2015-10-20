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

import hk.hku.eee.cro.yao23benchmark.Benchmark;
import hk.hku.eee.cro.yao23benchmark.Objective;

/**
 * This class is a collection of information on the optimization problem. An
 * instance of this class contains the description of the objective function,
 * constraints, parameters and other related information.
 *
 * @author James
 */
public class Problem {

    /**
     * The dimension of the optimization problem.
     */
    private int numVariable;
    /**
     * The index of the optimization problem in the benchmark set.
     */
    private int problemIndex;
    /**
     * The maximum allowable function evaluations.
     */
    private int FELimit;
    /**
     * The upper bound, lower bound, and bias of the optimization problem.
     */
    private double upperBound, lowerBound, bias;
    /**
     * The object of a benchmark class.
     */
    private Benchmark benchmark = new Benchmark();
    /**
     * The objective function object of the optimization problem.
     */
    private Objective objectiveFunction;

    /**
     * This is the constructor of the class.
     *
     * @param numRepeatCount The expected number of repeats to be performed.
     * @param problemIndex The index of the optimization problem in the
     * benchmark set.
     * @param numDataPoint The expected number of data points to take during a
     * repeat of the simulation.
     */
    Problem(int problemIndex) {
        this.problemIndex = problemIndex;
        objectiveFunction = benchmark.ObjFcnFactory(problemIndex);
        FELimit = objectiveFunction.FELimit;
        numVariable = objectiveFunction.numVariable;
        upperBound = objectiveFunction.upperBound;
        lowerBound = objectiveFunction.lowerBound;
        bias = objectiveFunction.bias;
    }

    /**
     * This is the fitness function of this problem.
     *
     * @param solution The solution to be evaluated by the objective function.
     * @return The objective function value of the input solution.
     */
    double fitness(double solution[]) {
        return objectiveFunction.f(solution);
    }

    /**
     * This is a container initializer. This function is called to set the initial
     * parameter of the container.
     * 
     * @param container The container to be set with given parameters.
     */
    void initContainer(Container container) {
        if (problemIndex < 8) {
            container.setIniPopSize(10);
            container.setCollRate(0.2);
            container.setStepSize(0.2);
            container.setEnergyBuffer(0);
            container.setIniKE(1000);
            container.setLossRate(0.1);
            container.setDecThres(150000);
            container.setSynThres(10);
        } else if (problemIndex > 13) {
            container.setIniPopSize(100);
            container.setCollRate(0.2);
            container.setStepSize(0.5);
            container.setEnergyBuffer(0);
            container.setIniKE(1000);
            container.setLossRate(0.1);
            container.setDecThres(500);
            container.setSynThres(10);
        } else {
            container.setIniPopSize(20);
            container.setCollRate(0.2);
            container.setStepSize(1.0);
            container.setEnergyBuffer(100000);
            container.setIniKE(10000000);
            container.setLossRate(0.1);
            container.setDecThres(150000);
            container.setSynThres(10);
        }
        if (problemIndex == 8) {
            container.setStepSize(300);
        }
        if (problemIndex == 11) {
            container.setStepSize(15);
        }
        container.initPopulation();
    }

    int getFELimit() {
        return FELimit;
    }

    double getBias() {
        return bias;
    }

    double getLowerBound() {
        return lowerBound;
    }

    int getNumVariable() {
        return numVariable;
    }

    double getUpperBound() {
        return upperBound;
    }

    public int getProblemIndex() {
        return problemIndex;
    }        
}
