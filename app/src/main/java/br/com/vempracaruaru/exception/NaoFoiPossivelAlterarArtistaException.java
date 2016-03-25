package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarArtistaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarArtistaException(){
		super("Não foi possível efetuar a alteração");
	}
	
	public NaoFoiPossivelAlterarArtistaException(String msg){
		super(msg);
	}

}
