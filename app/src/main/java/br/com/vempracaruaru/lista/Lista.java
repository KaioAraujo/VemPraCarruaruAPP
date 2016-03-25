package br.com.vempracaruaru.lista;


import java.io.Serializable;

public class Lista implements Serializable {

	private int 						idUsuario;
	private String 						nomeUsuario;
	private int 						idPontoTuristico;
	private String 						nomePontoTuristico;
	private String 						dataHora;
	private char						visitado;
	
	public Lista(int idUsuario, int idPontoTuristico, String dataHora,
			char visitado) {
		super();
		this.idUsuario = idUsuario;
		this.idPontoTuristico = idPontoTuristico;
		this.dataHora = dataHora;
		this.visitado = visitado;
	}

	public Lista(int idUsuario, String nomeUsuario, int idPontoTuristico,
			String nomePontoTuristico, String dataHora, char visitado) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.idPontoTuristico = idPontoTuristico;
		this.nomePontoTuristico = nomePontoTuristico;
		this.dataHora = dataHora;
		this.visitado = visitado;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public int getIdPontoTuristico() {
		return idPontoTuristico;
	}
	public void setIdPontoTuristico(int idPontoTuristico) {
		this.idPontoTuristico = idPontoTuristico;
	}
	public String getNomePontoTuristico() {
		return nomePontoTuristico;
	}
	public void setNomePontoTuristico(String nomePontoTuristico) {
		this.nomePontoTuristico = nomePontoTuristico;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public char getVisitado() {
		return visitado;
	}
	public void setVisitado(char visitado) {
		this.visitado = visitado;
	}
	
}
