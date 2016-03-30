package br.com.vempracaruaru.exception;

public class NaoFoiPossivelCadastrarListaException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelCadastrarListaException(){
		super("Não foi possível efetuar o cadastro da lista, tente novamente e caso o problema persista, entre em contato com o suporte no menu \"SUPORTE\"!");
	}
	
	public NaoFoiPossivelCadastrarListaException(String msg){
		super(msg);
	}

}
