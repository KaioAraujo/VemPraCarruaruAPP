package br.com.vempracaruaru.artista;

import java.io.Serializable;

import br.com.vempracaruaru.pessoa.Pessoa;

public class Artista extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	private int				idAdministrador;
	private String			nomeAdministrador;
	private String			historico;
	private String			tipo;
	private String			foto;
	private String			telefone;
	private String			email;
	private String			twitter;
	private String			instagram;
	private String			facebook;
	private char			ativo;

	public Artista(int id, String nome, String tipo) {
		super(id,nome);
		this.tipo = tipo;
	}

	public Artista(int id, String nome, int idAdministrador, String nomeAdministrador, String historico, String tipo,
			String foto, String telefone, String email, String twitter, String instagram, String facebook,
			char ativo) {
		super(id, nome);
		this.idAdministrador = idAdministrador;
		this.nomeAdministrador = nomeAdministrador;
		this.historico = historico;
		this.tipo = tipo;
		this.foto = foto;
		this.telefone = telefone;
		this.email = email;
		this.twitter = twitter;
		this.instagram = instagram;
		this.facebook = facebook;
		this.ativo = ativo;
	}

	public int getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getNomeAdministrador() {
		return nomeAdministrador;
	}

	public void setNomeAdministrador(String nomeAdministrador) {
		this.nomeAdministrador = nomeAdministrador;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Artista [idAdministrador=" + idAdministrador + ", nomeAdministrador=" + nomeAdministrador
				+ ", historico=" + historico + ", tipo=" + tipo + ", foto=" + foto + ", telefone=" + telefone
				+ ", email=" + email + ", twitter=" + twitter + ", instagram=" + instagram + ", facebook=" + facebook
				+ ", ativo=" + ativo + "]";
	}				
}
