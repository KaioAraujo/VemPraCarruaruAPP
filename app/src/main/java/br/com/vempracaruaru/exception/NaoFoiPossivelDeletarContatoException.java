package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarContatoException extends Exception{

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarContatoException(){
		super("N�o foi poss�vel inativar o artista!");
	}
	
	public NaoFoiPossivelDeletarContatoException(String msg){
		super(msg);
	}
}
