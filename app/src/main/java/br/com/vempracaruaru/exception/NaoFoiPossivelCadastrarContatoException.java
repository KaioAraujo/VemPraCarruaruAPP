package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarContatoException extends Exception {

	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarContatoException(){
		super("Não foi possível efetuar o cadastro do contato, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarContatoException(String msg){
		super(msg);
	}
}
