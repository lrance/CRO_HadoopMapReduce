package hk.hku.eee.cro.yao23benchmark;

public class F23 extends Objective {

    public static double a21[][] = {{4, 1, 8, 6, 3, 2, 5, 8, 6, 7},
        {4, 1, 8, 6, 7, 9, 5, 1, 2, 3.6},
        {4, 1, 8, 6, 3, 2, 3, 8, 6, 7},
        {4, 1, 8, 6, 7, 9, 3, 1, 2, 3.6}};
    public static double c21[] = {0.1, 0.2, 0.2, 0.4, 0.4, 0.6, 0.3, 0.7, 0.5,
        0.5};

    public F23() {
        numVariable = 4;
        upperBound = 10;
        lowerBound = 0;
        FELimit = 10000;
        bias = 100;
    }

    // Function body
    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2;
        for (int i = 0; i < 10; i++) {
            result2 = 0.0;
            for (int j = 0; j < numVariable; j++) {
                result2 += Math.pow((sln[j] - a21[j][i]), 2);
            }
            result1 += 1.0 / (result2 + c21[i]);
        }
        return -result1 + 100;
    }
}
