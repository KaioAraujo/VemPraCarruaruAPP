package br.com.vempracaruaru.exception;

public class BusinessException extends Exception{
	
	private static final long serialVersionUID = -2630096627816818099L;

	public BusinessException(String msg){
		super(msg);
	}

}
