package br.com.vempracaruaru.pontoturistico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.vempracaruaru.conexao.Conexao;
import br.com.vempracaruaru.conexao.DataBase;
import br.com.vempracaruaru.exception.AdministradorNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarPontoTuristicoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarPontoTuristicoException;
import br.com.vempracaruaru.exception.PontoTuristicoJaCadastradoException;
import br.com.vempracaruaru.exception.PontoTuristicoNaoCadastradoException;

public class RepositorioPontoTuristicoBDR implements IRepositorioPontoTuristico{

	private static RepositorioPontoTuristicoBDR instance;
	private static final String NOME_TABELA = "ponto_turistico";
	private Connection  connection;
	private int dataBase = DataBase.MYSQL;

	
	public static RepositorioPontoTuristicoBDR getInstace() throws Exception{
		if(instance == null){
			instance = new RepositorioPontoTuristicoBDR();
		}
		return instance;
	}
	
	public RepositorioPontoTuristicoBDR() throws Exception{
	this.connection = Conexao.getConexao(dataBase);
	}
	
	
	@Override
	public PontoTuristico cadastrar(PontoTuristico pontoTuristico) throws SQLException,
			NaoFoiPossivelCadastrarPontoTuristicoException, PontoTuristicoJaCadastradoException, Exception {
		System.out.println("Chegando ao repositorio");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
	
			sql = "INSERT INTO " + NOME_TABELA + " (id_administrador, nome, endereco, latitude, longitude, telefone, email, tempo_visitacao, horario_funcionamento, "
					+ "historico_descricao, imagem_principal, ativo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setInt(1, pontoTuristico.getIdAdministrador());
			ps.setString(2, pontoTuristico.getNome());
			ps.setString(3, pontoTuristico.getEndereco());
			ps.setString(4, pontoTuristico.getLatitude());
			ps.setString(5, pontoTuristico.getLongitude());
			ps.setString(6, pontoTuristico.getTelefone());
			ps.setString(7, pontoTuristico.getEmail());
			ps.setString(8, pontoTuristico.getTempoVisitacao());
			ps.setString(9, pontoTuristico.getHorarioFuncionamento());
			ps.setString(10, pontoTuristico.getHistoricoDescricao());
			ps.setString(11, pontoTuristico.getFoto());
			ps.setString(12, String.valueOf(pontoTuristico.getAtivo()));
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt(1);
				}
				pontoTuristico.setId(id);
			} else {
				throw new NaoFoiPossivelCadastrarPontoTuristicoException();
			}
			System.out.println("Cadastro concluido com sucesso");
		
		ps.close();
		rs.close();
		
		return pontoTuristico;
	}
	@Override
	public ArrayList<PontoTuristico> listarTodos(String complemento)
			throws SQLException, PontoTuristicoJaCadastradoException, Exception {
		System.out.println("Chegando ao repositorio");
		ArrayList<PontoTuristico> pontosTuristicos = new ArrayList<PontoTuristico>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM vw_ponto_turistico ";
		sql += "WHERE id IS NOT NULL ";
		sql += complemento;
		sql += " ORDER BY nome";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				PontoTuristico pontoTuristico = new PontoTuristico(rs.getInt("id"), rs.getInt("id_administrador"),
				rs.getString("nome_administrador"),rs.getString("nome"), rs.getString("endereco"), rs.getString("latitude"), rs.getString("longitude"),
				rs.getString("telefone"), rs.getString("email"), rs.getString("tempo_visitacao"), rs.getString("horario_funcionamento"),
				rs.getString("historico_descricao"), rs.getString("imagem_principal"), rs.getString("ativo").charAt(0), rs.getInt("quantidade_obras"));
				
				pontosTuristicos.add(pontoTuristico);
			}
			System.out.println("- consulta completada com sucesso -");
		}else{
			throw new AdministradorNaoCadastradoException();
		}
		ps.close();
		rs.close();
		return pontosTuristicos;
		
	}
	@Override
	public PontoTuristico listarPorId(int id) throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		return listarTodos("AND id=" + id).get(0);
		}
	@Override
	public ArrayList<PontoTuristico> listarPorNome(String nome)
			throws SQLException, PontoTuristicoNaoCadastradoException, Exception {
		return listarTodos("AND nome LIKE '%" + nome + "%'");
		}
	@Override
	public void alterar(PontoTuristico pontoTuristico) throws SQLException,
			NaoFoiPossivelCadastrarPontoTuristicoException, PontoTuristicoNaoCadastradoException, Exception {
		
		if (existeId(pontoTuristico) == false){
				PreparedStatement ps = null;
				String sql = "";
				sql = "UPDATE " + NOME_TABELA + " SET id_administrador=?, nome=?, endereco=?, latitude=?, longitude=?, telefone=?, email=?, tempo_visitacao=?, "
						+ "horario_funcionamento=?, historico_descricao=? WHERE id=?;";
				ps = this.connection.prepareStatement(sql);
				ps.setInt(1, pontoTuristico.getIdAdministrador());
				ps.setString(2, pontoTuristico.getNome());
				ps.setString(3, pontoTuristico.getEndereco());
				ps.setString(4, pontoTuristico.getLatitude());
				ps.setString(5, pontoTuristico.getLongitude());
				ps.setString(6, pontoTuristico.getTelefone());
				ps.setString(7, pontoTuristico.getEmail());
				ps.setString(8, pontoTuristico.getTempoVisitacao());
				ps.setString(9, pontoTuristico.getHorarioFuncionamento());
				ps.setString(10, pontoTuristico.getHistoricoDescricao());
				ps.setInt(11, pontoTuristico.getId());

				Integer resultado = ps.executeUpdate();
				if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
				ps.close();
			}else{
				throw new NaoFoiPossivelAlterarArtistaException();
			}
				
	}
	
	@Override
	public void definirImagemPrincipal(int id, String imagem) throws SQLException, PontoTuristicoNaoCadastradoException, NaoFoiPossivelAlterarPontoTuristicoException, Exception {			
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET imagem_principal=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, imagem);
		ps.setInt(2, id);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarPontoTuristicoException();
		ps.close();
	}
	
	@Override
	public void deletar(int id) throws SQLException, PontoTuristicoNaoCadastradoException, Exception {			
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, String.valueOf("N"));
		ps.setInt(2, id);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
		ps.close();

	
	}
	
	@Override
	public void ativar(int id) throws SQLException, PontoTuristicoNaoCadastradoException, Exception {			
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, String.valueOf("S"));
		ps.setInt(2, id);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
		ps.close();

	
	}
	
	@Override
	public boolean existeId(PontoTuristico pontoTuristico)
			throws SQLException, PontoTuristicoJaCadastradoException, Exception {		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, pontoTuristico.getId());
		rs = ps.executeQuery();
		if(rs != null){
			resposta = false;
		}
		ps.close();
		rs.close();
		return resposta;
		
	}
	
	@SuppressWarnings("unused")
	private int recuperarPontos(int id){
		return id;
	}
	
	@SuppressWarnings("unused")
	private void curtir(int id){
		
	}

}
