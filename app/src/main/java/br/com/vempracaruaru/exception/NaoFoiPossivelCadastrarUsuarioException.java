package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarUsuarioException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarUsuarioException(){
		super("Não foi possível efetuar o cadastro do usuario, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarUsuarioException(String msg){
		super(msg);
	}

}
