package br.com.vempracaruaru.exception;

public class ContatoNaoCadastradoException extends Exception {

	private static final long serialVersionUID = -2849294837831479260L;

	public ContatoNaoCadastradoException(){
		super("Contato n�o cadastrado!");
	}
	
	public ContatoNaoCadastradoException(String msg){
		super(msg);
	}

}
