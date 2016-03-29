package br.com.vempracaruaru.obra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.vempracaruaru.conexao.Conexao;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarObraException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarObraException;
import br.com.vempracaruaru.exception.ObraJaCadastradaException;
import br.com.vempracaruaru.exception.ObraNaoCadastradaException;

public class RepositorioObraBDR implements IRepositorioObra{
	
	private static RepositorioObraBDR instance;
	public static final String NOME_TABELA = "obra";
	private Connection connection;
	private int dataBase = DataBase.MYSQL;
	
	public static RepositorioObraBDR getInstance()throws Exception{
		if(instance == null){
			instance = new RepositorioObraBDR();
		}
		return instance;
	}
	
	public RepositorioObraBDR() throws Exception{
		this.connection = Conexao.getConexao(dataBase);
	}
	
	@Override
	public Obra cadastrar(Obra obra)
			throws SQLException, NaoFoiPossivelCadastrarObraException, ObraJaCadastradaException, Exception {
		System.out.println("Chegando ao repositorio");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
	
			sql = "INSERT INTO " + NOME_TABELA + " (id_administrador, id_artista, id_ponto_turistico, nome, ativo, imagem_principal, descricao) VALUES (?,?,?,?,?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setInt(1, obra.getIdAdministrador());
			ps.setInt(2, obra.getIdArtista());
			ps.setInt(3, obra.getIdPontoTuristico());
			ps.setString(4, obra.getNome());
			ps.setString(5, String.valueOf(obra.getAtivo()));
			ps.setString(6, obra.getFoto());
			ps.setString(7, obra.getDescricao());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt(1);
				}
				obra.setId(id);
			} else {
				throw new NaoFoiPossivelCadastrarObraException();
			}
			System.out.println("Cadastro concluido com sucesso");
		
		ps.close();
		rs.close();	
		
		return obra;
	}

	@Override
	public ArrayList<Obra> listarTodos(String complemento) throws SQLException, ObraNaoCadastradaException, Exception {
		System.out.println("Chegando ao repositorio -");
		ArrayList<Obra> obras = new ArrayList<Obra>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM vw_obra ";
		sql += "WHERE id IS NOT NULL ";
		sql += complemento;
		sql += " ORDER BY nome";
		ps = this.connection.prepareStatement(sql);
		System.out.println(ps);
		rs = ps.executeQuery();

		if (rs != null) {
			System.out.println("QTD LINHAS: " + rs.getRow());
			while (rs.next()) {
				Obra obra = new Obra(rs.getInt("id"), rs.getInt("id_artista"), rs.getString("nome_artista"), rs.getInt("id_administrador"), 
						rs.getString("nome_administrador"), rs.getInt("id_ponto_turistico"), rs.getString("nome_ponto_turistico"),
						rs.getString("nome"), rs.getString("ativo").charAt(0),rs.getString("imagem_principal"), rs.getString("descricao"));
				obras.add(obra);
			}
		} else {
			throw new ObraNaoCadastradaException();
		}
		System.out.println("- Consulta completada com sucesso -");
		ps.close();
		rs.close();
		return obras;
	
	}

	@Override
	public Obra listarPorId(int id) throws SQLException, ObraNaoCadastradaException, Exception {
		return listarTodos("AND id=" + id).get(0);
		}

	@Override
	public ArrayList<Obra> listarPorNome(String nome) throws SQLException, ObraNaoCadastradaException, Exception {
		return listarTodos("AND nome_obra LIKE '%" + nome + "%'");
		}

	@Override
	public void alterar(Obra obra)
			throws SQLException, NaoFoiPossivelCadastrarObraException, ObraNaoCadastradaException, Exception {		
		if (existeId(obra) == false){
				PreparedStatement ps = null;
				String sql = "";
				sql = "UPDATE " + NOME_TABELA + " SET id_administrador=?, id_artista=?, id_ponto_turistico=?, nome=?, descricao=? WHERE id=?;";
				ps = this.connection.prepareStatement(sql);
				ps.setInt(1, obra.getIdAdministrador());
				ps.setInt(2, obra.getIdArtista());
				ps.setInt(3, obra.getIdPontoTuristico());
				ps.setString(4, obra.getNome());
				ps.setString(5, obra.getDescricao());
				ps.setInt(6, obra.getId());
				Integer resultado = ps.executeUpdate();
				if (resultado == 0) throw new NaoFoiPossivelAlterarObraException();
				ps.close();
			}else{
				throw new NaoFoiPossivelAlterarObraException();
			}
				
	}
	
	@Override
	public void definirImagemPrincipal(int id, String imagem) throws SQLException, ObraNaoCadastradaException, NaoFoiPossivelCadastrarObraException, Exception {			
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET imagem_principal=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, imagem);
		ps.setInt(2, id);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarObraException();
		ps.close();
	}

	@Override
	public void deletar(int id) throws SQLException, ObraNaoCadastradaException, Exception {			
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, String.valueOf("N"));
		ps.setInt(2, id);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarObraException();
		ps.close();
	
	}
	
	@Override
	public void ativar(int id) throws SQLException, ObraNaoCadastradaException, Exception {			
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, String.valueOf("S"));
		ps.setInt(2, id);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarObraException();
		ps.close();
	
	}

	@Override
	public boolean existeId(Obra obra) throws SQLException, ObraJaCadastradaException, Exception {		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, obra.getId());
		rs = ps.executeQuery();
		if(rs != null){
			resposta = false;
		}
		ps.close();
		rs.close();
		return resposta;
	}

}
