package hk.hku.eee.cro.yao23benchmark;

public class F04 extends Objective {

    public F04() {
        numVariable = 30;
        upperBound = 100;
        lowerBound = -100;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            if (Math.abs(sln[i]) > result1) {
                result1 = Math.abs(sln[i]);
            }
        }
        return result1;
    }
}
