package br.com.vempracaruaru.exception;

public class NaofoiPossivelAlterarContatoException extends Exception{

	private static final long serialVersionUID = -5953376500993574711L;

	public NaofoiPossivelAlterarContatoException(){
		super("N�o foi poss�vel efetuar a altera��o");
	}

	public NaofoiPossivelAlterarContatoException(String msg){
		super(msg);
	}
}
