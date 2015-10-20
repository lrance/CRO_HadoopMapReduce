package hk.hku.eee.cro.yao23benchmark;

public class F03 extends Objective {

    public F03() {
        numVariable = 30;
        upperBound = 100;
        lowerBound = -100;
        FELimit = 250000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2;
        for (int i = 0; i < numVariable; i++) {
            result2 = 0;
            for (int j = 0; j < i; j++) {
                result2 = sln[j];
            }
            result1 += (result2 * result2);
        }
        return result1;
    }
}
