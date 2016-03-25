package br.com.vempracaruaru.exception;

public class AdministradorJaCadastradoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public AdministradorJaCadastradoException(){
		super("Administrador já cadastrado!");
	}
	
	public AdministradorJaCadastradoException(String msg){
		super(msg);
	}

}
