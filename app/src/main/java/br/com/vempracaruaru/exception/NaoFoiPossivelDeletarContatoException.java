package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarContatoException extends Exception{

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarContatoException(){
		super("Não foi possível inativar o artista!");
	}
	
	public NaoFoiPossivelDeletarContatoException(String msg){
		super(msg);
	}
}
