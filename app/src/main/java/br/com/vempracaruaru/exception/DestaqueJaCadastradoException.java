package br.com.vempracaruaru.exception;

public class DestaqueJaCadastradoException extends Exception {

	private static final long serialVersionUID = -5953376500993574711L;

	public DestaqueJaCadastradoException(){
		super("Destaque já cadastrado!");
	}
	
	public DestaqueJaCadastradoException(String msg){
		super(msg);
	}
}
