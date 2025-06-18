package la.servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.InnBean;
import la.dao.DAOException;
import la.dao.InnDAO;

/**
 * Servlet implementation class InnServlet
 */
@WebServlet("/InnServlet")
public class InnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// パラメータ解析

		// InnDAOの取得
		try {
			// アクションパラメータの取得
			String action = request.getParameter("action");
			InnDAO dao = new InnDAO();
			// セッション管理している会員情報の取得
			String customer = request.getParameter("customer");
			customer = "manager";
			// アクションがなしかlistの場合
			if (action == null || action.length() == 0 || action.equals("list")) {
				// 会員セッションが存在しないか会員の場合
				if (customer == null || customer.equals("user")) {
					// 宿一覧の取得
					List<InnBean> list = dao.findAllInn();
					// リクエストスコープで一覧を渡す
					request.setAttribute("items", list);
					request.setAttribute("menu", 1);
					// 宿一覧画面へ遷移
					gotoPage(request, response, "manager/.jsp");
				} else if (customer.equals("manager")) {// 管理者の場合
					// 宿一覧の取得
					List<InnBean> list = dao.findAllInn();
					// リクエストスコープで一覧を渡す
					request.setAttribute("items", list);
					request.setAttribute("menu", 1);
					// 管理者画面へ遷移
					gotoPage(request, response, "/manager.jsp");
				}

			} else if (action.equals("regist")) {// 新規宿登録ボタン押下
				// 宿登録画面へ遷移
				gotoPage(request, response, "/registInn.jsp");
			} else if (action.equals("add")) {// 登録ボタン押下
				// 入力情報の取得
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String stPrice = request.getParameter("price");

				// 未入力エラー処理
				if (name == "" || address == "" || tel == "" || stPrice == "") {
					request.setAttribute("message", "値を入力してください。");
					gotoPage(request, response, "/registInn.jsp");
					return;
				}

				// 宿名が文字数オーバー
				if (name.length() > 50) {
					request.setAttribute("message", "宿名は50文字以内で入力してください。");
					gotoPage(request, response, "/registInn.jsp");
					return;
				}

				// 場所が文字数オーバー
				if (address.length() > 50) {
					request.setAttribute("message", "場所は50文字以内で入力してください。");
					gotoPage(request, response, "/registInn.jsp");
					return;
				}

				// 電話番号が文字数オーバー
				if (tel.length() > 20) {
					request.setAttribute("message", "電話番号は20文字以内で入力してください。");
					gotoPage(request, response, "/registInn.jsp");
					return;
				}

				// 電話番号に-がなく、形式が誤り
				Pattern p = Pattern.compile("^[0-9]{2}-[0-9]{4}-[0-9]{4}$");
				Pattern p2 = Pattern.compile("^[0-9]{3}-[0-9]{4}-[0-9]{4}$");
				Pattern p3 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{4}$");
				if (p.matcher(tel).find() != true) {
					if (p2.matcher(tel).find() != true) {
						if (p3.matcher(tel).find() != true) {
							request.setAttribute("message", "電話番号は-を含めて入力してください。");
							gotoPage(request, response, "/registInn.jsp");
							return;
						}
					}
				}
				int price = Integer.parseInt(stPrice);
				// 宿の追加
				dao.addInn(name, address, tel, price);
				// 宿一覧の取得
				List<InnBean> list = dao.findAllInn();
				// リクエストスコープで一覧を渡す
				request.setAttribute("items", list);
				request.setAttribute("menu", 1);
				// 管理画面へ遷移
				gotoPage(request, response, "/manager.jsp");
			} else if (action.equals("edit")) {// 宿情報変更ボタン押下
				// 宿idの取得
				int id = Integer.parseInt(request.getParameter("id"));
				// 宿情報の取得
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				int price = Integer.parseInt(request.getParameter("price"));
				// 宿情報をリクエストスコープで渡す
				request.setAttribute("inn_id", id);
				request.setAttribute("name", name);
				request.setAttribute("address", address);
				request.setAttribute("tel", tel);
				request.setAttribute("price", price);

				// 一覧の取得
				List<InnBean> list = dao.findAllInn();
				// リクエストスコープで一覧を渡す
				request.setAttribute("items", list);
				// 宿情報変更画面へ遷移
				gotoPage(request, response, "/updateInn.jsp");
			} else if (action.equals("update")) {// 変更ボタン押下
				// 宿idの取得
				int id = Integer.parseInt(request.getParameter("id"));
				// 入力情報の取得
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String stPrice = request.getParameter("price");

				// 未入力エラー処理
				if (name == "" || address == "" || tel == "" || stPrice == "") {
					request.setAttribute("message", "値を入力してください。");
					request.setAttribute("inn_id", id);
					gotoPage(request, response, "/updateInn.jsp");
					return;
				}

				// 宿名が文字数オーバー
				if (name.length() > 50) {
					request.setAttribute("message", "宿名は50文字以内で入力してください。");
					request.setAttribute("inn_id", id);
					gotoPage(request, response, "/updateInn.jsp");
					return;
				}

				// 場所が文字数オーバー
				if (address.length() > 50) {
					request.setAttribute("message", "場所は50文字以内で入力してください。");
					request.setAttribute("inn_id", id);
					gotoPage(request, response, "/updateInn.jsp");
					return;
				}

				// 電話番号が文字数オーバー
				if (tel.length() > 20) {
					request.setAttribute("message", "電話番号は20文字以内で入力してください。");
					request.setAttribute("inn_id", id);
					gotoPage(request, response, "/updateInn.jsp");
					return;
				}

				// 電話番号に-がなく、形式が誤り
				Pattern p = Pattern.compile("^[0-9]{2}-[0-9]{4}-[0-9]{4}$");
				Pattern p2 = Pattern.compile("^[0-9]{3}-[0-9]{4}-[0-9]{4}$");
				Pattern p3 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{4}$");
				if (p.matcher(tel).find() != true) {
					if (p2.matcher(tel).find() != true) {
						if (p3.matcher(tel).find() != true) {
							request.setAttribute("message", "電話番号は-を含めて入力してください。");
							request.setAttribute("inn_id", id);
							gotoPage(request, response, "/updateInn.jsp");
							return;
						}
					}
				}
				int price = Integer.parseInt(stPrice);

				// 宿情報の変更
				dao.updateInn(id, name, address, tel, price);
				// 宿一覧の取得
				List<InnBean> list = dao.findAllInn();
				// リクエストスコープで一覧を渡す
				request.setAttribute("items", list);
				request.setAttribute("menu", 1);
				// 管理画面へ遷移
				gotoPage(request, response, "/manager.jsp");
			} else if (action.equals("delete")) {// 削除ボタン押下
				// 宿idの取得
				int id = Integer.parseInt(request.getParameter("id"));
				// 宿情報の削除
				dao.deleteInn(id);
				// 宿一覧の取得
				List<InnBean> list = dao.findAllInn();
				// リクエストスコープで一覧を渡す
				request.setAttribute("items", list);
				request.setAttribute("menu", 1);

				// 管理画面へ遷移
				gotoPage(request, response, "/manager.jsp");
			}

		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
