package br.com.vempracaruaru.usuario;

import java.io.Serializable;

import br.com.vempracaruaru.pessoa.Pessoa;

public class Usuario extends Pessoa implements Serializable {
	
	private String		email;
	private String		localizacao;
	private String		senha;
	private String		userFacebook;
	private String		linkfacebook;
	private int 		pontos;
	private char		ativo;
	
	public Usuario(int id, String nome, String email, String localizacao, String senha, String userFacebook,
			String link_facebook, int pontos, char ativo) {
		super(id, nome);
		this.email = email;
		this.localizacao = localizacao;
		this.senha = senha;
		this.userFacebook = userFacebook;
		this.linkfacebook = link_facebook;
		this.pontos = pontos;
		this.ativo = ativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUserFacebook() {
		return userFacebook;
	}

	public void setUserFacebook(String userFacebook) {
		this.userFacebook = userFacebook;
	}

	public String getLinkfacebook() {
		return linkfacebook;
	}

	public void setLinkfacebook(String linkfacebook) {
		this.linkfacebook = linkfacebook;
	}

	public int getPontos() {
		return pontos;
	}

	public void setAtivo(int pontos) {
		this.pontos = pontos;
	}
	
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Usuario [emial=" + email + ", localizacao=" + localizacao + ", senha=" + senha + ", userFacebook="
				+ userFacebook + ", linkfacebook=" + linkfacebook + ", pontos=" + pontos + ", ativo=" + ativo + "]";
	}
	
}
