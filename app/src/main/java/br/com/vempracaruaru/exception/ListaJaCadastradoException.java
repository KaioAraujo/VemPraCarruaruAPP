package br.com.vempracaruaru.exception;

public class ListaJaCadastradoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public ListaJaCadastradoException(){
		super("lista já cadastrado!");
	}
	
	public ListaJaCadastradoException(String msg){
		super(msg);
	}

}
