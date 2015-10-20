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

import java.util.Random;

/**
 * This is the utility class of RCCRO. It contains some useful functions
 * involved in the simulation of RCCRO.
 *
 * @author James
 */
public class Util {

    /**
     * This variable is a randomizer.
     */
    static Random r;

    /**
     * This function calculates the mean of a vector.
     *
     * @param inputVector The input vector whose mean value is to be calculated.
     * @return The mean value of the input vector.
     */
    static double mean(double inputVector[]) {
        double sum = 0;
        for (int i = 0; i < inputVector.length; i++) {
            sum += inputVector[i];
        }
        return sum / inputVector.length;
    }

    /**
     * This function calculates the standard deviation of a vector.
     *
     * @param inputVector The input vector whose standard deviation is to be
     * calculated.
     * @return The standard deviation of the input vector.
     */
    static double std(double inputVector[]) {
        double avg = mean(inputVector);
        double std = 0;
        for (int i = 0; i < inputVector.length; i++) {
            std += (avg - inputVector[i]) * (avg - inputVector[i]);
        }
        return Math.sqrt(std / inputVector.length);
    }

    /**
     * This function gets the best, or smallest, value from a vector.
     *
     * @param inputVector The input vector whose best value is to be calculated.
     * @return The best value of the input vector.
     */
    static double best(double inputVector[]) {
        double best = inputVector[0];
        for (int i = 0; i < inputVector.length; i++) {
            if (inputVector[i] < best) {
                best = inputVector[i];
            }
        }
        return best;
    }

    /**
     * This function gets the worst, or largest, value from a vector.
     *
     * @param inputVector The input vector whose worse value is to be
     * calculated.
     * @return The worse value of the input vector.
     */
    static double worst(double inputVector[]) {
        double worst = inputVector[0];
        for (int i = 0; i < inputVector.length; i++) {
            if (inputVector[i] > worst) {
                worst = inputVector[i];
            }
        }
        return worst;
    }

    /**
     * This function copies a vector to another one. Please note that there is
     * no length checking mechanism in this function, so make sure that the
     * length and data type of both input vectors are identical.
     *
     * @param source The source vector.
     * @param destination The destination vector.
     */
    static void copySln(double source[], double destination[]) {
        System.arraycopy(source, 0, destination, 0, source.length);
    }
}
