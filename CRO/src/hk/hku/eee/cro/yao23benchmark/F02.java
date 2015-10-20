package hk.hku.eee.cro.yao23benchmark;

public class F02 extends Objective {

    public F02() {
        numVariable = 30;
        upperBound = 10;
        lowerBound = -10;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 += Math.abs(sln[i]);
            result2 *= Math.abs(sln[i]);
        }
        return result1 + result2;
    }
}
