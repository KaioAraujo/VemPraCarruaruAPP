package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarDestaqueException extends Exception {

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarDestaqueException(){
		super("Não foi possível efetuar a alteração");
	}
	
	public NaoFoiPossivelAlterarDestaqueException(String msg){
		super(msg);
	}
}
