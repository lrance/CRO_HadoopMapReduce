package hk.hku.eee.cro.yao23benchmark;

public class F10 extends Objective {

    public F10() {
        numVariable = 30;
        upperBound = 32;
        lowerBound = -32;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        double result2 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 += Math.pow(sln[i], 2);
            result2 += Math.cos(2 * Math.PI * sln[i]);
        }
        return (-20)
                * Math.pow(Math.E, (-0.2 * Math.pow(result1 / numVariable, 0.5)))
                - Math.pow(Math.E, result2 / numVariable) + 20 + Math.E;
    }
}
