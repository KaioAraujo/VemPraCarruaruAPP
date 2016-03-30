package br.com.vempracaruaru.exception;

public class PontoTuristicoNaoCadastradoException extends Exception{
	
	private static final long serialVersionUID = -2849294837831479260L;

	public PontoTuristicoNaoCadastradoException(){
		super("Ponto turisitico não cadastrado!");
	}
	
	public PontoTuristicoNaoCadastradoException(String msg){
		super(msg);
	}

}
