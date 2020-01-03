package com.mercadolibre.mutante.detector;

public class MutantContext {

    private char[][] dna;
    private int sequenceSizeMutant;
    private int sequencesMatchMutant;
    private int quantityMutantSequences;

    public MutantContext(char[][] dna, int sequenceSizeMutant, int sequencesMatchMutant) {
        this.dna = dna;
        this.sequenceSizeMutant = sequenceSizeMutant;
        this.sequencesMatchMutant = sequencesMatchMutant;
        this.quantityMutantSequences = 0;
    }

    public char[][] getDna() {
        return dna;
    }

    public int getDnaLength() {
        return dna.length;
    }

    protected char getDnaChar(int row, int column){
        return dna[row][column];
    }

    public int getSequenceSizeMutant() {
        return sequenceSizeMutant;
    }

    public int getSequencesMatchMutant() {
        return sequencesMatchMutant;
    }

    public int getQuantityMutantSequences() {
        return quantityMutantSequences;
    }

    public void addQuantityMutantSequences() {
        this.quantityMutantSequences = this.quantityMutantSequences + 1;
    }

    public boolean hasMatchQuantitySequencesMutant() {
        return quantityMutantSequences >= sequencesMatchMutant;
    }
}
