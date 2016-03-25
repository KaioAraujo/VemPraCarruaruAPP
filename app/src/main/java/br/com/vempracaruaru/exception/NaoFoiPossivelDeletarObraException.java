package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarObraException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarObraException(){
		super("N�o foi poss�vel inativar a obra!");
	}
	
	public NaoFoiPossivelDeletarObraException(String msg){
		super(msg);
	}

}
