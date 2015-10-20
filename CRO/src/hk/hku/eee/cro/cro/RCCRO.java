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
 * This class is a sample of how to use CRO in solving optimization problems.
 * Since the benchmark set is a collection of continuous optimization problems,
 * we employ Real-Coded CRO to solve these problems. RCCRO has the same
 * framework with CRO and you can make the codes solve combinatorial problems
 * with very little modifications.
 *
 * @author James
 */
public class RCCRO {

    /**
     * This is the total number of repeats expected to perform.
     */
    private static final int NUMREPEATCOUNT = 25;

    /**
     * This is the entry point of the code. You can see the flow of how to initialize
     * a problem and create a CRO container to solve it.
     * @param args Useless.
     */
    public static void main(String[] args){
        Util.r = new Random();
        // We have 23 functions to solve. Iteration.
        for (int problemIndex = 1; problemIndex <= 23; problemIndex++) {
            System.out.println("==============================");
            Problem problem = new Problem(problemIndex);
            double globals[] = new double[NUMREPEATCOUNT];
            double times[] = new double[NUMREPEATCOUNT];
            // For every function, we perform NUMREPEATCOUNT repeats.
            for (int i = 0; i < NUMREPEATCOUNT; i++) {
                long start = System.currentTimeMillis();
                Container container = new Container(problem);
                problem.initContainer(container);
                globals[i] = container.run();
                times[i] = (System.currentTimeMillis() - start) / 1000F;
                System.out.println(globals[i]);
            }
            System.gc();
        }
    }
}
