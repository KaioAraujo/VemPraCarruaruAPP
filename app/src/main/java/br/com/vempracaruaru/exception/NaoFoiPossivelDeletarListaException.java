package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarListaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarListaException(){
		super("Não foi possível inativar a lista!");
	}
	
	public NaoFoiPossivelDeletarListaException(String msg){
		super(msg);
	}

}
