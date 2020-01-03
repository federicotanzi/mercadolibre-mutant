package com.mercadolibre.mutante.domain;

public class Status {

    private long mutantCount = 0L;

    private long humanCount = 0L;

    private double ratio = 0D;

    public long getMutantCount() {
        return mutantCount;
    }

    public void setMutantCount(long mutantCount) {
        this.mutantCount = mutantCount;
    }

    public long getHumanCount() {
        return humanCount;
    }

    public void setHumanCount(long humanCount) {
        this.humanCount = humanCount;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
