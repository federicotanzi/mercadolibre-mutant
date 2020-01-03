package com.mercadolibre.mutante.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "result")
public class Result {

	@Id
	private String id;

	private Sequence sequence;

	private boolean mutant;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
}
