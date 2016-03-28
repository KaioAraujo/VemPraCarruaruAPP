package br.com.vempracaruaru.foto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.vempracaruaru.conexao.Conexao;
import br.com.vempracaruaru.exception.ArtistaNaoCadastradoException;
import br.com.vempracaruaru.exception.FotoJaCadastradoException;
import br.com.vempracaruaru.exception.FotoNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelAlterarFotoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarArtistaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarFotoException;

public class RepositorioFotoBDR implements IRepositorioFoto{

	private  static RepositorioFotoBDR instance;
	public static final String NOME_TABELA = "foto";
	private Connection connection;
	private int dataBase = DataBase.MYSQL;
	
	public static RepositorioFotoBDR getInstance() throws Exception{
		
		if(instance == null){
			instance = new RepositorioFotoBDR();
		}
		
		return instance;
	}
	
	
	public RepositorioFotoBDR()throws Exception {
		this.connection = Conexao.getConexao(dataBase);
	}
	
	@Override
	public Foto cadastrar(Foto foto)
			throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoJaCadastradoException, Exception {
		System.out.println("Chegando ao repositorio");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
	
			sql = "INSERT INTO " + NOME_TABELA + " (id_administrador, id_referencia, referencia, imagem, descricao, ativo) VALUES (?,?,?,?,?,?);";
			if (this.dataBase == DataBase.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setInt(1, foto.getIdAdministrador());
			ps.setInt(2, foto.getIdReferencia());
			ps.setString(3, foto.getReferencia());
			ps.setString(4, foto.getImagem());
			ps.setString(5, foto.getDescricao());
			ps.setString(6, String.valueOf(foto.getAtivo()));
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt(1);
				}
				foto.setId(id);
			} else {
				throw new NaoFoiPossivelCadastrarArtistaException();
			}
			System.out.println("Cadastro concluido com sucesso");
		
		ps.close();
		rs.close();
		
		return foto;
		
	}

	@Override
	public ArrayList<Foto> listarTodos(String complemento) throws SQLException, FotoNaoCadastradoException, Exception {
		System.out.println("Chegando ao repositorio -");
		ArrayList<Foto> fotos = new ArrayList<Foto>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + NOME_TABELA + " ";
		sql += "WHERE id IS NOT NULL ";
		sql += complemento;
		sql += " ORDER BY referencia";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				Foto foto = new Foto(rs.getInt("id"), rs.getInt("id_Administrador"), rs.getInt("id_Referencia"), rs.getString("referencia"),
						rs.getString("imagem"),rs.getString("descricao"),rs.getString("ativo").charAt(0));	
				fotos.add(foto);
			}
		}else{
			throw new ArtistaNaoCadastradoException();
		}
		System.out.println("- Consulta completada com sucesso -");
		ps.close();
		rs.close();
		return fotos;
	
	}

	@Override
	public Foto listarPorId(int id) throws SQLException, FotoNaoCadastradoException, Exception {
		return listarTodos("AND id=" + id).get(0);
		}

	@Override
	public ArrayList<Foto> listarPorReferencia(String referencia, int idReferencia) throws SQLException, FotoNaoCadastradoException, Exception {
		return listarTodos("AND id_referencia = " + idReferencia + " AND referencia = '" + referencia + "'");
		}

	@Override
	public void alterar(Foto foto)
			throws SQLException, NaoFoiPossivelCadastrarFotoException, FotoNaoCadastradoException, Exception {
		
		if (existeId(foto) == false){
				PreparedStatement ps = null;
				String sql = "";
	
				sql = "UPDATE " + NOME_TABELA + " SET referencia=?, imagem=?, descricao=?, ativo=? WHERE id=?;";
				ps = this.connection.prepareStatement(sql);
				ps.setString(1, foto.getReferencia());
				ps.setString(2, foto.getImagem());
				ps.setString(3, foto.getDescricao());
				ps.setString(4, String.valueOf(foto.getAtivo()));
				ps.setInt(5, foto.getId());
				Integer resultado = ps.executeUpdate();
				if (resultado == 0) throw new NaoFoiPossivelAlterarArtistaException();
				ps.close();
			}else{
				throw new NaoFoiPossivelAlterarArtistaException();
			}
				
	}

	@Override
	public void deletar(int id) throws SQLException, FotoNaoCadastradoException, Exception {		
		if(id > 0){
		PreparedStatement ps = null;
		String sql = "";
		sql = "DELETE FROM " + NOME_TABELA + " WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setInt(1, id);
		System.out.println(ps);
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarFotoException();
		ps.close();
		System.out.println("- consulta completada com sucesso -");
	}else{
		throw new FotoNaoCadastradoException();
	}	
}

	@Override
	public boolean existeId(Foto foto) throws SQLException, FotoJaCadastradoException, Exception {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, foto.getId());
		rs = ps.executeQuery();
		if(rs != null){
			resposta = false;
		}
		ps.close();
		rs.close();
		return resposta;
		
	}

}
