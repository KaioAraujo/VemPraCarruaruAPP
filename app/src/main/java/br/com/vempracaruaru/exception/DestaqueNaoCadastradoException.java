package br.com.vempracaruaru.exception;

public class DestaqueNaoCadastradoException extends Exception {

	private static final long serialVersionUID = -2849294837831479260L;

	public DestaqueNaoCadastradoException(){
		super("Destaque n?o cadastrado!");
	}
	
	public DestaqueNaoCadastradoException(String msg){
		super(msg);
	}
}
