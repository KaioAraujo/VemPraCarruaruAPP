package br.com.vempracaruaru.foto;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.FotoJaCadastradoException;
import br.com.vempracaruaru.exception.FotoNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarFotoException;

public class ControladorFoto {

	private IRepositorioFoto repositorio;
	
	public ControladorFoto() throws Exception{
		this.repositorio = new RepositorioFotoBDR();
	}
	
	public Foto cadastrar(Foto foto) throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoJaCadastradoException, Exception{
		if(foto != null){
		return	repositorio.cadastrar(foto);
		}
		else{
			return null;
		}
	}
	public ArrayList<Foto> listarTodos(String complemento) throws SQLException, FotoNaoCadastradoException, Exception{
		return repositorio.listarTodos(complemento);
	}
	public Foto listarPorId(int id) throws SQLException, FotoNaoCadastradoException, Exception{
		return repositorio.listarPorId(id);
	}
	public ArrayList<Foto> listarPorReferencia(String referencia, int idReferencia) throws SQLException, FotoNaoCadastradoException, Exception{
		return repositorio.listarPorReferencia(referencia, idReferencia);
	}
	public void alterar(Foto foto) throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoNaoCadastradoException, Exception{
		repositorio.alterar(foto);
	}
	public void deletar(int id) throws SQLException, FotoNaoCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		repositorio.deletar(id);
	}
}
