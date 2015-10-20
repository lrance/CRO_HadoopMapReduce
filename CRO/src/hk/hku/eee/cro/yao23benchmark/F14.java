package hk.hku.eee.cro.yao23benchmark;

public class F14 extends Objective {

    public double a14[][] = {
        {-32, -16, 0, 16, 32, -32, -16, 0, 16, 32, -32, -16, 0, 16, 32,
            -32, -16, 0, 16, 32, -32, -16, 0, 16, 32},
        {-32, -32, -32, -32, -32, -16, -16, -16, -16, -16, 0, 0, 0, 0, 0,
            16, 16, 16, 16, 16, 32, 32, 32, 32, 32}};

    public F14() {
        numVariable = 2;
        upperBound = 65.536;
        lowerBound = -65.536;
        FELimit = 7500;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2;
        for (int j = 0; j < 25; j++) {
            result2 = 0.0;
            for (int i = 0; i < numVariable; i++) {
                result2 += Math.pow((sln[i] - a14[i][j]), 6);
            }
            result1 += 1.0 / (result2 + j + 1);
        }
        return 1 / (1.0 / 500 + result1);
    }
}
