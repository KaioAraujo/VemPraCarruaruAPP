package br.com.vempracaruaru.fachada;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.administrador.Administrador;
import br.com.vempracaruaru.administrador.ControladorAdministrador;
import br.com.vempracaruaru.artista.Artista;
import br.com.vempracaruaru.artista.ControladorArtista;
import br.com.vempracaruaru.contato.Contato;
import br.com.vempracaruaru.contato.ControladorContato;
import br.com.vempracaruaru.destaque.ControladorDestaque;
import br.com.vempracaruaru.destaque.Destaque;
import br.com.vempracaruaru.exception.AdministradorJaCadastradoException;
import br.com.vempracaruaru.exception.AdministradorNaoCadastradoException;
import br.com.vempracaruaru.exception.ArtistaJaCadastradoException;
import br.com.vempracaruaru.exception.ArtistaNaoCadastradoException;
import br.com.vempracaruaru.exception.BusinessException;
import br.com.vempracaruaru.exception.ContatoJaCadastradoException;
import br.com.vempracaruaru.exception.ContatoNaoCadastradoException;
import br.com.vempracaruaru.exception.DestaqueJaCadastradoException;
import br.com.vempracaruaru.exception.DestaqueNaoCadastradoException;
import br.com.vempracaruaru.exception.FotoJaCadastradoException;
import br.com.vempracaruaru.exception.FotoNaoCadastradoException;
import br.com.vempracaruaru.exception.ListaJaCadastradoException;
import br.com.vempracaruaru.exception.ListaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarPontoTuristicoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarUsuarioException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarAdministradorException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarContatoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarDestaqueException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarFotoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarListaException;
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

	private ControladorAdministrador controladorAdministrador;
	private ControladorArtista controladorArtista;
	private ControladorUsuario controladorUsuario;
	private ControladorPontoTuristico controladorPontoTuristico;
	private ControladorObra controladorObra;
	private ControladorLista controladorLista;
	private ControladorFoto controladorFoto;
	private ControladorDestaque controladorDestaque;
	private ControladorContato controladorContato;

	public Fachada() throws Exception {
		this.controladorAdministrador = new ControladorAdministrador();
		this.controladorArtista = new ControladorArtista();
		this.controladorUsuario = new ControladorUsuario();
		this.controladorPontoTuristico = new ControladorPontoTuristico();
		this.controladorObra = new ControladorObra();
		this.controladorLista = new ControladorLista();
		this.controladorFoto = new ControladorFoto();
		this.controladorDestaque = new ControladorDestaque();
		this.controladorContato = new ControladorContato();
	}

	public static Fachada getInstance() throws Exception {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}

	// MÉTODOS DO ADMINISTRADOR

	public void administradorCadastrar(Administrador administrador) throws SQLException,
			NaoFoiPossivelCadastrarAdministradorException, AdministradorJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorAdministrador.cadastrar(administrador);
	}

	public ArrayList<Administrador> administradorListarTodos(String complemento)
			throws SQLException, AdministradorNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorAdministrador.listarTodos(complemento);
	}

	public Administrador administradorListarPorId(int id)
			throws SQLException, AdministradorNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorAdministrador.listarPorId(id);
	}

	public ArrayList<Administrador> administradorListarPorNome(String nome)
			throws SQLException, AdministradorNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorAdministrador.listarPorNome(nome);
	}

	public Administrador administradorListarPorCpf(String cpf)
			throws SQLException, AdministradorNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorAdministrador.listarPorCpf(cpf);
	}

	public void administradorAlterar(Administrador administrador) throws SQLException,
			NaoFoiPossivelCadastrarAdministradorException, AdministradorNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorAdministrador.alterar(administrador);
	}

	public void administradorDeletar(int id) throws SQLException, AdministradorNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorAdministrador.deletar(id);
	}
	
	public void administradorAtivar(int id) throws SQLException, AdministradorNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorAdministrador.ativar(id);
	}
	
	public Administrador administradorLogin(String usuario, String senha) throws SQLException, BusinessException, Exception{
		return controladorAdministrador.login(usuario, senha);
	}

	// MÉTODOS DO ARTISTA
	public Artista artistaCadastrar(Artista artista)
			throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorArtista.cadastrar(artista);
	}

	public ArrayList<Artista> artistaListarTodos(String complemento)
			throws SQLException, ArtistaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorArtista.listarTodos(complemento);
	}

	public Artista artistaListarPorId(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorArtista.listarPorId(id);
	}

	public ArrayList<Artista> artistaListarPorNome(String nome)
			throws SQLException, ArtistaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorArtista.listarPorNome(nome);
	}

	public void artistaAlterar(Artista artista)
			throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorArtista.alterar(artista);
	}
	
	public void artistaDefinirImagemPrincipal(int id, String imagem) throws SQLException, ArtistaNaoCadastradoException, NaoFoiPossivelAlterarArtistaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorArtista.definirImagemPrincipal(id, imagem);
	}

	public void artistaDeletar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorArtista.deletar(id);
	}
	
	public void artistaAtivar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorArtista.ativar(id);
	}

	// MÉTODOS DO USUÁRIO

	public void usuarioCadastrar(Usuario usuario)
			throws SQLException, NaoFoiPossivelCadastrarUsuarioException, UsuarioJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorUsuario.cadastrar(usuario);
	}

	public ArrayList<Usuario> usuarioListarTodos(String complemento)
			throws SQLException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorUsuario.listarTodos(complemento);
	}

	public Usuario usuarioListarPorId(int id) throws SQLException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorUsuario.listarPorId(id);
	}
	
	public Usuario usuarioListarPorIdFacebook(String idFacebook, String email) throws SQLException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorUsuario.listarPorIdFacebook(idFacebook, email);
	}
	
	public Usuario usuarioListarPorEmail(String email) throws SQLException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorUsuario.listarPorEmail(email);
	}

	public ArrayList<Usuario> usuarioListarPorNome(String nome)
			throws SQLException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorUsuario.listarPorNome(nome);
	}
	
	public Usuario usuarioLoginSite(String email, String senha) throws SQLException, BusinessException, Exception{
		return controladorUsuario.loginSite(email, senha);
	}

	public void usuarioAlterar(Usuario usuario)
			throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorUsuario.alterar(usuario);
	}
	
	public void usuarioAlterarIdFacebook(String idFacebook, String email) throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorUsuario.alterarIdFacebook(idFacebook, email);
	}
	
	public void usuarioAlterarSenha(Usuario usuario)
			throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorUsuario.alterarSenha(usuario);
	}

	public void usuarioDeletar(int id) throws SQLException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorUsuario.deletar(id);
	}
	
	public void usuarioAtivar(int id) throws SQLException, UsuarioNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorUsuario.ativar(id);
	}
	
	public boolean usuarioExisteEmail(String email) throws SQLException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorUsuario.existeEmail(email);
	}

	// MÉTODOS DO PONTO_TURISTICOS

	public PontoTuristico pontoTuristicoCadastrar(PontoTuristico pontoTurustico) throws SQLException,
			NaoFoiPossivelCadastrarPontoTuristicoException, PontoTuristicoJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorPontoTuristico.cadastrar(pontoTurustico);
	}

	public ArrayList<PontoTuristico> pontoTuristicoListarTodos(String complemento)
			throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorPontoTuristico.listarTodos(complemento);
	}

	public PontoTuristico pontoTuristicoListarPorId(int id)
			throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorPontoTuristico.listarPorId(id);
	}

	public ArrayList<PontoTuristico> pontoTuristicoListarPorNome(String nome)
			throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorPontoTuristico.listarPorNome(nome);
	}

	public void pontoTuristicoAlterar(PontoTuristico pontoTurustico) throws SQLException,
			NaoFoiPossivelCadastrarPontoTuristicoException, PontoTuristicoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorPontoTuristico.alterar(pontoTurustico);
	}
	
	public void pontoTuristicoDefinirImagemPrincipal(int id, String imagem) throws SQLException, PontoTuristicoNaoCadastradoException, NaoFoiPossivelAlterarPontoTuristicoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorPontoTuristico.definirImagemPrincipal(id, imagem);
	}

	public void pontoTuristicoDeletar(int id) throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorPontoTuristico.deletar(id);
	}
	
	public void pontoTuristicoAtivar(int id) throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorPontoTuristico.ativar(id);
	}

	// METODOS DAS OBRAS

	public Obra obraCadastrar(Obra obra)
			throws SQLException, NaoFoiPossivelCadastrarObraException, ObraJaCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorObra.cadastrar(obra);
	}

	public ArrayList<Obra> obraListarTodos(String complemento)
			throws SQLException, ObraNaoCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorObra.listarTodos(complemento);
	}

	public Obra obraListarPorId(int id) throws SQLException, ObraNaoCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorObra.listarPorId(id);
	}

	public ArrayList<Obra> obraListarPorNome(String nome) throws SQLException, ObraNaoCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorObra.listarPorNome(nome);
	}

	public void obraAlterar(Obra obra)
			throws SQLException, NaoFoiPossivelCadastrarObraException, ObraNaoCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorObra.alterar(obra);
	}
	
	public void obraDefinirImagemPrincipal(int id, String imagem) throws SQLException, ArtistaNaoCadastradoException, NaoFoiPossivelCadastrarArtistaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorObra.definirImagemPrincipal(id, imagem);
	}

	public void obraDeletar(int id) throws SQLException, ObraNaoCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorObra.deletar(id);
	}
	
	public void obraAtivar(int id) throws SQLException, ObraNaoCadastradaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorObra.ativar(id);
	}

	// METODOS DAS LISTA

	public void listaCadastrar(Lista lista)
			throws SQLException, ListaJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorLista.cadastrar(lista);
	}

	public ArrayList<Lista> listarTodasListas(String complemento)
			throws SQLException, ListaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorLista.listarTodos(complemento);
	}

	public ArrayList<Lista> listarPorUsuario(int idUsuario, char visitado) throws SQLException, ListaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorLista.listarPorUsuario(idUsuario, visitado);
	}
	
	public Lista listarPorUsuarioPonto(int idUsuario, int idPonto, char visitado) throws SQLException, ListaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorLista.listarPorUsuarioPonto(idUsuario, idPonto, visitado);
	}

	public void ListaAlterar(Lista lista)
			throws SQLException, NaoFoiPossivelCadastrarListaException, ListaNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorLista.alterar(lista);
	}

	public void listaDeletar(int idUsuario, int idPontoTuristico) throws SQLException, NaoFoiPossivelDeletarListaException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorLista.deletar(idUsuario, idPontoTuristico);
	}

	// METODOS DAS FOTO

	public Foto fotoCadastrar(Foto foto)
			throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorFoto.cadastrar(foto);
	}

	public ArrayList<Foto> fotoListarTodos(String complemento)
			throws SQLException, FotoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorFoto.listarTodos(complemento);
	}

	public Foto fotoListarPorId(int id) throws SQLException, FotoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorFoto.listarPorId(id);
	}

	public ArrayList<Foto> fotoListarPorReferencia(String referencia, int idReferencia)
			throws SQLException, FotoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorFoto.listarPorReferencia(referencia, idReferencia);
	}

	public void fotoAlterar(Foto foto)
			throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorFoto.alterar(foto);
	}

	public void fotoDeletar(int id) throws SQLException, FotoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorFoto.deletar(id);
	}

	// Metodos Destaque
	public Destaque destaqueCadastrar(Destaque destaque)
			throws SQLException, NaoFoiPossivelCadastrarDestaqueException, DestaqueJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorDestaque.cadastrar(destaque);
	}

	public ArrayList<Destaque> destaqueListarTodos(String complemento)
			throws SQLException, DestaqueNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorDestaque.listarTodos(complemento);
	}

	public Destaque destaqueListarPorId(int id) throws SQLException, DestaqueNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorDestaque.listarPorId(id);
	}

	public ArrayList<Destaque> destaqueListarPorTitulo(String titulo)
			throws SQLException, DestaqueNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorDestaque.listarPorTitulo(titulo);
	}

	public void destaqueAlterar(Destaque destaque)
			throws SQLException, NaoFoiPossivelCadastrarDestaqueException, DestaqueNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorDestaque.alterar(destaque);

	}

	public void destaqueDeletar(int id) throws SQLException, DestaqueNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorDestaque.deletar(id);
	}

	// Metodos Contato

	public void contatoCadastrar(Contato contato)
			throws SQLException, NaoFoiPossivelCadastrarContatoException, ContatoJaCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorContato.cadastrar(contato);

	}

	public ArrayList<Contato> contatoListarTodos(String complemento)
			throws SQLException, ContatoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorContato.listarTodos(complemento);
	}
	
	public Contato contatoListarPorId(int id)
			throws SQLException, ContatoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		return controladorContato.listarPorId(id);
	}

	public void contatoAlterar(Contato contato)
			throws SQLException, NaoFoiPossivelCadastrarContatoException, ContatoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorContato.alterar(contato);
	}

	public void contatoDeletar(int id) throws SQLException, ContatoNaoCadastradoException, Exception {
		System.out.println("Passando pela fachada - concluido com sucesso -");
		controladorContato.deletar(id);
	}
}
