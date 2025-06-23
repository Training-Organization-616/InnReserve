package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.PlanBean;

public class PlanDAO {
	//url,ユーザー名,パスワードの設定
	private String url = "jdbc:postgresql:innreserve";
	private String user = "student";
	private String pass = "himitu";

	// コンストラクタ
	public PlanDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	// 全ての宿情報を取得する
	public List<PlanBean> findAll() throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM plan ORDER BY id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得
				List<PlanBean> list = new ArrayList<PlanBean>();
				while (rs.next()) {
					int id = rs.getInt("id");
					int inn_id = rs.getInt("inn_id");
					String title = rs.getString("title");
					int max_people = rs.getInt("max_people");
					int price = rs.getInt("price");
					String detail = rs.getString("detail");
					Boolean delete_flag = rs.getBoolean("delete_flag");
					PlanBean bean = new PlanBean(id, inn_id, title, max_people, price, detail, delete_flag);
					list.add(bean);
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	//idから宿情報を取得
	public PlanBean findById(int plan_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM plan WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, plan_id);
			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				PlanBean bean;
				// 結果の取得および表示
				if (rs.next()) {
					int id = rs.getInt("id");
					int inn_id = rs.getInt("inn_id");
					String title = rs.getString("title");
					int max_people = rs.getInt("max_people");
					int price = rs.getInt("price");
					String detail = rs.getString("detail");
					Boolean delete_flag = rs.getBoolean("delete_flag");
					bean = new PlanBean(id, inn_id, title, max_people, price, detail, delete_flag);
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

	public List<PlanBean> findByInnId(int inn_id, int customer_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM plan WHERE inn_id = ?";

		if (customer_id == 1) {
			sql += " ORDER BY id";
		} else {
			sql += " AND delete_flag = false ORDER BY id";
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, inn_id);
			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得
				List<PlanBean> list = new ArrayList<PlanBean>();
				while (rs.next()) {
					int id = rs.getInt("id");
					inn_id = rs.getInt("inn_id");
					String title = rs.getString("title");
					int max_people = rs.getInt("max_people");
					int price = rs.getInt("price");
					String detail = rs.getString("detail");
					Boolean delete_flag = rs.getBoolean("delete_flag");
					PlanBean bean = new PlanBean(id, inn_id, title, max_people, price, detail, delete_flag);
					list.add(bean);
				}
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	} //宿の追加

	public void addPlan(int inn_id, String title, int max_people, int price, String detail)
			throws DAOException {
		// SQL文の作成
		String sql = "INSERT INTO plan(inn_id, title, max_people, price, detail, delete_flag) VALUES(?, ?, ?, ?, ?, false)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// 値のセット
			st.setInt(1, inn_id);
			st.setString(2, title);
			st.setInt(3, max_people);
			st.setInt(4, price);
			st.setString(5, detail);

			// SQLの実行
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/**
	 * 更新処理
	 */
	public void updatePlan(int id, int inn_id, String title, int max_people, int price, String detail)
			throws DAOException {
		// SQL文の作成
		String sql = "UPDATE plan SET inn_id = ?, title=?, max_people=?, price = ?, detail = ? WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			// 値のセット
			st.setInt(1, inn_id);
			st.setString(2, title);
			st.setInt(3, max_people);
			st.setInt(4, price);
			st.setString(5, detail);
			st.setInt(6, id);

			// SQLの実行
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	/**
	 * 削除処理
	 */
	public void deletePlan(int id) throws DAOException {
		// SQL文の作成
		String sql = "UPDATE plan SET delete_flag = true WHERE id = ?";

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
		sql = "UPDATE reserve SET cancel_flag = true WHERE inn_id = ?";

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

	public void trueDeletePlan(int id) throws DAOException {
		String sql = "DELETE FROM reserve WHERE plan_id = ?";

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
		sql = "DELETE FROM plan WHERE id = ?";

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
}
