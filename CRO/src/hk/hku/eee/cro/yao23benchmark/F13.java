package hk.hku.eee.cro.yao23benchmark;

public class F13 extends Objective {

    public F13() {
        numVariable = 30;
        upperBound = 50;
        lowerBound = -50;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2 = 0.0;
        for (int i = 0; i < numVariable - 1; i++) {
            result1 += Math.pow(sln[i] - 1, 2)
                    * (1 + Math.pow(Math.sin(3 * Math.PI * sln[i + 1]), 2));
        }
        for (int i = 0; i < numVariable; i++) {
            if (sln[i] > 5) {
                result2 += 100 * Math.pow((sln[i] - 5), 4);
            } else if (sln[i] < -5) {
                result2 += 100 * Math.pow((-sln[i] - 5), 4);
            }
        }
        return 0.1
                * (Math.pow(Math.sin(3 * Math.PI * sln[0]), 2) + result1 + Math.pow(sln[numVariable - 1], 2)
                * (1 + Math.pow(
                Math.sin(2 * Math.PI * sln[numVariable - 1]), 2)))
                + result2;
    }
}
