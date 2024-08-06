package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {
	Connection connection;
	Statement statement;

	public DB(String dbName) {
		//String dbHost = "dpg-cqm9ma1u0jms73fo9jk0-a.singapore-postgres.render.com";
		String dbHost = "dpg-cqm9ma1u0jms73fo9jk0-a";
        String dbPort = "5432";
        String dbUser = "mydb_mb29_user";
        String dbPassword = "kF71i5sudVRIC3UiGkHqPbvRVAhfGUgT";
		//String url = "jdbc:postgresql://dpg-cqm9ma1u0jms73fo9jk0-a:5432/mydb_mb29";
		
		String jdbcUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			// ステートメントの作成
			statement = connection.createStatement();

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public String getPassWord(String userID) {
		String sql = "SELECT * FROM \"user\" WHERE \"ID\" = '" + userID + "';";
		String passWord = "";
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				passWord = resultSet.getString("PW");
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return passWord;
	}

	public void updateStock(List<Product> productList) {
		try {
			for(Product product : productList) {
				String sql = "UPDATE public.productlist\n"
						+ "	SET \"STOCK\"= \"STOCK\" - 1\n"
						+ "	WHERE \"ID\" = '"+ product.getId() +"';";
				statement.executeUpdate(sql);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Product> getProductList() {
		String sql = "SELECT * FROM \"productlist\" WHERE \"STOCK\" > 0 ORDER BY \"ID\" ASC;";
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String id = resultSet.getString("ID");
				String name = resultSet.getString("NAME");
				int price = resultSet.getInt("PRICE");
				int stock = resultSet.getInt("STOCK");
				productList.add(new Product(id, name, price,stock));
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return productList;
	}

	public void Close() {
		try {
			if (connection != null)
				connection.close();
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
