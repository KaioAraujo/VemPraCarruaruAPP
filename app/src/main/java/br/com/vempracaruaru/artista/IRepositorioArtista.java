package br.com.vempracaruaru.artista;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.ArtistaJaCadastradoException;
import br.com.vempracaruaru.exception.ArtistaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarArtistaException;

public interface IRepositorioArtista {
	
	public Artista cadastrar(Artista artista) throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaJaCadastradoException, Exception;
	public ArrayList<Artista> listarTodos(String complemento) throws SQLException, ArtistaNaoCadastradoException, Exception;
	public Artista listarPorId(int id) throws SQLException, ArtistaNaoCadastradoException, Exception;
	public ArrayList<Artista> listarPorNome(String nome) throws SQLException, ArtistaNaoCadastradoException, Exception;
	public void alterar(Artista artista) throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaNaoCadastradoException, Exception;
	public void definirImagemPrincipal(int id, String imagem) throws SQLException, ArtistaNaoCadastradoException, NaoFoiPossivelAlterarArtistaException, Exception;
	public void deletar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception;
	public void ativar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception;
	public boolean existeId(Artista artista) throws SQLException, ArtistaJaCadastradoException, Exception;
}
