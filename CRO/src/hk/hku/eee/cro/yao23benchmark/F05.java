package hk.hku.eee.cro.yao23benchmark;

public class F05 extends Objective {

    public F05() {
        numVariable = 30;
        upperBound = 30;
        lowerBound = -30;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < (numVariable - 1); i++) {
            result1 += 100
                    * Math.pow((sln[i + 1] - Math.pow(sln[i], 2)), 2)
                    + Math.pow((sln[i] - 1), 2);
        }
        return result1;
    }
}
