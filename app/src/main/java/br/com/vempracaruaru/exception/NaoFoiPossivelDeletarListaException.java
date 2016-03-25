package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarListaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarListaException(){
		super("N�o foi poss�vel inativar a lista!");
	}
	
	public NaoFoiPossivelDeletarListaException(String msg){
		super(msg);
	}

}
