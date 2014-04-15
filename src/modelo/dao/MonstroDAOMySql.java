package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.vo.Monstro;
import modelo.vo.MonstroChefe;
import modelo.vo.MonstroHomem;
import modelo.vo.MonstroMulher;

public class MonstroDAOMySql extends MonstroDAO {

	public Monstro buscarMonstro(int id) {
		Monstro monstro = null;
		String sql = "select * from monstro where id=?";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				ResultSet rs = prepareStatement.executeQuery();
				if (rs.next()) {
					switch(rs.getInt("tipo")){
						case 1:{
							monstro = new MonstroHomem();
							break;
						}
						case 2:{
							monstro = new MonstroMulher();
						}
						case 3:{
							monstro = new MonstroChefe();
						}
						default:{
							
						}
					}
					monstro.setId(rs.getInt("id"));
					monstro.setLife(rs.getInt("life"));
					monstro.setExp(rs.getInt("exp"));
					monstro.setNumQuador(rs.getInt("numQuadro"));
					monstro.setVelocidade(rs.getInt("velocidade"));
					monstro.setPosicaoX(rs.getInt("posicaoX"));
					monstro.setPosicaoY(rs.getInt("posicaoY"));
					monstro.setTamanhoX(rs.getInt("tamanhoX"));
					monstro.setTamanhoY(rs.getInt("tamanhoY"));
					monstro.setItem_id(rs.getInt("Item_id"));

				}
				rs.close();
				prepareStatement.close();
				return monstro;
			} else
				System.out.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return monstro;
	}

	public int ultimoMonstro() {
		String sql = "select MAX(id) from monstro";
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

	@Override
	public void inserir(Monstro m) {
		try {
			Connection conexao = Conexao.getConexao();

			String sql = "insert into monstro (exp, posicaoX, posicaoY, velocidade, numQuadro, life, tamanhoX, tamanhoY, Item_id) values (?,?,?,?,?,?,?,?,?)";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setInt(1, m.getExp());
				prepareStatement.setInt(2, m.getPosicaoX());
				prepareStatement.setInt(3, m.getPosicaoY());
				prepareStatement.setInt(4, m.getVelocidade());
				prepareStatement.setInt(5, m.getNumQuador());
				prepareStatement.setInt(6, m.getLife());
				prepareStatement.setInt(7, m.getTamanhoX());
				prepareStatement.setInt(8, m.getTamanhoY());
				prepareStatement.setInt(9, m.getItem_id());

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao inserir monstro");
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
	public void deletarMonstro() {
		try {
			Connection conexao = Conexao.getConexao();

			String sql = "delete from monstro where id not in(select Monstro_id from jogomonstros)";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao deletar monstros");
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
	public ArrayList<Monstro> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Monstro m) {
		try {
			Connection conexao = Conexao.getConexao();

			String sql = "UPDATE monstro SET posicaoX=?, posicaoY=?, life=? WHERE id=?";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setInt(1, m.getPosicaoX());
				prepareStatement.setInt(2, m.getPosicaoY());
				prepareStatement.setInt(3, m.getLife());
				prepareStatement.setInt(4, m.getId());
				

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					inserir(m);
					System.err.println("Erro ao alterar monstro");
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
}
