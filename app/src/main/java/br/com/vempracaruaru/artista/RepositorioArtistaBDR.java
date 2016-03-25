package br.com.vempracaruaru.artista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.vempracaruaru.conexao.Conexao;
import br.com.vempracaruaru.conexao.DataBase;
import br.com.vempracaruaru.exception.ArtistaJaCadastradoException;
import br.com.vempracaruaru.exception.ArtistaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarArtistaException;

public class RepositorioArtistaBDR  implements IRepositorioArtista{
	
	private static RepositorioArtistaBDR instance;
	public static final String NOME_TABELA = "artista";
	private Connection connection;
	private int dataBase = DataBase.MYSQL;
	
	public static RepositorioArtistaBDR getInstance() throws Exception{
		
		if(instance == null){
			instance = new RepositorioArtistaBDR();
		}
		return instance;
	}

	public RepositorioArtistaBDR()throws Exception{
		this.connection = Conexao.getConexao(dataBase);
	}
	
	@Override
	public Artista cadastrar(Artista artista) throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaJaCadastradoException, Exception {
		System.out.println("Chegando ao repositorio");
		
		if (existeNome(artista.getNome())){
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
		
				sql = "INSERT INTO " + NOME_TABELA + " (id_administrador, nome, historico,imagem_principal,"
						+ " telefone, email, twitter, instagram, facebook, tipo, ativo"
						+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				if (this.dataBase == DataBase.ORACLE) {
					ps = this.connection.prepareStatement(sql, new String[] { "id" });
				} else {
					ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				}
				ps.setInt(1, artista.getIdAdministrador());
				ps.setString(2, artista.getNome());
				ps.setString(3, artista.getHistorico());	
				ps.setString(4, artista.getFoto());
				ps.setString(5, artista.getTelefone());
				ps.setString(6, artista.getEmail());
				ps.setString(7, artista.getTwitter());
				ps.setString(8, artista.getInstagram());
				ps.setString(9, artista.getFacebook());			
				ps.setString(10, artista.getTipo());
				ps.setString(11, String.valueOf(artista.getAtivo()));
				ps.execute();
				rs = ps.getGeneratedKeys();
				int id = 0;
				if (rs != null) {
					while (rs.next()) {
						id = rs.getInt(1);
					}
					artista.setId(id);
				} else {
					throw new NaoFoiPossivelCadastrarArtistaException();
				}
				System.out.println("Cadastro concluido com sucesso");
			
			ps.close();
			rs.close();
		} else {
			throw new NaoFoiPossivelCadastrarArtistaException("Não foi possível efetuar o cadastro, pois o Artista já está cadastrado no sistema.");
		}
		
		return artista;
		
	}

	@Override
	public ArrayList<Artista> listarTodos(String complemento)
			throws SQLException, ArtistaNaoCadastradoException, Exception {
		System.out.println("Chegando ao repositorio -");
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM vw_artista ";
		sql += "WHERE id IS NOT NULL ";
		sql += complemento;
		sql += " ORDER BY nome_artista";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				Artista artista = new Artista(	rs.getInt("id"), rs.getString("nome_artista"), 
						rs.getInt("id_administrador"), rs.getString("nome_administrador"),
						 rs.getString("historico"), rs.getString("tipo"), rs.getString("imagem_principal"),
						  rs.getString("telefone"), rs.getString("email"), rs.getString("twitter"), rs.getString("instagram"),
						  rs.getString("facebook"),rs.getString("ativo").charAt(0));
				
				artistas.add(artista);
			}
		}else{
			throw new ArtistaNaoCadastradoException();
		}
		System.out.println("- Consulta completada com sucesso -");
		ps.close();
		rs.close();
		return artistas;
	
	}

	@Override
	public Artista listarPorId(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {
		return listarTodos("AND id=" + id).get(0);
		}

	@Override
	public ArrayList<Artista> listarPorNome(String nome) throws SQLException, ArtistaNaoCadastradoException, Exception {
		return listarTodos("AND nome_artista LIKE '%" + nome + "%'");
		}


	@Override
	public void alterar(Artista artista)
			throws SQLException, NaoFoiPossivelCadastrarArtistaException, ArtistaNaoCadastradoException, Exception {
		
		if (existeId(artista) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET nome=?, historico=?, tipo=?, telefone=?, email=?, twitter=?, instagram=?, facebook=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, artista.getNome());
			ps.setString(2, artista.getHistorico());
			ps.setString(3, artista.getTipo());
			ps.setString(4, artista.getTelefone());
			ps.setString(5, artista.getEmail());
			ps.setString(6, artista.getTwitter());
			ps.setString(7, artista.getInstagram());
			ps.setString(8, artista.getFacebook());
			ps.setInt(9, artista.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
			ps.close();
		}else{
			throw new NaoFoiPossivelAlterarArtistaException();
		}
				
	}
	
	@Override
	public void definirImagemPrincipal(int id, String imagem) throws SQLException, ArtistaNaoCadastradoException, NaoFoiPossivelCadastrarArtistaException, Exception {			
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET imagem_principal=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, imagem);
		ps.setInt(2, id);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
		ps.close();
	}

	@Override
	public void deletar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {		
			Artista artista = new Artista(id, "", 0, "", "", "", "", "", "", "", "", "", 'N');
		
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(artista.getAtivo()));
			ps.setInt(2, artista.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
			ps.close();
	}
	
	@Override
	public void ativar(int id) throws SQLException, ArtistaNaoCadastradoException, Exception {		
			Artista artista = new Artista(id, "", 0, "", "", "", "", "", "", "", "", "", 'S');
		
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(artista.getAtivo()));
			ps.setInt(2, artista.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
			ps.close();
	}

	@Override
	public boolean existeId(Artista artista) throws SQLException, ArtistaJaCadastradoException, Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, artista.getId());
		rs = ps.executeQuery();
		if(rs != null){
			resposta = false;
		}
		ps.close();
		rs.close();
		return resposta;
		
	}
	

	private boolean existeNome(String nome) throws SQLException, ArtistaJaCadastradoException, Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE nome=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setString(1, nome);
		rs = ps.executeQuery();
		if(rs != null){
			resposta = false;
		}
		ps.close();
		rs.close();
		return resposta;
		
	}

}
