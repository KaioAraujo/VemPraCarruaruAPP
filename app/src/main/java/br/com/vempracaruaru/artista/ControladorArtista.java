package br.com.vempracaruaru.artista;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.ArtistaJaCadastradoException;
import br.com.vempracaruaru.exception.ArtistaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarArtistaException;

public class ControladorArtista {

	private IRepositorioArtista repositorio;
	
	public ControladorArtista()throws Exception{
		this.repositorio = new RepositorioArtistaBDR();
	}
	
	public Artista cadastrar(Artista artista) throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaJaCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		if(artista !=null){
			return repositorio.cadastrar(artista);
		}else{
			return null;
		}
	}
	
	public ArrayList<Artista> listarTodos(String complemento) throws SQLException, ArtistaNaoCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		return repositorio.listarTodos(complemento);
	}
	public Artista listarPorId(int id) throws SQLException, ArtistaNaoCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		return repositorio.listarPorId(id);
	}
	public ArrayList<Artista> listarPorNome(String nome) throws SQLException, ArtistaNaoCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		return repositorio.listarPorNome(nome);
	}
	
	public void alterar(Artista artista) throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaNaoCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		repositorio.alterar(artista);
	}
	public void definirImagemPrincipal(int id, String imagem) throws SQLException, ArtistaNaoCadastradoException, NaoFoiPossivelCadastrarArtistaException, Exception {
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		repositorio.definirImagemPrincipal(id, imagem);
	}
	public void deletar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		repositorio.deletar(id);
	}
	public void ativar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception{
		System.out.println("Passando pela Controladora - concluido com sucesso -");
		repositorio.ativar(id);
	}
	
	
}
