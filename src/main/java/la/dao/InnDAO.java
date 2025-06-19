package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.InnBean;

public class InnDAO {
	//url,ユーザー名,パスワードの設定
	private String url = "jdbc:postgresql:innreserve";
	private String user = "student";
	private String pass = "himitu";

	// コンストラクタ
	public InnDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	// 全ての宿情報を取得する
	public List<InnBean> findAllInn(int customer_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM inn";
		if (customer_id == 1) {
			sql += " ORDER BY id";
		} else {
			sql += " WHERE delete_flag = false ORDER BY id";
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得
				List<InnBean> list = new ArrayList<InnBean>();
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					int price = rs.getInt("price");
					Boolean delete_flag = rs.getBoolean("delete_flag");
					InnBean bean = new InnBean(id, name, address, tel, price, delete_flag);
					list.add(bean);
				}
				// 商品一覧をListとして返す
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	//idから宿情報を取得
	public InnBean findInnById(int inn_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM inn WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, inn_id);
			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				InnBean bean;
				// 結果の取得および表示
				if (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					int price = rs.getInt("price");
					Boolean delete_flag = rs.getBoolean("delete_flag");
					bean = new InnBean(id, name, address, tel, price, delete_flag);
				} else {
					return null;
				}
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	//宿の追加
	public int addInn(String name, String address, String tel, int price)
			throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO inn(name,address,tel,price,delete_flag) VALUES(?, ?, ?, ?,false)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// 値のセット
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setInt(4, price);

			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/**
	 * 更新処理
	 */
	public int updateInn(int id, String name, String address, String tel, int price)
			throws DAOException {
		// SQL文の作成
		String sql = "UPDATE inn SET name = ?,address=?,tel=?,price=? WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// 値のセット
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setInt(4, price);
			st.setInt(5, id);

			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/**
	 * 削除処理
	 */
	public int deleteInn(int id) throws DAOException {
		// SQL文の作成
		String sql = "UPDATE inn SET delete_flag = true WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// コードを指定
			st.setInt(1, id);
			// SQLの実行
			int rows = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
		sql = "UPDATE reserve SET cancel_flag = true WHERE inn_id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// コードを指定
			st.setInt(1, id);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}
}
