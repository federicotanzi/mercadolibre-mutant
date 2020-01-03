package com.mercadolibre.mutante.domain;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Sequence {

    @NotNull
    private List<String> dna;

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

}
