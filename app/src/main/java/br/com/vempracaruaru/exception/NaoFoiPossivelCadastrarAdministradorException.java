package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarAdministradorException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarAdministradorException(){
		super("N�o foi poss�vel efetuar o cadastro do administrador, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarAdministradorException(String msg){
		super(msg);
	}

}
