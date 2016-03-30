package br.com.vempracaruaru.exception;

public class ContatoJaCadastradoException extends Exception {
	
	private static final long serialVersionUID = -5953376500993574711L;

	public ContatoJaCadastradoException(){
		super("Contato j? cadastrado!");
	}
	
	public ContatoJaCadastradoException(String msg){
		super(msg);
	}
}
