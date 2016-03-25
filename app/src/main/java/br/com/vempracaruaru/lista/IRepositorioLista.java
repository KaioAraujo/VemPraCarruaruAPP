package br.com.vempracaruaru.lista;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.ListaJaCadastradoException;
import br.com.vempracaruaru.exception.ListaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarListaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelDeletarListaException;

public interface IRepositorioLista {

	public void cadastrar(Lista lista) throws SQLException, ListaJaCadastradoException, Exception;
	public ArrayList<Lista> listarTodos(String complemento) throws SQLException, ListaNaoCadastradoException, Exception;
	public ArrayList<Lista> listarPorUsuario(int idUsuario, char visitado) throws SQLException, ListaNaoCadastradoException, Exception;
	public Lista listarPorUsuarioPonto(int idUsuario, int idPonto, char visitado) throws SQLException, ListaNaoCadastradoException, Exception;
	public void alterar(Lista lista) throws SQLException, NaoFoiPossivelCadastrarListaException, ListaNaoCadastradoException, Exception;
	public void deletar(int idUsuario, int idPontoTuristico) throws SQLException, NaoFoiPossivelDeletarListaException, Exception;
	public boolean existeId(Lista lista) throws SQLException, ListaJaCadastradoException, Exception;

}
