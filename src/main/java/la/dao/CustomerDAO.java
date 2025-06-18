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
				Boolean delete_flag = rs.getBoolean("delete_flag");

				CustomerBean bean = new CustomerBean(id, name, tel, email, password, delete_flag);
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
					Boolean delete_flag = rs.getBoolean("delete_flag");

					CustomerBean bean = new CustomerBean(id, name, tel, email, password, delete_flag);

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
					Boolean delete_flag = rs.getBoolean("delete_flag");

					CustomerBean bean = new CustomerBean(id, name, tel, email, password, delete_flag);

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

	public void updateCustomer(int id, String name, String tel, String email, String password) throws DAOException {

		String sql = "update customers set name = ?, tel = ?, email = ?, password = ? where id = ?";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, name);
			st.setString(2, tel);
			st.setString(3, email);
			st.setString(4, password);
			st.setInt(5, id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	public void addCustomer(String name, String tel, String email, String password) throws DAOException {

		String sql = "Insert into customers(name, tel, email, password, delete_flag) values(?, ?, ?, ?, false)";

		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql);) {

			st.setString(1, name);
			st.setString(2, tel);
			st.setString(3, email);
			st.setString(4, password);

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
	}

}
