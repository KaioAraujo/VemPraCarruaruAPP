package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarArtistaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarArtistaException(){
		super("Não foi possível efetuar o cadastro do artista, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarArtistaException(String msg){
		super(msg);
	}

}
