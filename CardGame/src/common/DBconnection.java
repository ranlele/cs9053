package common;

import java.sql.*;
public class DBconnection {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:cs9053.db");
			System.out.println("Opened database connection!");
			
			try {
				deleteTable(conn);
			}
			catch(Exception ignored) {
				// do nothing, table not exist
			}
			createTable(conn);
			System.out.println();
			System.out.println("Inserting Data");
			insertUser(conn, "Shuo", 500);
			insertUser(conn, "Jingyu", 800);
			insertUser(conn, "Jingyan", 300);
			System.out.println();
			System.out.println("Displaying database");
			displayDatabase(conn,"Users");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		}

	}

	private static void insertUser(Connection conn, String userName, int balance) throws SQLException {
		String insertSQL = "INSERT INTO Users(userName, balance) VALUES(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(insertSQL);
		pstmt.setString(1, userName);
		pstmt.setInt(2, balance);
		pstmt.executeUpdate();
		
	}

	private static void displayDatabase(Connection conn, String tableName) throws SQLException {
		String seleteSQL = "SELECT * from " + tableName;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(seleteSQL);
		System.out.println("----- " + tableName + "-----");
		while(rs.next()) {
			System.out.print("User: " + rs.getString("userName") + ", ");
			System.out.println(rs.getString("balance"));
		}
	}

	private static void createTable(Connection conn) throws SQLException {
		String createTablesql = "" + "CREATE TABLE Users " +
				"( " +
				"userName varchar(255), " +
				"balance integer " + "); " + "";
		Statement stmt = conn.createStatement();
		stmt.execute(createTablesql);
		
	}

	private static void deleteTable(Connection conn) throws SQLException {
		String deleteTableSQL = "DROP TABLE Users";
		Statement stmt = conn.createStatement();
		stmt.execute(deleteTableSQL);
	}

}
