package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.vo.Jogo;
import modelo.vo.Monstro;

public class JogoDAOMySql extends JogoDAO {

	@Override
	public void alterarJogo(Jogo j) {

		try {
			Connection conexao = Conexao.getConexao();

			String sql = "UPDATE jogo SET qtdSalvo=?, deslocX=?, deslocY=? WHERE id=?";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setInt(1, j.getQtdSalvo());
				prepareStatement.setInt(2, j.getDeslocX());
				prepareStatement.setInt(3, j.getDeslocY());
				prepareStatement.setInt(4, j.getId());

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao alterar jogo");
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
	public void deletarJogo(Jogo j) {
		MonstroDAOMySql monstroDaoMySql = new MonstroDAOMySql();
		int k=0;
		String sql = "";
		if (j.getM().size() > 0) {
			String add = "?";
			for (int i = 0; i < j.getM().size(); i++) {
				monstroDaoMySql.atualizar(j.getM().get(i));
				if (i > 0)
					add += ",?";
			}
			sql = "delete from jogomonstros where Monstro_id not in(" + add
					+ ") and Jogo_id = ?";
		} else {
			sql = "delete from jogomonstros where Jogo_id = ?;";
		}
		try {
			Connection conexao = Conexao.getConexao();

			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);
				if (j.getM().size() > 0) {
					for (k = 0; k < j.getM().size(); k++) {
						prepareStatement.setInt(k+1, j.getM().get(k).getId());
					}
					prepareStatement.setInt(k+1, j.getId());					
				}else{
					prepareStatement.setInt(1, j.getId());
				}

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao deletar jogo");
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
	public void inserirJogo(Jogo j) {
		try {
			Connection conexao = Conexao.getConexao();

			String sql = "insert into jogo (Heroi_id,qtdSalvo,deslocX,deslocY) values (?,?,?,?)";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setInt(1, j.getH().getId());
				prepareStatement.setInt(2, j.getQtdSalvo());
				prepareStatement.setInt(3, j.getDeslocX());
				prepareStatement.setInt(4, j.getDeslocY());

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao inserir jogo");
				} else {
					prepareStatement.close();
				}
			} else
				System.err.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int ultimoJogo() {
		String sql = "select MAX(id) from jogo";
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
		System.out.println("Ultimo Jogo:"+num);
		return num;
	}

	public void addMonstrosJogo(int id, int idMonstro) {
		try {
			Connection conexao = Conexao.getConexao();

			String sql = "insert into jogomonstros (Jogo_id,Monstro_id) values (?,?)";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setInt(1, id);
				prepareStatement.setInt(2, idMonstro);

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao inserir jogomonstro");
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
	public Jogo buscarJogo(int id) {
		Jogo j = null;
		HeroiDAOMySql heroiDaoMySql = new HeroiDAOMySql();
		String sql = "select * from jogo where Heroi_id=?";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				ResultSet rs = prepareStatement.executeQuery();
				if (rs.next()) {
					j = new Jogo();
					j.setId(rs.getInt("id"));
					j.setH(heroiDaoMySql.buscarHeroi(rs.getInt("Heroi_id")));
					j.setM(buscarJogoMonstros(j.getId()));
					j.setQtdSalvo(rs.getInt("qtdSalvo"));
					j.setDeslocX(rs.getInt("deslocX"));
					j.setDeslocY(rs.getInt("deslocY"));
				}
				rs.close();
				prepareStatement.close();
				return j;
			} else
				System.out.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return j;
	}

	public List<Monstro> buscarJogoMonstros(int id) {
		List<Monstro> lista = new ArrayList<Monstro>();
		Monstro m = null;
		MonstroDAOMySql monstroDaoMySql = new MonstroDAOMySql();
		String sql = "select Monstro_id from jogomonstros where Jogo_id=?";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				ResultSet rs = prepareStatement.executeQuery();
				while (rs.next()) {
					m = monstroDaoMySql.buscarMonstro(rs.getInt(1));
					lista.add(m);
				}
				rs.close();
				prepareStatement.close();
				return lista;
			} else
				System.out.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

}
