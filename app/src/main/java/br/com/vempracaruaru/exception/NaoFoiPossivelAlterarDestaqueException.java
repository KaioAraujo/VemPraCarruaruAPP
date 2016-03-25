package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarDestaqueException extends Exception {

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarDestaqueException(){
		super("N�o foi poss�vel efetuar a altera��o");
	}
	
	public NaoFoiPossivelAlterarDestaqueException(String msg){
		super(msg);
	}
}
