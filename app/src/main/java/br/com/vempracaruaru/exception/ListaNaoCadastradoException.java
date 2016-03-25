package br.com.vempracaruaru.exception;

public class ListaNaoCadastradoException extends Exception{
	
	private static final long serialVersionUID = -2849294837831479260L;

	public ListaNaoCadastradoException(){
		super("lista não cadastrada!");
	}
	
	public ListaNaoCadastradoException(String msg){
		super(msg);
	}

}
