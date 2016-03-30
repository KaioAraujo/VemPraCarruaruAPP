package br.com.vempracaruaru.exception;

public class NaoFoiPossivelDeletarPontoTuristicoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelDeletarPontoTuristicoException(){
		super("Não foi possível inativar o ponto turistico!");
	}
	
	public NaoFoiPossivelDeletarPontoTuristicoException(String msg){
		super(msg);
	}

}
