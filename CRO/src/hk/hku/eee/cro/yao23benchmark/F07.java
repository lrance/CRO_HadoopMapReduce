package hk.hku.eee.cro.yao23benchmark;

public class F07 extends Objective {

    public F07() {
        numVariable = 30;
        upperBound = 1.28;
        lowerBound = -1.28;
        FELimit = 150000;
    }

    @Override
    public double f(double[] sln) {
        double result1 = 0.0;
        for (int i = 0; i < numVariable; i++) {
            result1 += (i + 1) * Math.pow(sln[i], 4);
        }
        result1 += Benchmark.r.nextDouble();
        return result1;
    }
}
