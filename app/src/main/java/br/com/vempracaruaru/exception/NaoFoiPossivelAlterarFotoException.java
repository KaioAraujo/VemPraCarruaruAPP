package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarFotoException extends Exception{

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarFotoException(){
		super("N�o foi poss�vel efetuar a altera��o");
	}

	public NaoFoiPossivelAlterarFotoException(String msg){
		super(msg);
	}

}
