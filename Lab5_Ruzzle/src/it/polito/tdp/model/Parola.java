package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

/*
 * classe corrispondente all'entità parola del DB
 * id posso evitare di metterlo in questo problema 
 * perchè in un dizionario il nome è già unico di
 * per sè, perciò posso vedere esso come chiave primaria
 */

public class Parola {
	
	private String nome ;
	//definisco una Lista indicante le posizioni di ogni carattere di tale parola nel ruzzle
	private List<Position> positions = new ArrayList<Position>();
	
	public Parola(String n){
		nome=n;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parola other = (Parola) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public List<Position> getPositions() {
		// TODO Auto-generated method stub
		return positions;
	}
	

	
}
