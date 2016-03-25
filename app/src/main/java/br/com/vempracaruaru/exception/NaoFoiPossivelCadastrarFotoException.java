package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarFotoException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarFotoException(){
		super("N�o foi poss�vel efetuar o cadastro da imagem, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarFotoException(String msg){
		super(msg);
	}

}
