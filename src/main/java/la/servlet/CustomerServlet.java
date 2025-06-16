package la.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomerBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			String action = request.getParameter("action");

			//CustomerBean bean = new CustomerBean();

			CustomerDAO dao = new CustomerDAO();

			HttpSession session = request.getSession();

			if (action.equals("gologin") || action == null || action.length() == 0 || action.equals(null)) {

				session = request.getSession(false);

				gotoPage(request, response, "/Customer_Login");

			} else if (action.equals("login")) {//ログイン処理

				String email = request.getParameter("email");
				String password = request.getParameter("password");

				if (email.equals("") || password.equals("")) {
					request.setAttribute("Login_message", "email または パスワードが記載されていません");
					gotoPage(request, response, "/Customer_login.jsp");
				}

				CustomerBean Customer = dao.findCustomer(email, password);

				if (Customer == null) {
					request.setAttribute("Login_message", "email または パスワードが正しくありません");
					gotoPage(request, response, "/Customer_login.jsp");
				}

				session.setAttribute("Customer", Customer);

				//ログイン分岐
				if (Customer.getId() == 1) {

					gotoPage(request, response, "/test2.jsp"); //////////////////会員ページへ移動

				}

				gotoPage(request, response, "/test.jsp"); ////////////////////要　宿一覧画面名

			} else if (action.equals("regist")) {//会員登録画面へ遷移

				gotoPage(request, response, "/Customer_Regist.jsp");

			} else if (action.equals("add")) {//会員情報の登録

				String name = request.getParameter("name");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String check_password = request.getParameter("check_password");

				//エラー処理
				if (name.equals("") || tel.equals("") || email.equals("") || password.equals("")
						|| check_password.equals("")) {
					request.setAttribute("Regist_message", "未記入の記入欄があります");
					gotoPage(request, response, "/Customer_Login.jsp");
				}

				dao.addCustomer(name, tel, email, password);

				request.setAttribute("Login_message", "会員登録が完了しました\n ログインして下さい");

				gotoPage(request, response, "/Customer_Login.jsp");

			} else if (action.equals("list")) {//会員の情報一覧表示

				//int id = Integer.parseInt(request.getParameter("id"));

				List<CustomerBean> Customers = dao.findAll();

				request.setAttribute("Customers_list", Customers);

				gotoPage(request, response, "/test3.jsp");

			} else if (action.equals("edit")) {//会員の情報変更画面へ遷移

				int id = Integer.parseInt(request.getParameter("id"));

				CustomerBean Customer = dao.findByID(id);

				request.setAttribute("id", Customer.getId());
				request.setAttribute("name", Customer.getName());
				request.setAttribute("tel", Customer.getTel());
				request.setAttribute("email", Customer.getEmail());
				//request.setAttribute("password", Customer.getPassword());

				gotoPage(request, response, "/Customer_Update.jsp");

			} else if (action.equals("update")) {//会員情報の変更処理

				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String check_password = request.getParameter("check_password");

				//エラー処理
				if (name.equals("") || tel.equals("") || email.equals("") || password.equals("")
						|| check_password.equals("")) {
					request.setAttribute("Update_massage", "未記入の記入欄があります");

					CustomerBean Customer = dao.findByID(id);

					request.setAttribute("id", Customer.getId());
					request.setAttribute("name", Customer.getName());
					request.setAttribute("tel", Customer.getTel());
					request.setAttribute("email", Customer.getEmail());

					gotoPage(request, response, "/Customer_Update.jsp");
				}

				dao.updateCustomer(id, name, tel, email, password);

				CustomerBean ADMIN = (CustomerBean) session.getAttribute("Customer");

				if (ADMIN.getId() == 1) {

					List<CustomerBean> Customers = dao.findAll();

					request.setAttribute("Customers_list", Customers);

					gotoPage(request, response, "/test3.jsp");

				}

				request.setAttribute("Login_message", "アカウント情報を変更しました ログインしてください");

				gotoPage(request, response, "/Customer_Login.jsp");

			} else if (action.equals("delete")) {//会員削除

				int id = Integer.parseInt(request.getParameter("id"));

				dao.deleteCustomer(id);

				CustomerBean ADMIN = (CustomerBean) session.getAttribute("Customer");

				if (ADMIN.getId() == 1) {

					List<CustomerBean> Customers = dao.findAll();

					request.setAttribute("Customers_list", Customers);

					gotoPage(request, response, "/test3.jsp");

				}

				session = request.getSession(false);

				gotoPage(request, response, "/test.jsp"); //////////////////////要宿

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (DAOException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@SuppressWarnings("unused")
	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
