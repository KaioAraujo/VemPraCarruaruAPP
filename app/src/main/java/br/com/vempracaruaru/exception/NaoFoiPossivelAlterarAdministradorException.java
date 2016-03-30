package br.com.vempracaruaru.exception;

public class NaoFoiPossivelAlterarAdministradorException extends Exception{
	
	private static final long serialVersionUID = -5953376500993574711L;

	public NaoFoiPossivelAlterarAdministradorException(){
		super("Não foi possível efetuar a alteração, o CPF que você está tentando cadastrar já existe no banco de dados!");
	}
	
	public NaoFoiPossivelAlterarAdministradorException(String msg){
		super(msg);
	}

}
