package la.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

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
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		try {

			String action = request.getParameter("action");

			//CustomerBean bean = new CustomerBean();

			CustomerDAO dao = new CustomerDAO();

			HttpSession session = request.getSession();

			if (action.equals("gologin") || action == null || action.length() == 0 || action.equals(null)) {

				session = request.getSession(false);

				gotoPage(request, response, "/Customer_Login.jsp");

			} else if (action.equals("login")) {//ログイン処理

				String email = request.getParameter("email");
				String password = request.getParameter("password");

				if (email.equals("") || password.equals("")) {
					request.setAttribute("Login_message", "email または パスワードが記載されていません");
					gotoPage(request, response, "/Customer_Login.jsp");
					return;
				}

				CustomerBean Customer = dao.findCustomer(email, password);

				if (Customer == null) {
					request.setAttribute("Login_message", "email または パスワードが正しくありません");
					gotoPage(request, response, "/Customer_Login.jsp");
					return;
				}
				if (email.equals("null")) {
					request.setAttribute("Login_message", "存在しないアカウントです");
					gotoPage(request, response, "/Customer_Login.jsp");
					return;
				}

				session.setAttribute("Customer", Customer);
				if (Customer.getId() == 1) {
					gotoPage(request, response, "/InnServlet?action=list"); //////////////////管理人ページへ移動
				} else {
					gotoPage(request, response, "/ReserveServlet?action=list"); //////////////////会員ページへ移動
				}
			} else if (action.equals("logout")) {//会員登録画面へ遷移

				session.removeAttribute("Customer");
				gotoPage(request, response, "/Customer_Login.jsp");

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
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
				}
				Pattern p = Pattern.compile(".*-.*-.*");
				if (name.length() >= 20) {
					request.setAttribute("Regist_message", "名前は20文字以下で入力してください");
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
				} else if (tel.length() >= 20) {
					request.setAttribute("Regist_message", "電話番号は20文字以下で入力してください");
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
				} else if (p.matcher(tel).find() != true) {
					request.setAttribute("Regist_message", "電話番号に「-：ハイフン」がありません");
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
				} else if (email.length() >= 50) {
					request.setAttribute("Regist_message", "メールアドレスは50文字以内で入力してください");
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
					//} else if (!email.contains("@")) {
					//	request.setAttribute("Regist_message", "メールアドレスに「@」がありません");
					//	gotoPage(request, response, "/Customer_Regist.jsp");
					//	return;
				} else if (email.length() >= 50) {
					request.setAttribute("Regist_message", "メールアドレスは50文字以内で入力してください");
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
				} else if (password.length() >= 20) {
					request.setAttribute("Regist_message", "パスワードは20文字以下で入力してください");
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
				} else if (!password.equals(check_password)) {
					request.setAttribute("Regist_message", "パスワードに誤りがあります");
					gotoPage(request, response, "/Customer_Regist.jsp");
					return;
				}

				List<CustomerBean> check_list = dao.findAll();

				for (CustomerBean check_email : check_list) {
					if (check_email.getEmail().equals(email)) {
						request.setAttribute("Regist_message", "アドレスは既に使われてます");
						gotoPage(request, response, "/Customer_Regist.jsp");
						return;
					}
				}
				//-----------------------------------------------------------

				dao.addCustomer(name, tel, email, password);

				request.setAttribute("Login_message", "会員登録が完了しました\n ログインして下さい");

				gotoPage(request, response, "/Customer_Login.jsp");

			} else if (action.equals("list")) {//会員の情報一覧表示

				//int id = Integer.parseInt(request.getParameter("id"));

				List<CustomerBean> Customers = dao.findAll();

				request.setAttribute("Customers_list", Customers);
				request.setAttribute("menu", 3);

				gotoPage(request, response, "/manager.jsp");

			} else if (action.equals("edit")) {//会員の情報変更画面へ遷移

				int id = Integer.parseInt(request.getParameter("id"));

				CustomerBean Customer = dao.findByID(id);

				request.setAttribute("id", Customer.getId());
				request.setAttribute("name", Customer.getName());
				request.setAttribute("tel", Customer.getTel());
				request.setAttribute("email", Customer.getEmail());
				request.setAttribute("original_email", Customer.getEmail());
				//request.setAttribute("password", Customer.getPassword());

				gotoPage(request, response, "/Customer_Update.jsp");

			} else if (action.equals("update")) {//会員情報の変更処理

				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String check_password = request.getParameter("check_password");
				String origin_email = request.getParameter("original_email");
				//エラー処理
				if (name.equals("") || tel.equals("") || email.equals("") || password.equals("")
						|| check_password.equals("")) {
					request.setAttribute("Update_massage", "未記入の記入欄があります");

					CustomerBean Customer = dao.findByID(id);

					request.setAttribute("id", Customer.getId());
					request.setAttribute("name", Customer.getName());
					request.setAttribute("tel", Customer.getTel());
					request.setAttribute("email", Customer.getEmail());
					request.setAttribute("original_email", Customer.getEmail());

					gotoPage(request, response, "/Customer_Update.jsp");
					return;
				}

				//エラー処理
				Pattern p = Pattern.compile(".*-.*-.*");
				if (p.matcher(tel).find() != true) {
					request.setAttribute("Update_massage", "電話番号に「-：ハイフン」がありません");

					CustomerBean Customer = dao.findByID(id);

					request.setAttribute("id", Customer.getId());
					request.setAttribute("name", Customer.getName());
					request.setAttribute("tel", Customer.getTel());
					request.setAttribute("email", Customer.getEmail());
					request.setAttribute("original_email", Customer.getEmail());

					gotoPage(request, response, "/Customer_Update.jsp");
					return;

				} else if (!password.equals(check_password)) {
					request.setAttribute("Update_massage", "パスワードに誤りがあります");

					CustomerBean Customer = dao.findByID(id);

					request.setAttribute("id", Customer.getId());
					request.setAttribute("name", Customer.getName());
					request.setAttribute("tel", Customer.getTel());
					request.setAttribute("email", Customer.getEmail());
					request.setAttribute("original_email", Customer.getEmail());

					gotoPage(request, response, "/Customer_Update.jsp");
					return;
				}

				List<CustomerBean> check_list = dao.findAll();

				if (!(email.equals(origin_email))) {
					for (CustomerBean check_email : check_list) {
						if (check_email.getEmail().equals(email)) {
							request.setAttribute("Update_massage", "アドレスは既に使われてます");

							CustomerBean Customer = dao.findByID(id);

							request.setAttribute("id", Customer.getId());
							request.setAttribute("name", Customer.getName());
							request.setAttribute("tel", Customer.getTel());
							request.setAttribute("email", Customer.getEmail());
							request.setAttribute("original_email", Customer.getEmail());

							gotoPage(request, response, "/Customer_Update.jsp");
							return;
						}
					}
				}
				//-----------------------------------------------------------

				dao.updateCustomer(id, name, tel, email, password);
				CustomerBean ADMIN = (CustomerBean) session.getAttribute("Customer");

				if (ADMIN.getId() == 1) {

					List<CustomerBean> Customers = dao.findAll();

					request.setAttribute("Customers_list", Customers);

					gotoPage(request, response, "/InnServlet?action=list");
					return;

				} else {
					CustomerBean customer = dao.findByID(id);
					session.setAttribute("Customer", customer);

				}

				gotoPage(request, response, "/ReserveServlet?action=list");

			} else if (action.equals("delete")) {//会員削除

				int id = Integer.parseInt(request.getParameter("id"));

				dao.deleteCustomer(id);

				CustomerBean ADMIN = (CustomerBean) session.getAttribute("Customer");

				if (ADMIN.getId() == 1) {

					List<CustomerBean> Customers = dao.findAll();

					request.setAttribute("Customers_list", Customers);

					gotoPage(request, response, "/InnServlet?action=list");
					return;

				}

				session = request.getSession(false);
				session.removeAttribute("Customer");

				gotoPage(request, response, "/ReserveServlet?action=list"); //////////////////////要宿

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
		return;
	}
}
