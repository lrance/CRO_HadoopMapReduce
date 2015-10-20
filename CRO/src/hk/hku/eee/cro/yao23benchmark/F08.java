package hk.hku.eee.cro.yao23benchmark;

public class F08 extends Objective {

    public F08() {
        numVariable = 30;
        upperBound = 500;
        lowerBound = -500;
        FELimit = 150000;
        bias = 100;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 -= sln[i] * Math.sin(Math.pow(Math.abs(sln[i]), 0.5));
        }
        return result1 + 100;
    }
}
