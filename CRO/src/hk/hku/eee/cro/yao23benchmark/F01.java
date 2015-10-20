package hk.hku.eee.cro.yao23benchmark;

public class F01 extends Objective {

    public F01() {
        numVariable = 30;
        upperBound = 100;
        lowerBound = -100;
        FELimit = 150000;
    }

    // Function body
    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 += (sln[i] * sln[i]);
        }
        return result1;
    }
}
