package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarObraException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarObraException(){
		super("N�o foi poss�vel efetuar o cadastro do usuario, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarObraException(String msg){
		super(msg);
	}

}
