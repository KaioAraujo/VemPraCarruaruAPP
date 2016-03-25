package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarUsuarioException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarUsuarioException(){
		super("Não foi possível inativar o usuario!");
	}
	
	public NaoFoiPossivelDeletarUsuarioException(String msg){
		super(msg);
	}

}
