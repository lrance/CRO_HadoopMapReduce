package hk.hku.eee.cro.yao23benchmark;

public class F09 extends Objective {

    public F09() {
        numVariable = 30;
        upperBound = 5.12;
        lowerBound = -5.12;
        FELimit = 250000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 += (Math.pow(sln[i], 2) - 10
                    * Math.cos(2 * Math.PI * sln[i]) + 10);
        }
        return result1;
    }
}
