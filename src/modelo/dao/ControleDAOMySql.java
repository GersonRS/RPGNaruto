package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.vo.Controle;

public class ControleDAOMySql extends ControleDAO{

	public Controle getControlePorChave(int id){
		Controle c= null;
		String sql = "select * from controle where id=?";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				ResultSet rs = prepareStatement.executeQuery();
				if (rs.next()) {
					c = new Controle();
					c.setCima(rs.getInt("cima"));
					c.setDireita(rs.getInt("direita"));
					c.setBaixo(rs.getInt("baixo"));
					c.setEsquerda(rs.getInt("esquerda"));
					c.setSpaco(rs.getInt("spaco"));
					c.setUm(rs.getInt("um"));
					c.setDois(rs.getInt("dois"));
					c.setTres(rs.getInt("tres"));
					c.setQuatro(rs.getInt("quatro"));
				}
				rs.close();
				prepareStatement.close();
				return c;
			} else
				System.out.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}
	public void inserir(Controle c) {
		try {

			Connection conexao = Conexao.getConexao();

			String sql = "insert into controle (cima, direita, baixo, esquerda, spaco, um, dois, tres, quatro) values (?,?,?,?,?,?,?,?,?)";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setInt(1, c.getCima());
				prepareStatement.setInt(2, c.getDireita());
				prepareStatement.setInt(3, c.getBaixo());
				prepareStatement.setInt(4, c.getEsquerda());
				prepareStatement.setInt(5, c.getSpaco());
				prepareStatement.setInt(6, c.getUm());
				prepareStatement.setInt(7, c.getDois());
				prepareStatement.setInt(8, c.getTres());
				prepareStatement.setInt(9, c.getQuatro());
				

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao inserir");
				} else {
					prepareStatement.close();
					return;
				}
			} else
				System.err.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public int ultimoControle() {
		String sql = "select MAX(id) from controle";
		int num = 0;
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				ResultSet rs = prepareStatement.executeQuery();
				if (rs.next()) {
					num = rs.getInt(1);
				}
				rs.close();
				prepareStatement.close();
				return num;
			} else
				System.out.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return num;
	}
}
