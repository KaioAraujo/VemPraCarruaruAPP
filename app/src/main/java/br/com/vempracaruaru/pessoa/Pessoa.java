package br.com.vempracaruaru.pessoa;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	private int			id;
	private String		nome;
	
	public Pessoa(int id, String nome) {
			this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + "]";
	}	

}
