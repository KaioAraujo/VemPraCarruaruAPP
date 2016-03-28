package br.com.vempracaruaru.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import br.com.vempracaruaru.conexao.Conexao;
import br.com.vempracaruaru.exception.BusinessException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarUsuarioException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarUsuarioException;
import br.com.vempracaruaru.exception.UsuarioJaCadastradoException;
import br.com.vempracaruaru.exception.UsuarioNaoCadastradoException;

public class RepositorioUsuarioBDR implements IRepositorioUsuario{

	private static RepositorioUsuarioBDR instance;
	public static final String NOME_TABELA = "usuario";
	private Connection connection;
	private int dataBase = DataBase.MYSQL;
	
	public static RepositorioUsuarioBDR getInstace() throws Exception{
		if(instance != null){
			instance = new RepositorioUsuarioBDR();
		}
		return instance;
	}
	
	public RepositorioUsuarioBDR() throws Exception{
		this.connection = Conexao.getConexao(dataBase);
		
	}
	
	@Override
	public void cadastrar(Usuario usuario)
			throws SQLException, NaoFoiPossivelCadastrarUsuarioException, UsuarioJaCadastradoException, Exception {
		System.out.println("Chegando ao repositorio");
		if (existeEmail(usuario.getEmail()) == false){
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
		
				sql = "INSERT INTO " + NOME_TABELA + " (email, nome, localizacao, senha, user_facebook, link_facebook, ativo) VALUES (?,?,?,password(?),?,?,?);";
				if (this.dataBase == DataBase.ORACLE) {
					ps = this.connection.prepareStatement(sql, new String[] { "id" });
				} else {
					ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				}
				ps.setString(1, usuario.getEmail());
				ps.setString(2, usuario.getNome());
				ps.setString(3, usuario.getLocalizacao());
				ps.setString(4, usuario.getSenha());
				ps.setString(5, usuario.getUserFacebook());
				ps.setString(6, usuario.getLinkfacebook());
				ps.setString(7, String.valueOf(usuario.getAtivo()));
				ps.execute();
				rs = ps.getGeneratedKeys();
				int id = 0;
				if (rs != null) {
					while (rs.next()) {
						id = rs.getInt(1);
					}
					usuario.setId(id);
					System.out.println("cadastro concluido com sucesso");
				} else {
					throw new NaoFoiPossivelCadastrarUsuarioException();
				}
						
			ps.close();
			rs.close();	
		} else {
			throw new NaoFoiPossivelCadastrarUsuarioException("Email j� cadastrado no sistema!!!");
		}
		
	}

	@Override
	public ArrayList<Usuario> listarTodos(String complemento)
			throws SQLException, UsuarioNaoCadastradoException, Exception {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA;
		sql += " WHERE id IS NOT NULL ";
		sql += complemento;
		sql += " ORDER BY nome DESC;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"),rs.getString("email"), rs.getString("localizacao"), rs.getString("senha"),
						rs.getString("user_facebook"),rs.getString("link_facebook"), rs.getInt("pontos"), rs.getString("ativo").charAt(0));
				usuarios.add(usuario);
			}
		}else{
			throw new UsuarioNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return usuarios;
	}
	
	@Override
	public Usuario loginSite(String email, String senha) throws SQLException, BusinessException, Exception {

		Usuario usuario = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " WHERE email=? AND senha=password(?) AND ativo='S'";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, senha);
		System.out.println("QUERY DO LOGIN: " + ps);
		rs = ps.executeQuery();
		if (rs != null) {
			int qtdLinhas = 0;
			while (rs.next()) {
				qtdLinhas++;
				usuario = new Usuario(rs.getInt("id"), rs.getString("nome"),rs.getString("email"), rs.getString("localizacao"), rs.getString("senha"),
						rs.getString("user_facebook"),rs.getString("link_facebook"), rs.getInt("pontos"), rs.getString("ativo").charAt(0));
			}
			if (qtdLinhas == 0) {
				throw new BusinessException("Login inv�lido!");
			}
		}
		ps.close();
		rs.close();
		return usuario;
	}
	
	@Override
	public Usuario listarPorId(int id) throws SQLException, UsuarioNaoCadastradoException, Exception {
		return listarTodos("AND id=" + id).get(0);
	}
	
	@Override
	public Usuario listarPorIdFacebook(String idFacebook, String email) throws SQLException, UsuarioNaoCadastradoException, Exception {
		ArrayList<Usuario> usuarios = listarTodos("AND email='" + email + "' AND user_facebook='" + idFacebook + "'");
		Usuario usuario = null;
		if (usuarios.size() > 0) {
			usuario = usuarios.get(0);
		}
		return usuario;
	}
	
	@Override
	public Usuario listarPorEmail(String email) throws SQLException, UsuarioNaoCadastradoException, Exception {
		ArrayList<Usuario> usuarios = listarTodos("AND email=" + email);
		Usuario usuario = null;
		if (usuarios.size() > 0) {
			usuario = usuarios.get(0);
		}
		return usuario;
	}

	@Override
	public ArrayList<Usuario> listarPorNome(String nome) throws SQLException, UsuarioNaoCadastradoException, Exception {
		return listarTodos("AND nome LIKE '%" + nome + "%'");
		}

	@Override
	public void alterar(Usuario usuario)
			throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception {
		
		if (existeId(usuario.getId()) == false){
				PreparedStatement ps = null;
				String sql = "";
				sql = "UPDATE " + NOME_TABELA + " SET email=?, nome=?, localizacao=?, senha=password(?), user_facebook=?, link_facebook=?, ativo=? WHERE id=?;";
				ps = this.connection.prepareStatement(sql);
				ps.setString(1, usuario.getEmail());
				ps.setString(2, usuario.getNome());
				ps.setString(3, usuario.getLocalizacao());
				ps.setString(4, usuario.getSenha());
				ps.setString(5, usuario.getUserFacebook());
				ps.setString(6, usuario.getLinkfacebook());
				ps.setString(7, String.valueOf(usuario.getAtivo()));
				ps.setInt(8, usuario.getId());
				Integer resultado = ps.executeUpdate();
				if (resultado == 0) throw new NaoFoiPossivelAlterarUsuarioException();
				ps.close();
			}else{
				throw new NaoFoiPossivelAlterarUsuarioException();
			}
		
	}
	
	@Override
	public void alterarIdFacebook(String idFacebook, String email) throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception {
		
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET user_facebook=? WHERE email=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, idFacebook);
		ps.setString(2, email);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarUsuarioException();
		ps.close();
	}
	
	@Override
	public void alterarSenha(Usuario usuario)
			throws SQLException, NaoFoiPossivelAlterarUsuarioException, UsuarioNaoCadastradoException, Exception {
		
		if (existeId(usuario.getId()) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET senha=password(?) WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, usuario.getSenha());
			ps.setInt(2, usuario.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarUsuarioException();
			ps.close();
		}else{
			throw new NaoFoiPossivelAlterarUsuarioException();
		}
	}

	@Override
	public void deletar(int id) throws SQLException, UsuarioNaoCadastradoException, Exception {
		
		Usuario usuario = new Usuario(id, "", "", "", "", "", "", 0,'N');
		if (existeId(usuario.getId()) == false){
			PreparedStatement ps = null;
			String sql = "";
			// instru��o de update do usuario
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(usuario.getAtivo()));
			ps.setInt(2, usuario.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarUsuarioException();
			ps.close();
		}else{
			throw new UsuarioNaoCadastradoException();
		}
		
	}
	
	@Override
	public void ativar(int id) throws SQLException, UsuarioNaoCadastradoException, Exception {
		
		Usuario usuario = new Usuario(id, "", "", "", "", "", "", 0,'S');
		if (existeId(usuario.getId()) == false){
			PreparedStatement ps = null;
			String sql = "";
			// instru��o de update do usuario
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(usuario.getAtivo()));
			ps.setInt(2, usuario.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarUsuarioException();
			ps.close();
		}else{
			throw new UsuarioNaoCadastradoException();
		}
		
	}

	@Override
	public boolean existeId(int id) throws SQLException, UsuarioJaCadastradoException, Exception {		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = false;
		}
		ps.close();
		rs.close();
		return resposta;		
	}
	
	@Override
	public boolean existeEmail(String email) throws SQLException, Exception {		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE email=?";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;		
	}

}
