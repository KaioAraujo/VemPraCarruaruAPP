package br.com.vempracaruaru.obra;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarObraException;
import br.com.vempracaruaru.exception.ObraJaCadastradaException;
import br.com.vempracaruaru.exception.ObraNaoCadastradaException;


public interface IRepositorioObra {

	public Obra cadastrar(Obra obra) throws SQLException, NaoFoiPossivelCadastrarObraException, ObraJaCadastradaException, Exception;
	public ArrayList<Obra> listarTodos(String complemento) throws SQLException, ObraNaoCadastradaException, Exception;
	public Obra listarPorId(int id) throws SQLException, ObraNaoCadastradaException, Exception;
	public ArrayList<Obra> listarPorNome(String nome) throws SQLException, ObraNaoCadastradaException, Exception;
	public void alterar(Obra obra) throws SQLException, NaoFoiPossivelCadastrarObraException, ObraNaoCadastradaException, Exception;
	public void deletar(int id) throws SQLException, ObraNaoCadastradaException, Exception;
	public void ativar(int id) throws SQLException, ObraNaoCadastradaException, Exception;
	public boolean existeId(Obra obra) throws SQLException, ObraJaCadastradaException, Exception;
	public void definirImagemPrincipal(int id, String imagem) throws SQLException, ObraNaoCadastradaException, NaoFoiPossivelCadastrarObraException, Exception;
}
