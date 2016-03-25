package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarArtistaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarArtistaException(){
		super("N�o foi poss�vel inativar o artista!");
	}
	
	public NaoFoiPossivelDeletarArtistaException(String msg){
		super(msg);
	}

}
