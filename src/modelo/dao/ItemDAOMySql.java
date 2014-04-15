package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.vo.Item;

public class ItemDAOMySql extends ItemDAO {

	@Override
	public void addItem(int idHeroi, int idItem) {
		try {
			Connection conexao = Conexao.getConexao();

			String sql = "insert into inventario (Heroi_id,Item_id) values (?,?)";
			if (conexao != null) {
				PreparedStatement prepareStatement = conexao
						.prepareStatement(sql);

				prepareStatement.setInt(1, idHeroi);
				prepareStatement.setInt(2, idItem);

				if (prepareStatement.executeUpdate() == 0) {
					prepareStatement.close();
					System.err.println("Erro ao inserir inventario");
				} else {
					prepareStatement.close();
				}
			} else
				System.err.println("IMPOSSIVEL CONECTAR");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public List<Item> buscarInventario(int id) {
		ArrayList<Item> listaItem = new ArrayList<Item>();
		Item item = null;
		String sql = "select Item_id from inventario where Heroi_id = ?";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				ResultSet rs = prepareStatement.executeQuery();
				while (rs.next()) {
					item = buscarItem(rs.getInt("Item_id"));
					listaItem.add(item);
				}
				rs.close();
				prepareStatement.close();
				return listaItem;
			} else {
				System.err.println("Erro ao conectar");
				System.exit(0);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listaItem;
	}

	public Item buscarItem(int id) {
		Item item = null;
		String sql = "select * from item where id=?";
		try {
			Connection conn = Conexao.getConexao();
			if (conn != null) {
				PreparedStatement prepareStatement = conn.prepareStatement(sql);
				prepareStatement.setInt(1, id);
				ResultSet rs = prepareStatement.executeQuery();
				if (rs.next()) {
					item = new Item();
					item.setId(rs.getInt("id"));
					item.setNome(rs.getString("nome"));
					item.setStr(rs.getInt("str"));
					item.setAgi(rs.getInt("agi"));
					item.setDef(rs.getInt("def"));
					item.setJut(rs.getInt("jut"));
					item.setHp(rs.getInt("hp"));
					item.setCh(rs.getInt("ch"));
				}
				rs.close();
				prepareStatement.close();
				return item;
			} else {
				System.err.println("Erro ao conectar");
				System.exit(0);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return item;
	}

}
