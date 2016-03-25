package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarUsuarioException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarUsuarioException(){
		super("N�o foi poss�vel efetuar a altera��o");
	}
	
	public NaoFoiPossivelAlterarUsuarioException(String msg){
		super(msg);
	}

}
