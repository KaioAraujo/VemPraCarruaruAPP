package br.com.vempracaruaru.pontoturistico;


import java.io.Serializable;
import java.util.ArrayList;

import br.com.vempracaruaru.foto.Foto;

public class PontoTuristico implements Serializable {

	private static final long serialVersionUID = 1L;
	private int				id;
	private int				idAdministrador;
	private String			nomeAdministrador;
	private String			nome;
	private String			endereco;
	private String			latitude;
	private String			longitude;
	private String			telefone;
	private String			email;
	private String			tempoVisitacao;
	private String			horarioFuncionamento;
	private String			historicoDescricao;
	private char			ativo;
	private String			foto;
	private int 			quantidadeObras;

	// array das fotos do ponto

	private ArrayList<Foto> listaFotoPonto;
	public PontoTuristico(int id, String nome, String foto, ArrayList<Foto> listaFotoPonto){
		this.id=id;
		this.nome= nome;
		this.foto=foto;
		this.listaFotoPonto = listaFotoPonto;

	}

	public ArrayList<Foto> getListaFotoPonto(){
		return listaFotoPonto;
	}

	public void setListaFotoPonto(ArrayList<Foto> listaFotoPonto){this.listaFotoPonto = listaFotoPonto;}
	// fim do array

	public PontoTuristico(int id, int idAdministrador,
						  String nomeAdministrador, String nome, String endereco, String latitude, String longitude,
						  String telefone, String email, String tempoVisitacao,
						  String horarioFuncionamento, String historicoDescricao, String foto, char ativo, int quantidadeObras) {
		super();
		this.id = id;
		this.idAdministrador = idAdministrador;
		this.nomeAdministrador = nomeAdministrador;
		this.nome = nome;
		this.endereco = endereco;
		this.latitude = latitude;
		this.longitude = longitude;
		this.telefone = telefone;
		this.email = email;
		this.tempoVisitacao = tempoVisitacao;
		this.horarioFuncionamento = horarioFuncionamento;
		this.historicoDescricao = historicoDescricao;
		this.foto = foto;
		this.ativo = ativo;
		this.quantidadeObras = quantidadeObras;
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

	public String getNomeAdministrador() {
		return nomeAdministrador;
	}

	public void setNomeAdministrador(String nomeAdministrador) {
		this.nomeAdministrador = nomeAdministrador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	public String getTempoVisitacao() {
		return tempoVisitacao;
	}

	public void setTempoVisitacao(String tempoVisitacao) {
		this.tempoVisitacao = tempoVisitacao;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public String getHistoricoDescricao() {
		return historicoDescricao;
	}

	public void setHistoricoDescricao(String historicoDescricao) {
		this.historicoDescricao = historicoDescricao;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public int getQuantidadeObras() {
		return quantidadeObras;
	}

	public void setQuantidadeObras(int quantidadeObras) {
		this.quantidadeObras = quantidadeObras;
	}

	@Override
	public String toString() {
		return "PontoTuristico [id=" + id + ", idAdministrador="
				+ idAdministrador + ", nomeAdministrador=" + nomeAdministrador
				+ ", nome=" + nome + ", endereco=" + endereco + ", telefone="
				+ telefone +  ", email=" + email + ", tempoVisitacao=" + tempoVisitacao
				+ ", horarioFuncionamento=" + horarioFuncionamento
				+ ", historicoDescricao=" + historicoDescricao + ", ativo="
				+ ativo + ", foto=" + foto + ", quantidadeObras=" + quantidadeObras + "]";
	}
}
