package br.com.vempracaruaru.exception;

public class ArtistaJaCadastradoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public ArtistaJaCadastradoException(){
		super("Artista j? cadastrado!");
	}
	
	public ArtistaJaCadastradoException(String msg){
		super(msg);
	}

}
