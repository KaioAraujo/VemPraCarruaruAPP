package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarFotoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarFotoException(){
		super("N�o foi poss�vel inativar a imagem!");
	}
	
	public NaoFoiPossivelDeletarFotoException(String msg){
		super(msg);
	}

}
