package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarAdministradorException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarAdministradorException(){
		super("N�o foi poss�vel inativar o administrador!");
	}
	
	public NaoFoiPossivelDeletarAdministradorException(String msg){
		super(msg);
	}

}
