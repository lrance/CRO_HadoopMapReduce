package hk.hku.eee.cro.yao23benchmark;

public class F20 extends Objective {

    public static double a20[][] = {{10.0, 3.0, 17.0, 3.5, 1.7, 8.0},
        {0.05, 10.0, 17.0, 0.1, 8.0, 14.0},
        {3.0, 3.5, 1.7, 10.0, 17.0, 8.0},
        {17.0, 8.0, 0.05, 10.0, 0.1, 14.0}};
    public static double c20[] = {1.0, 1.2, 3.0, 3.2};
    public static double p20[][] = {
        {0.1312, 0.1696, 0.5569, 0.0124, 0.8283, 0.5886},
        {0.2329, 0.4135, 0.8307, 0.3736, 0.1004, 0.9991},
        {0.2348, 0.1451, 0.3522, 0.2883, 0.3047, 0.6650},
        {0.4047, 0.8828, 0.8732, 0.5743, 0.1091, 0.0381}};

    public F20() {
        numVariable = 6;
        upperBound = 1;
        lowerBound = 0;
        FELimit = 7500;
        bias = 100;
    }

    // Function body
    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2;
        for (int i = 0; i < 4; i++) {
            result2 = 0;
            for (int j = 0; j < numVariable; j++) {
                result2 += a20[i][j]
                        * Math.pow((sln[j] - p20[i][j]), 2);
            }
            result1 += c20[i] * Math.pow(Math.E, -result2);
        }
        return -result1 + 100;
    }
}
