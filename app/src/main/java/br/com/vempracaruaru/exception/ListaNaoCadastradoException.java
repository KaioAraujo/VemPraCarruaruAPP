package br.com.vempracaruaru.exception;

public class ListaNaoCadastradoException extends Exception{
	
	private static final long serialVersionUID = -2849294837831479260L;

	public ListaNaoCadastradoException(){
		super("lista n�o cadastrada!");
	}
	
	public ListaNaoCadastradoException(String msg){
		super(msg);
	}

}
