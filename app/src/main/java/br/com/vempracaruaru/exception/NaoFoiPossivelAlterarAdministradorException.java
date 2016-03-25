package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarAdministradorException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarAdministradorException(){
		super("N�o foi poss�vel efetuar a altera��o, o CPF que voc� est� tentando cadastrar j� existe no banco de dados!");
	}
	
	public NaoFoiPossivelAlterarAdministradorException(String msg){
		super(msg);
	}

}
