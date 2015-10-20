package hk.hku.eee.cro.yao23benchmark;

public class F17 extends Objective {

    public F17() {
        numVariable = 2;
        upperBound = 15;
        lowerBound = -5;
        FELimit = 5000;
    }

    // Function body
    @Override
    public double f(double[] sln) {
        return Math.pow((sln[1] - 5.1 * Math.pow(sln[0], 2)
                / (4 * Math.PI * Math.PI) + 5 * sln[0] / Math.PI - 6), 2)
                + (1 - 1 / (8 * Math.PI)) * 10 * Math.cos(sln[0]) + 10;
    }
}
