package br.com.vempracaruaru.exception;

public class ArtistaNaoCadastradoException extends Exception{
	
	private static final long serialVersionUID = -2849294837831479260L;

	public ArtistaNaoCadastradoException(){
		super("Artista n?o cadastrado!");
	}
	
	public ArtistaNaoCadastradoException(String msg){
		super(msg);
	}

}
