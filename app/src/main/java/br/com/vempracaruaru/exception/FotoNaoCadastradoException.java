package br.com.vempracaruaru.exception;

public class FotoNaoCadastradoException extends Exception{
	
	private static final long serialVersionUID = -2849294837831479260L;

	public FotoNaoCadastradoException(){
		super("Imagem não cadastrado!");
	}
	
	public FotoNaoCadastradoException(String msg){
		super(msg);
	}

}
