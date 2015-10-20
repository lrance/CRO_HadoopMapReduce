package hk.hku.eee.cro.yao23benchmark;

public class F18 extends Objective {

    public F18() {
        numVariable = 2;
        upperBound = 2;
        lowerBound = -2;
        FELimit = 10000;
    }

    @Override
    public double f(double[] sln) {
        return (1 + Math.pow((sln[0] + sln[1] + 1), 2)
                * (19 - 14 * sln[0] + 3 * Math.pow(sln[0], 2) - 14 * sln[1]
                + 6 * sln[0] * sln[1] + 3 * Math.pow(sln[1], 2)))
                * (30 + Math.pow(2 * sln[0] - 3 * sln[1], 2)
                * (18 - 32 * sln[0] + 12 * Math.pow(sln[0], 2) + 48
                * sln[1] - 36 * sln[0] * sln[1] + 27 * Math.pow(sln[1], 2)));
    }
}
