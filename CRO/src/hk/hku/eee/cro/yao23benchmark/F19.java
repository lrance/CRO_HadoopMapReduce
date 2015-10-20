package hk.hku.eee.cro.yao23benchmark;

public class F19 extends Objective {

    public static double a19[][] = {{3, 10, 30}, {0.1, 10, 35},
        {3, 10, 30}, {0.1, 10, 35}};
    public static double c19[] = {1, 1.2, 3, 3.2};
    public static double p19[][] = {{0.6890, 0.1170, 0.2673},
        {0.4699, 0.4387, 0.7470}, {0.1091, 0.8732, 0.5547},
        {0.03815, 0.5743, 0.8828}};

    public F19() {
        numVariable = 3;
        upperBound = 1;
        lowerBound = 0;
        FELimit = 4000;
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
                result2 += a19[i][j]
                        * Math.pow((sln[j] - p19[i][j]), 2);
            }
            result1 += c19[i] * Math.pow(Math.E, -result2);
        }
        return -result1 + 100;
    }
}
