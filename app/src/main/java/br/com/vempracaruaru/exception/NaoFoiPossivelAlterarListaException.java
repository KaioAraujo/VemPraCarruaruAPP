package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarListaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarListaException(){
		super("Não foi possível efetuar a alteração");
	}
	
	public NaoFoiPossivelAlterarListaException(String msg){
		super(msg);
	}

}
