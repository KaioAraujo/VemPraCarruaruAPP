package br.com.vempracaruaru.lista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.vempracaruaru.conexao.Conexao;
import br.com.vempracaruaru.conexao.DataBase;
import br.com.vempracaruaru.exception.ListaJaCadastradoException;
import br.com.vempracaruaru.exception.ListaNaoCadastradoException;
import br.com.vempracaruaru.exception.NaoFoiPossivelCadastrarListaException;
import br.com.vempracaruaru.exception.NaoFoiPossivelDeletarListaException;

public class RepositorioListaBDR implements IRepositorioLista{
	/*
	 * pricisamos criar o metodo que vai recuperar e controlar os pontos dos usuarios
	 */
	
	
	private static RepositorioListaBDR instance;
	public static final String NOME_TABELA = "lista_ponto";
	public static final String VIEW_TABELA = "vw_lista_ponto";
	private Connection connection;
	private int dataBase = DataBase.MYSQL;
	
	public static RepositorioListaBDR getInstance() throws Exception {
		
		if(instance == null){
			instance =  new RepositorioListaBDR();
		}
		return instance;
	}
	
	public RepositorioListaBDR() throws Exception {
		this.connection = Conexao.getConexao(dataBase);
	}
	
	
	@Override
	public void cadastrar(Lista lista)
			throws SQLException, ListaJaCadastradoException, Exception {
		System.out.println("Chegando ao repositorio LIST");
		System.out.println(existeId(lista));
		if (existeId(lista) == false) {
			PreparedStatement ps = null;
			String sql = "";
			sql = "INSERT INTO " + NOME_TABELA + " (id_usuario, id_ponto_turistico) VALUES (?,?);";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, lista.getIdUsuario());
			ps.setInt(2, lista.getIdPontoTuristico());
			System.out.println(ps);
			int execucao = ps.executeUpdate();
			if (execucao == 0) throw new ListaJaCadastradoException();
			ps.close();
		} else {
			throw new ListaJaCadastradoException();
		}
		System.out.println("Cadastro concluido com sucesso");
	}

	@Override
	public ArrayList<Lista> listarTodos(String complemento)
			throws SQLException, ListaNaoCadastradoException, Exception {
		System.out.println("Chegando ao repositorio -");
		ArrayList<Lista> listas = new ArrayList<Lista>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "SELECT * FROM " + VIEW_TABELA + " ";
		sql += "WHERE ";
		sql += complemento;
		sql += " ORDER by data_hora DESC";
		ps = this.connection.prepareStatement(sql);
		System.out.println(ps);
		rs = ps.executeQuery();
//		int contador = 0;
		while (rs.next()) {
//			contador++;
			Lista lista = new Lista(rs.getInt("id_usuario"), rs.getString("nome_usuario"), rs.getInt("id_ponto_turistico"), rs.getString("nome_ponto"), rs.getString("data_hora"), rs.getString("visitado").charAt(0));
			listas.add(lista);
		}
//		if (contador == 0) throw new ObraNaoCadastradaException();
		
		System.out.println("- Consulta completada com sucesso -");
		ps.close();
		rs.close();
		return listas;
	
	}
	
	@Override
	public ArrayList<Lista> listarPorUsuario(int idUsuario, char visitado) throws SQLException, ListaNaoCadastradoException, Exception {
		return listarTodos("id_usuario=" + idUsuario + " AND visitado='"+ visitado +"'");
	}
	
	@Override
	public Lista listarPorUsuarioPonto(int idUsuario, int idPonto, char visitado) throws SQLException, ListaNaoCadastradoException, Exception {
		return listarTodos("id_usuario=" + idUsuario + " AND id_ponto_turistico= " + idPonto + " AND visitado='"+ visitado +"'").get(0);
	}

	@Override
	public void alterar(Lista lista)
			throws SQLException, NaoFoiPossivelCadastrarListaException, ListaNaoCadastradoException, Exception {
		
	}

	@Override
	public void deletar(int idUsuario, int idPontoTuristico) throws SQLException, NaoFoiPossivelDeletarListaException, Exception {
		System.out.println("chegando ao repositorio");
		PreparedStatement stmt = null;
		String sql = "";
		if(existeId(new Lista(idUsuario, idPontoTuristico, "", 'N'))){
			sql = "delete from "+ NOME_TABELA +" where id_usuario=? AND id_ponto_turistico=? AND visitado='N'";
			stmt = this.connection.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			stmt.setInt(2, idPontoTuristico);
			stmt.execute();
			System.out.println("foi removido");
		}else{
			throw new NaoFoiPossivelDeletarListaException();
		}
	}

	@Override
	public boolean existeId(Lista lista) throws SQLException, ListaJaCadastradoException, Exception {		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id_usuario=? AND id_ponto_turistico=? AND visitado='N'";
		boolean resposta = false;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, lista.getIdUsuario());
		ps.setInt(2, lista.getIdPontoTuristico());
		rs = ps.executeQuery();
		if(rs.next()){
			resposta = true;
		}
		ps.close();
		rs.close();
		return resposta;		
	}
	
}
