package br.com.vempracaruaru.obra;


import java.io.Serializable;

public class Obra implements Serializable {

	private int				id;
	private int 			idArtista;
	private String 			nomeArtista;
	private int 			idAdministrador;
	private String			nomeAdministrador;
	private int				idPontoTuristico;
	private String			nomePontoTuristico;
	private String 			nome;
	private char 			ativo;
	private String			foto;
	private String			descricao;
	
	public Obra(int id, int idArtista, String nomeArtista, int idAdministrador,
			String nomeAdministrador, int idPontoTuristico,
			String nomePontoTuristico, String nome, char ativo, String foto,
			String descricao) {
		super();
		this.id = id;
		this.idArtista = idArtista;
		this.nomeArtista = nomeArtista;
		this.idAdministrador = idAdministrador;
		this.nomeAdministrador = nomeAdministrador;
		this.idPontoTuristico = idPontoTuristico;
		this.nomePontoTuristico = nomePontoTuristico;
		this.nome = nome;
		this.ativo = ativo;
		this.foto = foto;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}
	public String getNomeArtista() {
		return nomeArtista;
	}
	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Obra [id=" + id + ", idArtista=" + idArtista + ", nomeArtista="
				+ nomeArtista + ", idAdministrador=" + idAdministrador
				+ ", nomeAdministrador=" + nomeAdministrador
				+ ", idPontoTuristico=" + idPontoTuristico
				+ ", nomePontoTuristico=" + nomePontoTuristico + ", nome="
				+ nome + ", ativo=" + ativo + ", foto=" + foto + ", descricao="
				+ descricao + "]";
	}
	
}
