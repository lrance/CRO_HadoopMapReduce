package hk.hku.eee.cro.yao23benchmark;

public abstract class Objective {

    public int numVariable, FELimit;
    public double upperBound, lowerBound, bias = 0;
    
    public Objective() {
    }

    public abstract double f(double[] x);
}
