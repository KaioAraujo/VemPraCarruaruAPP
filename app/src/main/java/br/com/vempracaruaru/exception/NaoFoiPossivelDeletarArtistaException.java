package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarArtistaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarArtistaException(){
		super("Não foi possível inativar o artista!");
	}
	
	public NaoFoiPossivelDeletarArtistaException(String msg){
		super(msg);
	}

}
