package hk.hku.eee.cro.yao23benchmark;

public class F12 extends Objective {

    public F12() {
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
            result1 += Math.pow((sln[i] + 1) / 4, 2)
                    * (1 + 10 * Math.pow(
                    Math.sin(Math.PI * (1 + (sln[i + 1] + 1) / 4)),
                    2));
        }
        for (int i = 0; i < numVariable; i++) {
            if (sln[i] > 10) {
                result2 += 100 * Math.pow((sln[i] - 10), 4);
            } else if (sln[i] < -10) {
                result2 += 100 * Math.pow((-sln[i] - 10), 4);
            }
        }
        double rr = sln[numVariable - 1] + 1;
        double result3 = Math.pow((rr) / 4, 2);
        return (Math.PI / numVariable) * (10 * Math.pow(Math.sin(Math.PI * (1 + (sln[0] + 1) / 4)), 2)
                + result1 + result3) + result2;
    }
}
