package br.com.vempracaruaru.exception;

public class NaofoiPossivelAlterarContatoException extends Exception{

	private static final long serialVersionUID = -5953376500993574711L;

	public NaofoiPossivelAlterarContatoException(){
		super("Não foi possível efetuar a alteração");
	}

	public NaofoiPossivelAlterarContatoException(String msg){
		super(msg);
	}
}
