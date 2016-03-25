package br.com.vempracaruaru.obra;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.ArtistaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarObraException;
import br.com.vempracaruaru.exception.ObraJaCadastradaException;
import br.com.vempracaruaru.exception.ObraNaoCadastradaException;

public class ControladorObra {

	private IRepositorioObra repositorio;
	
	public ControladorObra() throws Exception{
		this.repositorio = new RepositorioObraBDR();
	}

	public Obra cadastrar(Obra obra) throws SQLException, NaoFoiPossivelCadastrarObraException, ObraJaCadastradaException, Exception{
		if(obra !=null){
		return	repositorio.cadastrar(obra);
		}else{
			return null;
		}
	}
	public ArrayList<Obra> listarTodos(String complemento) throws SQLException, ObraNaoCadastradaException, Exception{
		return repositorio.listarTodos(complemento);
	}
	public Obra listarPorId(int id) throws SQLException, ObraNaoCadastradaException, Exception{
		return repositorio.listarPorId(id);
	}
	public ArrayList<Obra> listarPorNome(String nome) throws SQLException, ObraNaoCadastradaException, Exception{
		return repositorio.listarPorNome(nome);
	}
	public void alterar(Obra obra) throws SQLException, NaoFoiPossivelCadastrarObraException, ObraNaoCadastradaException, Exception{
		repositorio.alterar(obra);
	}
	public void definirImagemPrincipal(int id, String imagem) throws SQLException, ArtistaNaoCadastradoException, NaoFoiPossivelCadastrarArtistaException, Exception {
		repositorio.definirImagemPrincipal(id, imagem);
	}
	public void deletar(int id) throws SQLException, ObraNaoCadastradaException, Exception{
		repositorio.deletar(id);
	}
	public void ativar(int id) throws SQLException, ObraNaoCadastradaException, Exception{
		repositorio.ativar(id);
	}
	
}
