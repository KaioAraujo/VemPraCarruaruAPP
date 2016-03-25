package br.com.vempracaruaru.lista;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.ListaJaCadastradoException;
import br.com.vempracaruaru.exception.ListaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarListaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelDeletarListaException;

public class ControladorLista {

private IRepositorioLista repositorio;
	
	public ControladorLista() throws Exception{
		this.repositorio = new RepositorioListaBDR();
	}
	
	public void cadastrar(Lista lista) throws SQLException, ListaJaCadastradoException, Exception{
		System.out.println("passando pela controladora - concluido com sucesso -");
		if (lista != null) {
			repositorio.cadastrar(lista);
		}
	}
	public ArrayList<Lista> listarTodos(String complemento) throws SQLException, ListaNaoCadastradoException, Exception{
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return repositorio.listarTodos(complemento);
	}
	public ArrayList<Lista> listarPorUsuario(int idUsuario, char visitado) throws SQLException, ListaNaoCadastradoException, Exception{
		return repositorio.listarPorUsuario(idUsuario, visitado);
	}
	public Lista listarPorUsuarioPonto(int idUsuario, int idPonto, char visitado) throws SQLException, ListaNaoCadastradoException, Exception{
		return repositorio.listarPorUsuarioPonto(idUsuario, idPonto, visitado);
	}
	public void alterar(Lista lista) throws SQLException, NaoFoiPossivelCadastrarListaException, ListaNaoCadastradoException, Exception{
		repositorio.alterar(lista);	
	}
	public void deletar(int idUsuario, int idPontoTuristico) throws SQLException, NaoFoiPossivelDeletarListaException, Exception{
		repositorio.deletar(idUsuario, idPontoTuristico);
	}
}
