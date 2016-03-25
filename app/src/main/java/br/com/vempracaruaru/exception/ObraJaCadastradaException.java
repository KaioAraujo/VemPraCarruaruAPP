package br.com.vempracaruaru.exception;

public class ObraJaCadastradaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public ObraJaCadastradaException(){
		super("Obra j� cadastrado!");
	}
	
	public ObraJaCadastradaException(String msg){
		super(msg);
	}

}
