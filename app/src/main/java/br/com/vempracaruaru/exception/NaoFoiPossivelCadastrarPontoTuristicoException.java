package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarPontoTuristicoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarPontoTuristicoException(){
		super("Não foi possível efetuar o cadastro do ponto turistico, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarPontoTuristicoException(String msg){
		super(msg);
	}

}
