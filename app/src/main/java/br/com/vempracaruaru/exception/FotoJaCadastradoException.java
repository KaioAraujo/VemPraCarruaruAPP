package br.com.vempracaruaru.exception;

public class FotoJaCadastradoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public FotoJaCadastradoException(){
		super("Imagem já cadastrado!");
	}
	
	public FotoJaCadastradoException(String msg){
		super(msg);
	}

}
