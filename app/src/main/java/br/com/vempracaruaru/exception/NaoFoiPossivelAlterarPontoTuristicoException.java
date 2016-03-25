package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarPontoTuristicoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarPontoTuristicoException(){
		super("N�o foi poss�vel efetuar a altera��o");
	}
	
	public NaoFoiPossivelAlterarPontoTuristicoException(String msg){
		super(msg);
	}

}
