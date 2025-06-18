package la.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ReserveBean;

public class ReserveDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:innreserve";
	private String user = "student";
	private String pass = "himitu";

	/**
	 * コンストラクタ
	 */
	public ReserveDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("ドライバの登録に失敗しました。");
		}
	}

	public ReserveBean findById(int reserve_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM reserve WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, reserve_id);
			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				ReserveBean bean;
				// 結果の取得および表示
				if (rs.next()) {
					int id = rs.getInt("id");
					int customer_id = rs.getInt("customer_id");
					int inn_id = rs.getInt("inn_id");
					int people = rs.getInt("people");
					int stay_days = rs.getInt("stay_days");
					Date first_day = rs.getDate("first_day");
					int total_price = rs.getInt("total_price");
					Boolean cancel_flag = rs.getBoolean("cancel_flag");
					bean = new ReserveBean(id, customer_id, inn_id, people,
							stay_days, first_day, total_price, cancel_flag);
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

	public List<ReserveBean> findByCustomerId(int customer_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM reserve";

		if (customer_id == 1) {
			sql += " ORDER BY id";
		} else {
			sql += " WHERE customer_id = ? AND cancel_flag = false ORDER BY id";
		}

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			if (customer_id != 1) {
				st.setInt(1, customer_id);
			}
			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<ReserveBean> list = new ArrayList<ReserveBean>();
				while (rs.next()) {
					int id = rs.getInt("id");
					int inn_id = rs.getInt("inn_id");
					int people = rs.getInt("people");
					int stay_days = rs.getInt("stay_days");
					Date first_day = rs.getDate("first_day");
					int total_price = rs.getInt("total_price");
					Boolean cancel_flag = rs.getBoolean("cancel_flag");
					ReserveBean bean = new ReserveBean(id, customer_id, inn_id, people,
							stay_days, first_day, total_price, cancel_flag);
					list.add(bean);
				}

				// カテゴリ一覧をListとして返す
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public void addReserve(int customer_id, int inn_id, int people, int stay_days,
			Date first_day, int total_price) throws DAOException {
		int reserveNumber = 0;
		String sql = "SELECT nextval('reserve_id_seq')";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			if (rs.next()) {
				reserveNumber = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} // SQL文の作成
		sql = "INSERT INTO reserve VALUES(?, ?, ?, ?, ?, ?, ?, false)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダの設定
			st.setInt(1, reserveNumber);
			st.setInt(2, customer_id);
			st.setInt(3, inn_id);
			st.setInt(4, people);
			st.setInt(5, stay_days);
			st.setDate(6, first_day);
			st.setInt(7, total_price);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public void updateReserve(int reserve_id, int people, int stay_days,
			Date first_day, int total_price) throws DAOException {
		String sql = "UPDATE reserve SET people = ?, stay_days = ?, first_day = ?, total_price = ? WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダの設定
			st.setInt(1, people);
			st.setInt(2, stay_days);
			st.setDate(3, first_day);
			st.setInt(4, total_price);
			st.setInt(5, reserve_id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public void deleteReserve(int reserve_id) throws DAOException {
		String sql = "UPDATE reserve SET cancel_flag = true WHERE id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダの設定
			st.setInt(1, reserve_id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

}