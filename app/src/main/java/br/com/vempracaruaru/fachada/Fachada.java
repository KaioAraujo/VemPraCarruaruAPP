package br.com.vempracaruaru.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.artista.ControladorArtista;
import br.com.vempracaruaru.exception.ArtistaJaCadastradoException;
import br.com.vempracaruaru.exception.ArtistaNaoCadastradoException;
import br.com.vempracaruaru.exception.BusinessException;
import br.com.vempracaruaru.exception.FotoNaoCadastradoException;
import br.com.vempracaruaru.exception.ListaJaCadastradoException;
import br.com.vempracaruaru.exception.ListaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarUsuarioException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarObraException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarPontoTuristicoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarUsuarioException;
import br.com.vempracaruaru.exception.NaoFoiPossivelDeletarListaException;
import br.com.vempracaruaru.exception.ObraJaCadastradaException;
import br.com.vempracaruaru.exception.ObraNaoCadastradaException;
import br.com.vempracaruaru.exception.PontoTuristicoJaCadastradoException;
import br.com.vempracaruaru.exception.PontoTuristicoNaoCadastradoException;
import br.com.vempracaruaru.exception.UsuarioJaCadastradoException;
import br.com.vempracaruaru.exception.UsuarioNaoCadastradoException;
import br.com.vempracaruaru.foto.ControladorFoto;
import br.com.vempracaruaru.foto.Foto;
import br.com.vempracaruaru.lista.ControladorLista;
import br.com.vempracaruaru.lista.Lista;
import br.com.vempracaruaru.obra.ControladorObra;
import br.com.vempracaruaru.obra.Obra;
import br.com.vempracaruaru.pontoturistico.ControladorPontoTuristico;
import br.com.vempracaruaru.pontoturistico.PontoTuristico;
import br.com.vempracaruaru.usuario.ControladorUsuario;
import br.com.vempracaruaru.usuario.Usuario;

public class Fachada {

	private static Fachada instance = null;

	private ControladorArtista controladorArtista;
	private ControladorUsuario controladorUsuario;
	private ControladorPontoTuristico controladorPontoTuristico;
	private ControladorObra controladorObra;
	private ControladorLista controladorLista;
	private ControladorFoto controladorFoto;

	public Fachada() throws Exception {
		this.controladorArtista = new ControladorArtista();
		this.controladorUsuario = new ControladorUsuario();
		this.controladorPontoTuristico = new ControladorPontoTuristico();
		this.controladorObra = new ControladorObra();
		this.controladorLista = new ControladorLista();
		this.controladorFoto = new ControladorFoto();
	}

	public static Fachada getInstance() throws Exception {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}

	// MÉTODOS DO ARTISTA
	public Artista artistaCadastrar(Artista artista)
			throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaJaCadastradoException, Exception {
		return controladorArtista.cadastrar(artista);
	}

	public ArrayList<Artista> artistaListarTodos(String complemento)
			throws SQLException, ArtistaNaoCadastradoException, Exception {
		return controladorArtista.listarTodos(complemento);
	}

	public Artista artistaListarPorId(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {
		return controladorArtista.listarPorId(id);
	}

	public ArrayList<Artista> artistaListarPorNome(String nome)
			throws SQLException, ArtistaNaoCadastradoException, Exception {
		return controladorArtista.listarPorNome(nome);
	}

	public void artistaDeletar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {
		controladorArtista.deletar(id);
	}

	// MÉTODOS DO USUÁRIO
	public void usuarioCadastrar(Usuario usuario)
			throws SQLException, NaoFoiPossivelCadastrarUsuarioException, UsuarioJaCadastradoException, Exception {
		controladorUsuario.cadastrar(usuario);
	}

	public Usuario usuarioListarPorId(int id) throws SQLException, UsuarioNaoCadastradoException, Exception {
		return controladorUsuario.listarPorId(id);
	}

	public Usuario usuarioListarPorIdFacebook(String idFacebook, String email) throws SQLException, UsuarioNaoCadastradoException, Exception {
		return controladorUsuario.listarPorIdFacebook(idFacebook, email);
	}

	public Usuario usuarioListarPorEmail(String email) throws SQLException, UsuarioNaoCadastradoException, Exception {
		return controladorUsuario.listarPorEmail(email);
	}

	public Usuario usuarioLoginSite(String email, String senha) throws SQLException, BusinessException, Exception{
		return controladorUsuario.loginSite(email, senha);
	}

	public void usuarioAlterar(Usuario usuario)
			throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception {
		controladorUsuario.alterar(usuario);
	}

	public void usuarioAlterarIdFacebook(String idFacebook, String email) throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception{
		controladorUsuario.alterarIdFacebook(idFacebook, email);
	}

	public void usuarioAlterarSenha(Usuario usuario)
			throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception {
		controladorUsuario.alterarSenha(usuario);
	}

	public boolean usuarioExisteEmail(String email) throws SQLException, Exception {
		return controladorUsuario.existeEmail(email);
	}

	// MÉTODOS DO PONTO_TURISTICOS
	public PontoTuristico pontoTuristicoCadastrar(PontoTuristico pontoTurustico) throws SQLException,
			NaoFoiPossivelCadastrarPontoTuristicoException, PontoTuristicoJaCadastradoException, Exception {
		return controladorPontoTuristico.cadastrar(pontoTurustico);
	}

	public ArrayList<PontoTuristico> pontoTuristicoListarTodos(String complemento)
			throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		return controladorPontoTuristico.listarTodos(complemento);
	}

	public PontoTuristico pontoTuristicoListarPorId(int id)
			throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		return controladorPontoTuristico.listarPorId(id);
	}

	public ArrayList<PontoTuristico> pontoTuristicoListarPorNome(String nome)
			throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		return controladorPontoTuristico.listarPorNome(nome);
	}

	public void pontoTuristicoAlterar(PontoTuristico pontoTurustico) throws SQLException,
			NaoFoiPossivelCadastrarPontoTuristicoException, PontoTuristicoNaoCadastradoException, Exception {
		controladorPontoTuristico.alterar(pontoTurustico);
	}

	public void pontoTuristicoDeletar(int id) throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		controladorPontoTuristico.deletar(id);
	}

	// METODOS DAS OBRAS
	public Obra obraCadastrar(Obra obra)
			throws SQLException, NaoFoiPossivelCadastrarObraException, ObraJaCadastradaException, Exception {
		return controladorObra.cadastrar(obra);
	}

	public ArrayList<Obra> obraListarTodos(String complemento)
			throws SQLException, ObraNaoCadastradaException, Exception {
		return controladorObra.listarTodos(complemento);
	}

	public Obra obraListarPorId(int id) throws SQLException, ObraNaoCadastradaException, Exception {
		return controladorObra.listarPorId(id);
	}

	public ArrayList<Obra> obraListarPorNome(String nome) throws SQLException, ObraNaoCadastradaException, Exception {
		return controladorObra.listarPorNome(nome);
	}

	public void obraDeletar(int id) throws SQLException, ObraNaoCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorObra.deletar(id);
	}

	// METODOS DAS LISTA
	public void listaCadastrar(Lista lista)
			throws SQLException, ListaJaCadastradoException, Exception {
		controladorLista.cadastrar(lista);
	}

	public ArrayList<Lista> listarPorUsuario(int idUsuario, char visitado) throws SQLException, ListaNaoCadastradoException, Exception {
		return controladorLista.listarPorUsuario(idUsuario, visitado);
	}

	public Lista listarPorUsuarioPonto(int idUsuario, int idPonto, char visitado) throws SQLException, ListaNaoCadastradoException, Exception {
		return controladorLista.listarPorUsuarioPonto(idUsuario, idPonto, visitado);
	}

	public void listaDeletar(int idUsuario, int idPontoTuristico) throws SQLException, NaoFoiPossivelDeletarListaException, Exception {
		controladorLista.deletar(idUsuario, idPontoTuristico);
	}

	// METODOS DAS FOTO
	public Foto fotoListarPorId(int id) throws SQLException, FotoNaoCadastradoException, Exception {
		return controladorFoto.listarPorId(id);
	}

	public ArrayList<Foto> fotoListarPorReferencia(String referencia, int idReferencia)
			throws SQLException, FotoNaoCadastradoException, Exception {
		return controladorFoto.listarPorReferencia(referencia, idReferencia);
	}

	public void fotoDeletar(int id) throws SQLException, FotoNaoCadastradoException, Exception {
		controladorFoto.deletar(id);
	}

}