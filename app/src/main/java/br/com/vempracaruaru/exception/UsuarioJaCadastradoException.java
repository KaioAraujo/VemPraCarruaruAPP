package br.com.vempracaruaru.exception;

public class UsuarioJaCadastradoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public UsuarioJaCadastradoException(){
		super("Usuario já cadastrado!");
	}
	
	public UsuarioJaCadastradoException(String msg){
		super(msg);
	}

}
