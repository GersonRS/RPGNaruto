package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.vo.Heroi;

public class HeroiDAOMySql extends HeroiDAO {

	

	public void inserir(Heroi heroi){
		try {
			ControleDAOMySql c = new ControleDAOMySql();
			Connection conexao = Conexao.getConexao();

			String sql = "insert into heroi (nome, tempoDeJogo, life, lifeMax, chaka, chakaMax, exp, expMax, nivel, velocidade, posicaoX, posicaoY, str, strMax, agi, agiMax, def, defMax, jut, jutMax, hp, hpMax, ch, chMax, tamanhoX, tamanhoY, idControle) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				c.inserir(heroi.getControle());
				
				prepareStatement.setString(1, heroi.getNome());
				prepareStatement.setTime(2, heroi.getTempoDeJogo());
				prepareStatement.setInt(3, heroi.getLife());
				prepareStatement.setInt(4, heroi.getLifeMax());
				prepareStatement.setInt(5, heroi.getChaka());
				prepareStatement.setInt(6, heroi.getChakaMax());
				prepareStatement.setInt(7, heroi.getExp());
				prepareStatement.setInt(8, heroi.getExpMax());
				prepareStatement.setInt(9, heroi.getNivel());
				prepareStatement.setInt(10, heroi.getVelocidade());
				prepareStatement.setInt(11, heroi.getPosicaoX());
				prepareStatement.setInt(12, heroi.getPosicaoY());
				prepareStatement.setInt(13, heroi.getStr());
				prepareStatement.setInt(14, heroi.getStrMax());
				prepareStatement.setInt(15, heroi.getAgi());
				prepareStatement.setInt(16, heroi.getAgiMax());
				prepareStatement.setInt(17, heroi.getDef());
				prepareStatement.setInt(18, heroi.getDefMax());
				prepareStatement.setInt(19, heroi.getJut());
				prepareStatement.setInt(20, heroi.getJutMax());
				prepareStatement.setInt(21, heroi.getHp());
				prepareStatement.setInt(22, heroi.getHpMax());
				prepareStatement.setInt(23, heroi.getCh());
				prepareStatement.setInt(24, heroi.getChMax());
				prepareStatement.setInt(25, heroi.getTamanhoX());
				prepareStatement.setInt(26, heroi.getTamanhoY());
				prepareStatement.setInt(27, c.ultimoControle());
				

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao inserir");
				} else {
					prepareStatement.close();
				}
				
				ItemDAOMySql itemDaoMySlq = new ItemDAOMySql();
				int num = ultimoHeroi();
				for (int i = 0; i < heroi.getInventario().size(); i++) {
					itemDaoMySlq.addItem(num, heroi.getInventario().get(i).getId());
				}
			} else
				System.err.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	public int ultimoHeroi(){
		String sql = "select MAX(id) from heroi";
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
		System.out.println("Ultimo heroi: "+num);
		return num;
	}
	

	public Heroi buscarHeroi(int id){
		ItemDAOMySql itemDaoMySql = new ItemDAOMySql();
		Heroi heroi = null;
		String sql = "select * from heroi where id=?";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				ResultSet rs = prepareStatement.executeQuery();
				if (rs.next()) {
					heroi = new Heroi();
					heroi.setId(rs.getInt("id"));
					heroi.setNome(rs.getString("nome"));
					heroi.setTempoDeJogo(rs.getTime("TempoDeJogo"));
					heroi.setLife(rs.getInt("life"));
					heroi.setLifeMax(rs.getInt("lifeMax"));
					heroi.setChaka(rs.getInt("chaka"));
					heroi.setChakaMax(rs.getInt("chakaMax"));
					heroi.setExp(rs.getInt("exp"));
					heroi.setExpMax(rs.getInt("expMax"));
					heroi.setNivel(rs.getInt("nivel"));
					heroi.setVelocidade(rs.getInt("velocidade"));
					heroi.setPosicaoX(rs.getInt("posicaoX"));
					heroi.setPosicaoY(rs.getInt("posicaoY"));
					heroi.setStr(rs.getInt("str"));
					heroi.setStrMax(rs.getInt("strMax"));
					heroi.setAgi(rs.getInt("agi"));
					heroi.setAgiMax(rs.getInt("agiMax"));
					heroi.setDef(rs.getInt("def"));
					heroi.setDefMax(rs.getInt("defMax"));
					heroi.setJut(rs.getInt("jut"));
					heroi.setJutMax(rs.getInt("jutMax"));
					heroi.setHp(rs.getInt("hp"));
					heroi.setHpMax(rs.getInt("hpMax"));
					heroi.setCh(rs.getInt("ch"));
					heroi.setChMax(rs.getInt("chMax"));
					heroi.setTamanhoX(rs.getInt("tamanhoX"));
					heroi.setTamanhoY(rs.getInt("tamanhoY"));
					heroi.setIdControle(rs.getInt("idControle"));
					heroi.setInventario(itemDaoMySql.buscarInventario(heroi.getId()));
				}
				rs.close();
				prepareStatement.close();
				return heroi;
			} else
				System.out.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return heroi;
	}

	public void deletarHeroi(int id) {

	}

	public ArrayList<Heroi> listar(){
		ArrayList<Heroi> listaHeroi = new ArrayList<Heroi>();
		Heroi heroi = null;
		String sql = "select * from heroi;";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				ResultSet rs = prepareStatement.executeQuery();
				while (rs.next()) {
					heroi = new Heroi();
					heroi.setId(rs.getInt("id"));
					heroi.setNome(rs.getString("nome"));
					heroi.setTempoDeJogo(rs.getTime("TempoDeJogo"));
					heroi.setLife(rs.getInt("life"));
					heroi.setLifeMax(rs.getInt("lifeMax"));
					heroi.setChaka(rs.getInt("chaka"));
					heroi.setChakaMax(rs.getInt("chakaMax"));
					heroi.setExp(rs.getInt("exp"));
					heroi.setExpMax(rs.getInt("expMax"));
					heroi.setNivel(rs.getInt("nivel"));
					heroi.setStr(rs.getInt("str"));
					heroi.setAgi(rs.getInt("agi"));
					heroi.setDef(rs.getInt("def"));
					heroi.setJut(rs.getInt("jut"));
					heroi.setHp(rs.getInt("hp"));
					heroi.setCh(rs.getInt("ch"));

					listaHeroi.add(heroi);
				}
				rs.close();
				prepareStatement.close();
				return listaHeroi;
			} else
				System.err.println("imposivel conectar");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listaHeroi;
	}

	public void atualizar(Heroi heroi) {
		try {
			Connection conexao = Conexao.getConexao();

			String sql = "update heroi SET tempoDeJogo=?, life=?, lifeMax=?, chaka=?, chakaMax=?, exp=?, expMax=?, nivel=?, velocidade=?, posicaoX=?, posicaoY=?, str=?, strMax=?, agi=?, agiMax=?, def=?, defMax=?, jut=?, jutMax=?, hp=?, hpMax=?, ch=?, chMax=? WHERE id = ?";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setTime(1, heroi.getTempoDeJogo());
				prepareStatement.setInt(2, heroi.getLife());
				prepareStatement.setInt(3, heroi.getLifeMax());
				prepareStatement.setInt(4, heroi.getChaka());
				prepareStatement.setInt(5, heroi.getChakaMax());
				prepareStatement.setInt(6, heroi.getExp());
				prepareStatement.setInt(7, heroi.getExpMax());
				prepareStatement.setInt(8, heroi.getNivel());
				prepareStatement.setInt(9, heroi.getVelocidade());
				prepareStatement.setInt(10, heroi.getPosicaoX());
				prepareStatement.setInt(11, heroi.getPosicaoY());
				prepareStatement.setInt(12, heroi.getStr());
				prepareStatement.setInt(13, heroi.getStrMax());
				prepareStatement.setInt(14, heroi.getAgi());
				prepareStatement.setInt(15, heroi.getAgiMax());
				prepareStatement.setInt(16, heroi.getDef());
				prepareStatement.setInt(17, heroi.getDefMax());
				prepareStatement.setInt(18, heroi.getJut());
				prepareStatement.setInt(19, heroi.getJutMax());
				prepareStatement.setInt(20, heroi.getHp());
				prepareStatement.setInt(21, heroi.getHpMax());
				prepareStatement.setInt(22, heroi.getCh());
				prepareStatement.setInt(23, heroi.getChMax());
				prepareStatement.setInt(24, heroi.getId());
				
				ItemDAOMySql itemDaoMySql = new ItemDAOMySql();
				for (int i = 0; i < heroi.getInventario().size(); i++) {
					itemDaoMySql.addItem(heroi.getId(), heroi.getInventario().get(i).getId());
				}
				
				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao alterar heroi");
				} else {
					prepareStatement.close();
				}
			} else
				System.err.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		

	}
}
