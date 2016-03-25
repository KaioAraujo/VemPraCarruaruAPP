package br.com.vempracaruaru.exception;

public class PontoTuristicoJaCadastradoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public PontoTuristicoJaCadastradoException(){
		super("Ponto turisitico já cadastrado!");
	}
	
	public PontoTuristicoJaCadastradoException(String msg){
		super(msg);
	}

}
