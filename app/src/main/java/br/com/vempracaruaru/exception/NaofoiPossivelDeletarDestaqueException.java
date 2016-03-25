package br.com.vempracaruaru.exception;

public class NaofoiPossivelDeletarDestaqueException extends Exception {

	private static final long serialVersionUID = -5953376500993574711L;

	public NaofoiPossivelDeletarDestaqueException(){
		super("Não foi possível inativar o destaque!");
	}
	
	public NaofoiPossivelDeletarDestaqueException(String msg){
		super(msg);
	}
}
