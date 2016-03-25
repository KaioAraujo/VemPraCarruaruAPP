package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarObraException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarObraException(){
		super("Não foi possível efetuar a alteração");
	}
	
	public NaoFoiPossivelAlterarObraException(String msg){
		super(msg);
	}

}
