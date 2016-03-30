package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarUsuarioException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarUsuarioException(){
		super("Não foi possível efetuar a alteração");
	}
	
	public NaoFoiPossivelAlterarUsuarioException(String msg){
		super(msg);
	}

}
