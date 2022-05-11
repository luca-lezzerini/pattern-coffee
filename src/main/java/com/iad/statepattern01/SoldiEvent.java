package com.iad.statepattern01;

public class SoldiEvent extends Event {

    private double importo;

    public SoldiEvent() {
        importo = 0;
    }

    public SoldiEvent(double importo) {
        this.importo = importo;
    }

    public double getImporto() {
        return importo;
    }

    @Override
    public String toString() {
        return "SoldiEvent{" +  "importo=" + importo + '}';
    }

}
