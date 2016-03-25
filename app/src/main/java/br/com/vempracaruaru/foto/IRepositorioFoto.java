package br.com.vempracaruaru.foto;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.FotoJaCadastradoException;
import br.com.vempracaruaru.exception.FotoNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarFotoException;


public interface IRepositorioFoto {

	public Foto cadastrar(Foto foto) throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoJaCadastradoException, Exception;
	public ArrayList<Foto> listarTodos(String complemento) throws SQLException, FotoNaoCadastradoException, Exception;
	public Foto listarPorId(int id) throws SQLException, FotoNaoCadastradoException, Exception;
	public ArrayList<Foto> listarPorReferencia(String referencia, int idReferencia) throws SQLException, FotoNaoCadastradoException, Exception;
	public void alterar(Foto foto) throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoNaoCadastradoException, Exception;
	public void deletar(int id) throws SQLException, FotoNaoCadastradoException, Exception;
	public boolean existeId(Foto foto) throws SQLException, FotoJaCadastradoException, Exception;
	
}
