package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarDestaqueException extends Exception {

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarDestaqueException(){
		super("N�o foi poss�vel efetuar o cadastro do destaque, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarDestaqueException(String msg){
		super(msg);
	}
}
