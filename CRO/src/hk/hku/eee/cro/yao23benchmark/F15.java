package hk.hku.eee.cro.yao23benchmark;

public class F15 extends Objective {

    public double a15[] = {0.1957, 0.1947, 0.1735, 0.16, 0.0844,
        0.0627, 0.0456, 0.0342, 0.0323, 0.0235, 0.0246};
    public double b15[] = {1 / 0.25, 1 / 0.5, 1 / 1, 1.0 / 2, 1.0 / 4,
        1.0 / 6, 1.0 / 8, 1.0 / 10, 1.0 / 12, 1.0 / 14, 1.0 / 16};

    public F15() {
        numVariable = 4;
        upperBound = 5;
        lowerBound = -5;
        FELimit = 250000;
    }

    // Function body
    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < 11; i++) {
            result1 += Math.pow(
                    (a15[i] - (sln[0] * (b15[i] * sln[1] + Math.pow(b15[i], 2)))
                    / (Math.pow(b15[i], 2) + b15[i]
                    * sln[2] + sln[3])), 2);
        }
        return result1;
    }
}
