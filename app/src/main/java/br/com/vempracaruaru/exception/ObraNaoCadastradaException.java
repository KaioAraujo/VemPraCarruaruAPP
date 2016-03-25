package br.com.vempracaruaru.exception;

public class ObraNaoCadastradaException extends Exception{
	
	private static final long serialVersionUID = -2849294837831479260L;

	public ObraNaoCadastradaException(){
		super("Obra não cadastrado!");
	}
	
	public ObraNaoCadastradaException(String msg){
		super(msg);
	}

}
