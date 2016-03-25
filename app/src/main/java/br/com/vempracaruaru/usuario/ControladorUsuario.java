package br.com.vempracaruaru.usuario;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.exception.BusinessException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarUsuarioException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarUsuarioException;
import br.com.vempracaruaru.exception.UsuarioJaCadastradoException;
import br.com.vempracaruaru.exception.UsuarioNaoCadastradoException;

public class ControladorUsuario {

	private IRepositorioUsuario repositorio;

	public ControladorUsuario()throws Exception{
		this.repositorio = new RepositorioUsuarioBDR();
	}
	public void cadastrar(Usuario usuario) throws SQLException, NaoFoiPossivelCadastrarUsuarioException, UsuarioJaCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		if(usuario !=null){
			repositorio.cadastrar(usuario);
		}
	}
	public ArrayList<Usuario> listarTodos(String complemento) throws SQLException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		return repositorio.listarTodos(complemento);
	}
	public Usuario listarPorId(int id) throws SQLException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		return repositorio.listarPorId(id);
	}
	public Usuario listarPorIdFacebook(String idFacebook, String email) throws SQLException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		return repositorio.listarPorIdFacebook(idFacebook, email);
	}
	public Usuario listarPorEmail(String email) throws SQLException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		return repositorio.listarPorEmail(email);
	}
	public ArrayList<Usuario> listarPorNome(String nome) throws SQLException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		return repositorio.listarPorNome(nome);
	}
	public Usuario loginSite(String email, String senha) throws SQLException, BusinessException, Exception{
		return repositorio.loginSite(email, senha);
	}
	public void alterar(Usuario usuario) throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
	repositorio.alterar(usuario);
	}
	public void alterarIdFacebook(String idFacebook, String email) throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		repositorio.alterarIdFacebook(idFacebook, email);
	}
	public void alterarSenha(Usuario usuario) throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
	repositorio.alterarSenha(usuario);
	}
	public void deletar(int id) throws SQLException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		repositorio.deletar(id);
	}
	public void ativar(int id) throws SQLException, UsuarioNaoCadastradoException, Exception{
		System.out.println("Passando pela controladora");
		repositorio.ativar(id);
	}
	public boolean existeEmail(String email) throws SQLException, Exception {
		System.out.println("Passando pela controladora");
		return repositorio.existeEmail(email);
	}

}
