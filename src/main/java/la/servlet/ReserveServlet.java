package la.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomerBean;
import la.bean.InnBean;
import la.bean.ReserveBean;
import la.dao.DAOException;
import la.dao.InnDAO;
import la.dao.ReserveDAO;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		CustomerBean customer = (CustomerBean) session.getAttribute("Customer");
		try {
			//actionパラメータを読み取る
			String action = request.getParameter("action");
			//DAOの定義	
			ReserveDAO dao = new ReserveDAO();
			InnDAO inndao = new InnDAO();

			//宿一覧の表示
			if (action == null || action.length() == 0 || action.equals("list")) {
				//全宿をリストに入れる
				List<InnBean> inns = inndao.findAllInn();

				request.setAttribute("inns", inns);
				gotoPage(request, response, "/inn.jsp");

				//宿詳細画面の表示
			} else if (action.equals("goinn")) {
				//宿情報を検索
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));
				InnBean inn = inndao.findInnById(inn_id);

				request.setAttribute("inn", inn);
				gotoPage(request, response, "/inndetail.jsp");

				//宿予約画面の表示
			} else if (action.equals("goreserve")) {
				if (customer == null) {
					gotoPage(request, response, "/Customer_Login.jsp");
				} else {
					//宿情報を検索
					int inn_id = Integer.parseInt(request.getParameter("inn_id"));
					InnBean inn = inndao.findInnById(inn_id);

					request.setAttribute("inn", inn);
					gotoPage(request, response, "/reserve.jsp");
				}

				//予約の確定
			} else if (action.equals("reserve")) {
				int flag = 0;
				int people = 0;
				int stay_days = 0;
				int customer_id = customer.getId();
				//入力された予約情報を型変換
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				if (request.getParameter("people") == "") {
					request.setAttribute("people_msg", "人数を入力してください");
					flag = 1;
				} else if (isNumber(request.getParameter("people"))) {
					people = Integer.parseInt(request.getParameter("people"));
				} else {
					request.setAttribute("people_msg", "数字を入力してください");
					flag = 1;
				}
				if (request.getParameter("days") == "") {
					request.setAttribute("days_msg", "日数を入力してください");
					flag = 1;
				} else if (isNumber(request.getParameter("days"))) {
					stay_days = Integer.parseInt(request.getParameter("days"));
				} else {
					request.setAttribute("days_msg", "数字を入力してください");
					flag = 1;
				}
				String strDate = request.getParameter("check_in");
				Date first_day = java.sql.Date.valueOf(strDate);
				//合計金額は宿の金額*人数*宿泊日数で計算
				int total_price = Integer.parseInt(request.getParameter("price")) * people * stay_days;

				//入力された情報の正誤確認
				if (people > 4) {
					request.setAttribute("people_msg", "4以下の数字を入力してください");
					flag = 1;
				}

				if (flag == 1) {
					InnBean inn = inndao.findInnById(inn_id);
					request.setAttribute("inn", inn);
					gotoPage(request, response, "/reserve.jsp");
					return;
				}

				dao.addReserve(customer_id, inn_id, people, stay_days, first_day, total_price);
				gotoPage(request, response, "/reservecomp.jsp");

				//予約一覧画面の表示
			} else if (action.equals("reservelist")) {
				//宿名や宿金額を持ってくるために全宿を検索
				List<InnBean> inns = inndao.findAllInn();
				request.setAttribute("inns", inns);

				if (customer == null) {
					gotoPage(request, response, "/Customer_Login.jsp");
					return;
				}
				int customer_id = customer.getId();
				//int customer_id = 1;

				//会員IDから予約一覧を検索し表示
				List<ReserveBean> reserves = dao.findByCustomerId(customer_id);
				request.setAttribute("reserves", reserves);

				gotoPage(request, response, "/reservelist.jsp");

				//予約変更画面の表示
			} else if (action.equals("edit")) {
				//宿情報を表示
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));
				InnBean inn = inndao.findInnById(inn_id);
				request.setAttribute("inn", inn);

				//予約番号から予約情報を表示
				int id = Integer.parseInt(request.getParameter("reserve_id"));
				ReserveBean reserve = dao.findById(id);
				request.setAttribute("reserve", reserve);

				gotoPage(request, response, "/updatereserve.jsp");

				//予約情報の変更
			} else if (action.equals("update")) {
				//変更する情報を型変換
				int reserve_id = Integer.parseInt(request.getParameter("reserve_id"));
				int customer_id = customer.getId();
				int people = Integer.parseInt(request.getParameter("people"));
				int stay_days = Integer.parseInt(request.getParameter("days"));
				String strDate = request.getParameter("check_in");
				Date first_day = java.sql.Date.valueOf(strDate);
				int total_price = Integer.parseInt(request.getParameter("price")) * people * stay_days;

				//予約情報の変更
				dao.updateReserve(reserve_id, people, stay_days, first_day, total_price);

				//変更したのち、予約一覧画面を表示
				List<InnBean> inns = inndao.findAllInn();
				request.setAttribute("inns", inns);

				List<ReserveBean> reserves = dao.findByCustomerId(customer_id);
				request.setAttribute("reserves", reserves);
				gotoPage(request, response, "/reservelist.jsp");

				//予約キャンセル
			} else if (action.equals("delete")) {
				//予約番号を型変換
				int reserve_id = Integer.parseInt(request.getParameter("reserve_id"));
				int customer_id = customer.getId();

				//予約番号からその予約を非表示にしてキャンセル
				dao.deleteReserve(reserve_id);

				//キャンセルしたのち、予約一覧画面を表示
				List<InnBean> inns = inndao.findAllInn();
				request.setAttribute("inns", inns);

				List<ReserveBean> reserves = dao.findByCustomerId(customer_id);
				request.setAttribute("reserves", reserves);
				gotoPage(request, response, "/reservelist.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	public boolean isNumber(String val) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
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
