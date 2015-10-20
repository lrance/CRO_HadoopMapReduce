package hk.hku.eee.cro.yao23benchmark;

public class F16 extends Objective {

    public F16() {
        numVariable = 2;
        upperBound = 5;
        lowerBound = -5;
        FELimit = 1250;
        bias = 100;
    }

    @Override
    public double f(double[] sln) {
        return 4 * Math.pow(sln[0], 2) - 2.1 * Math.pow(sln[0], 4)
                + Math.pow(sln[0], 6) / 3 + sln[0] * sln[1] - 4
                * Math.pow(sln[1], 2) + 4 * Math.pow(sln[1], 4) + 100;
    }
}
