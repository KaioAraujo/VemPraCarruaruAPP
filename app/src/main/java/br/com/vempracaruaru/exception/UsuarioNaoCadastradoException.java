package br.com.vempracaruaru.exception;

public class UsuarioNaoCadastradoException extends Exception{
	
	private static final long serialVersionUID = -2849294837831479260L;

	public UsuarioNaoCadastradoException(){
		super("Usuario não cadastrado!");
	}
	
	public UsuarioNaoCadastradoException(String msg){
		super(msg);
	}

}
