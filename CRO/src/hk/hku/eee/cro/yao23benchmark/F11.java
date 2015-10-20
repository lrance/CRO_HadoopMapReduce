package hk.hku.eee.cro.yao23benchmark;

public class F11 extends Objective {

    public F11() {
        numVariable = 30;
        upperBound = 600;
        lowerBound = -600;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 += Math.pow(sln[i], 2);
            result2 = result2
                    * Math.cos(sln[i] / Math.pow((double) (i + 1), 0.5));
        }
        return result1 / 4000 - result2 + 1;
    }
}
