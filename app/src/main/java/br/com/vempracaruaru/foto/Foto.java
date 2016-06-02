package br.com.vempracaruaru.foto;

import java.io.Serializable;

public class Foto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int			id;
	private int			idAdministrador;
	private	int			idReferencia;
	private String 		referencia;
	private String		imagem;
	private String		descricao;
	private char		ativo;
	
	public Foto(int id, int idAdministrador, int idReferencia, String referencia, String imagem, String descricao, char ativo) {
		this.id = id;
		this.idAdministrador = idAdministrador;
		this.idReferencia = idReferencia;
		this.referencia = referencia;
		this.imagem = imagem;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAdministrador() {
		return idAdministrador;
	}
	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	public int getIdReferencia() {
		return idReferencia;
	}
	public void setIdReferencia(int idReferencia) {
		this.idReferencia = idReferencia;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public char getAtivo() {
		return ativo;
	}
	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Foto [id=" + id + ", idAdministrador=" + idAdministrador + ", idReferencia=" + idReferencia
				+ ", referencia=" + referencia + ", imagem=" + imagem + ", descricao=" + descricao + ", ativo=" + ativo
				+ "]";
	}
}
