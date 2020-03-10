package it.dstech.digimon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Digimon implements Serializable{

	private String nome;
	private int atk;
	private int def;
	private int res;
	private Evoluzione evol;
	



	public Digimon(String nome, int atk, int def, int res, Evoluzione evol) {
		super();
		this.nome = nome;
		this.atk = atk;
		this.def = def;
		this.res = res;
		this.evol = evol;
	
	}
	
	public Digimon() {
		
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
		Digimon other = (Digimon) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public Evoluzione getEvol() {
		return evol;
	}

	public void setEvol(Evoluzione evol) {
		this.evol = evol;
	}


	@Override
	public String toString() {
		return "Digimon [nome=" + nome + ", atk=" + atk + ", def=" + def + ", res=" + res + ", evol=" + evol
				+ "]";
	}

	
	


	

	

}
