package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CustomerBean;

public class CustomerDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:innreserve";
	private String user = "student";
	private String pass = "himitu";

	public CustomerDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public List<CustomerBean> findAll() throws SQLException, DAOException {
		String sql = " select * from customers ORDER BY id";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();) {
			List<CustomerBean> Customer_List = new ArrayList<CustomerBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int point = rs.getInt("point");
				Boolean delete_flag = rs.getBoolean("delete_flag");

				CustomerBean bean = new CustomerBean(id, name, tel, email, password, point, delete_flag);
				Customer_List.add(bean);
			}
			return Customer_List;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	public CustomerBean findByID(int id) throws DAOException {
		String sql = " select * from customers where id = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					id = rs.getInt("id");
					String name = rs.getString("name");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					String password = rs.getString("password");
					int point = rs.getInt("point");
					Boolean delete_flag = rs.getBoolean("delete_flag");

					CustomerBean bean = new CustomerBean(id, name, tel, email, password, point, delete_flag);

					return bean;
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	public CustomerBean findCustomer(String email, String password) throws DAOException {

		String sql = " select * from customers where email = ? and password = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, email);
			st.setString(2, password);

			try (ResultSet rs = st.executeQuery();) {
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String tel = rs.getString("tel");
					email = rs.getString("email");
					password = rs.getString("password");
					int point = rs.getInt("point");
					Boolean delete_flag = rs.getBoolean("delete_flag");

					CustomerBean bean = new CustomerBean(id, name, tel, email, password, point, delete_flag);

					return bean;
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	public void updateCustomer(int id, String name, String tel, String email, String password, int point)
			throws DAOException {

		String sql = "update customers set name = ?, tel = ?, email = ?, password = ?, point = ? where id = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, name);
			st.setString(2, tel);
			st.setString(3, email);
			st.setString(4, password);
			st.setInt(5, point);
			st.setInt(6, id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	public void addCustomer(String name, String tel, String email, String password, int point) throws DAOException {

		String sql = "Insert into customers(name, tel, email, password, point, delete_flag) values(?, ?, ?, ?, ?, false)";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, name);
			st.setString(2, tel);
			st.setString(3, email);
			st.setString(4, password);
			st.setInt(5, point);

			st.executeUpdate();

		} catch (

		SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	public void deleteCustomer(int id) throws DAOException {

		String sql = "UPDATE customers SET email = 'null', delete_flag = true WHERE id = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		sql = "UPDATE reserve SET cancel_flag = true WHERE customer_id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// コードを指定
			st.setInt(1, id);
			// SQLの実行
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public void truedeleteCustomer(int id) throws DAOException {

		String sql = "DELETE FROM reserve WHERE customer_id = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		sql = "DELETE FROM customers WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// コードを指定
			st.setInt(1, id);
			// SQLの実行
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public void trueDeleteCustomer(int id) throws DAOException {
		String sql = "DELETE FROM reserve WHERE customer_id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// コードを指定
			st.setInt(1, id);
			// SQLの実行
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		sql = "DELETE FROM customers WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// コードを指定
			st.setInt(1, id);
			// SQLの実行
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public List<CustomerBean> findByNameAndEmail(String Name, String Email)
			throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM customers WHERE 1 = 1 ";
		// 条件の追加
		if (Name != null && Name.length() != 0) {
			sql += "AND name = ? ";
		}
		if (Email != null && Email.length() != 0) {
			sql += "AND email = ? ";
		}
		sql += "ORDER BY id ";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダの設定
			int i = 0; // カウンタ変数
			if (Name != null && Name.length() != 0) {
				i++;
				st.setString(i, Name);
			}
			if (Email != null && Email.length() != 0) {
				i++;
				st.setString(i, Email);
			}

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<CustomerBean> Customer_List = new ArrayList<CustomerBean>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					String password = rs.getString("password");
					int point = rs.getInt("point");
					Boolean delete_flag = rs.getBoolean("delete_flag");

					CustomerBean bean = new CustomerBean(id, name, tel, email, password, point, delete_flag);
					Customer_List.add(bean);
				}
				// 商品一覧をListとして返す
				return Customer_List;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public void updatePoint(int id, int point) throws DAOException {

		String sql = "update customers set point = ? where id = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, point);
			st.setInt(2, id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}
}
