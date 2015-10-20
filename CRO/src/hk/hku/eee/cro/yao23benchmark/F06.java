package hk.hku.eee.cro.yao23benchmark;

public class F06 extends Objective {

    public F06() {
        numVariable = 30;
        upperBound = 100;
        lowerBound = -100;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 += Math.pow(Math.floor(sln[i] + 0.5), 2);
        }
        return result1;
    }
}
