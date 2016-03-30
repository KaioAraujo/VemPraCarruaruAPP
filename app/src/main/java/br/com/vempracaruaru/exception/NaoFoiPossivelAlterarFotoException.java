package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarFotoException extends Exception{

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarFotoException(){
		super("Não foi possível efetuar a alteração");
	}

	public NaoFoiPossivelAlterarFotoException(String msg){
		super(msg);
	}

}
