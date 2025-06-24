package la.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
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
import la.bean.PlanBean;
import la.bean.ReserveBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;
import la.dao.InnDAO;
import la.dao.PlanDAO;
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
			CustomerDAO customerdao = new CustomerDAO();
			PlanDAO plandao = new PlanDAO();
			int view_id = 0;
			//宿一覧の表示
			if (action == null || action.length() == 0 || action.equals("list")) {
				if (customer == null) {
					view_id = 0;
				} else {
					view_id = customer.getId();
				}
				//全宿をリストに入れる
				List<InnBean> inns = inndao.findAllInn(view_id);
				List<CustomerBean> customers = customerdao.findAll();
				request.setAttribute("customers", customers);

				request.setAttribute("inns", inns);
				gotoPage(request, response, "/inn.jsp");

				//宿詳細画面の表示
			} else if (action.equals("goinn")) {

				//ユーザーID取得
				if (customer == null) {
					view_id = 0;
				} else {
					view_id = customer.getId();
				}
				//宿情報を検索
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));
				InnBean inn = inndao.findInnById(inn_id);

				//プラン検索
				List<PlanBean> plans = plandao.findByInnId(inn_id, view_id);

				request.setAttribute("inn", inn);
				request.setAttribute("plans", plans);
				gotoPage(request, response, "/inndetail.jsp");

				//宿予約画面の表示
			} else if (action.equals("goreserve")) {
				if (customer == null) {
					gotoPage(request, response, "/Customer_Login.jsp");
				} else {
					//宿情報を検索
					//プラン情報を検索

					int plan_id = Integer.parseInt(request.getParameter("plan_id"));

					PlanBean plan = plandao.findById(plan_id);

					request.setAttribute("plan", plan);

					int inn_id = plan.getInn_id();

					InnBean inn = inndao.findInnById(inn_id);
					request.setAttribute("inn", inn);
					gotoPage(request, response, "/reserve.jsp");
				}

				//予約の確定
			} else if (action.equals("reserve")) {

				//追加要素
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));
				int plan_id = Integer.parseInt(request.getParameter("plan_id"));
				int max_people = Integer.parseInt(request.getParameter("max_people"));
				int price = Integer.parseInt(request.getParameter("price"));

				int flag = 0;
				int people = 0;
				int stay_days = 0;
				String strDate = "";
				Date first_day = null;
				//入力された予約情報を型変換

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
				if (request.getParameter("check_in") == "") {
					request.setAttribute("check_in_msg", "日付を入力してください");
					flag = 1;
				} else {
					strDate = request.getParameter("check_in");
					first_day = java.sql.Date.valueOf(strDate);
				}

				//入力された情報の正誤確認
				if (people > max_people) {
					request.setAttribute("people_msg", "人以下の数字を入力してください");
					flag = 1;
				}

				if (flag == 1) {
					request.setAttribute("plan_id", plan_id);
					request.setAttribute("max_people", max_people);
					request.setAttribute("price", price);
					request.setAttribute("inn_id", inn_id);
					PlanBean plan = plandao.findById(plan_id);
					request.setAttribute("Plan", plan);

					InnBean inn = inndao.findInnById(inn_id);
					request.setAttribute("inn", inn);
					gotoPage(request, response, "/reserve.jsp");
					return;
				}

				//dao.addReserve(customer_id, inn_id, people, stay_days, first_day, total_price);

				//合計金額は宿の金額*人数*宿泊日数で計算
				int total_price = price * people * stay_days;

				//宿泊日の加算処理(カレンダー型に変換
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(first_day);
				//日時の加算
				calendar.add(Calendar.DATE, stay_days);
				//java.sql.date型に変換
				Date finally_day = new Date(calendar.getTime().getTime());

				//カスタマーのポイントに必要
				int cus_point = customer.getPoint();

				request.setAttribute("inn_id", inn_id);
				request.setAttribute("plan_id", plan_id);
				request.setAttribute("cus_point", cus_point);
				request.setAttribute("total_price", total_price);
				request.setAttribute("first_day", strDate);
				request.setAttribute("finally_day", finally_day);
				request.setAttribute("people", people);
				request.setAttribute("stay_days", stay_days);

				gotoPage(request, response, "/payment.jsp");

				//予約確認画面(new
			} else if (action.equals("comfirm")) {

				int inn_id = Integer.parseInt(request.getParameter("inn_id"));
				int plan_id = Integer.parseInt(request.getParameter("plan_id"));
				int total_price = Integer.parseInt(request.getParameter("total_price"));
				int how_usepoint = 0;
				int people = Integer.parseInt(request.getParameter("people"));
				int stay_days = Integer.parseInt(request.getParameter("stay_days"));
				if (request.getParameter("usePoint").equals("yes")) {
					how_usepoint = Integer.parseInt(request.getParameter("how_usePoint"));
				}

				//チェックイン日ーチェックアウト日（チェックアウトはデータ外）
				String first = request.getParameter("first_day");
				Date first_day = java.sql.Date.valueOf(first);

				String finally_d = request.getParameter("finally_day");
				Date finally_day = java.sql.Date.valueOf(finally_d);

				InnBean inn = inndao.findInnById(inn_id);
				PlanBean plan = plandao.findById(plan_id);

				request.setAttribute("original_price", total_price);

				total_price -= how_usepoint;

				request.setAttribute("inn", inn);
				request.setAttribute("plan", plan);
				request.setAttribute("total_price", total_price);
				request.setAttribute("how_usepoint", how_usepoint);
				request.setAttribute("first_day", first_day);
				request.setAttribute("finally_day", finally_day);
				request.setAttribute("people", people);
				request.setAttribute("stay_days", stay_days);

				gotoPage(request, response, "/reservecheck.jsp");

			} else if (action.equals("reservecomp")) {
				int customer_id = Integer.parseInt(request.getParameter("customer_id"));
				int plan_id = Integer.parseInt(request.getParameter("plan_id"));
				int original_price = Integer.parseInt(request.getParameter("original_price"));
				int people = Integer.parseInt(request.getParameter("people"));
				int stay_days = Integer.parseInt(request.getParameter("stay_days"));
				String first = request.getParameter("first_day");
				Date first_day = java.sql.Date.valueOf(first);
				int how_usepoint = Integer.parseInt(request.getParameter("how_usepoint"));

				PlanBean plan = plandao.findById(plan_id);
				int inn_id = plan.getInn_id();

				int give_point = (int) (original_price * 0.1);

				int now_point = customer.getPoint();

				now_point = now_point - how_usepoint + give_point;
				customerdao.updatePoint(customer_id, now_point);

				dao.addReserve(customer_id, inn_id, plan.getId(), people, stay_days, first_day, original_price);

				request.setAttribute("give_point", give_point);
				CustomerBean update_customer = customerdao.findByID(customer_id);
				session.setAttribute("Customer", update_customer);
				gotoPage(request, response, "/reservecomp.jsp");

				//予約一覧画面の表示
			} else if (action.equals("reservelist")) {
				//宿名や宿金額を持ってくるために全宿を検索
				view_id = customer.getId();
				//全宿をリストに入れる
				List<InnBean> inns = inndao.findAllInn(view_id);
				request.setAttribute("inns", inns);

				int customer_id = customer.getId();
				//int customer_id = 1;
				List<CustomerBean> customers = customerdao.findAll();
				request.setAttribute("customers", customers);

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
				int flag = 0;
				int people = 0;
				int stay_days = 0;
				String strDate;
				Date first_day = null;
				int customer_id = customer.getId();
				//入力された予約情報を型変換
				int reserve_id = Integer.parseInt(request.getParameter("reserve_id"));
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				view_id = customer.getId();
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
				if (request.getParameter("check_in") == "") {
					request.setAttribute("check_in_msg", "日付を入力してください");
					flag = 1;
				} else {
					strDate = request.getParameter("check_in");
					first_day = java.sql.Date.valueOf(strDate);
				}
				//合計金額は宿の金額*人数*宿泊日数で計算
				int total_price = Integer.parseInt(request.getParameter("price")) * people * stay_days;

				//入力された情報の正誤確認
				if (people > 4) {
					request.setAttribute("people_msg", "4以下の数字を入力してください");
					flag = 1;
				}

				if (flag == 1) {
					ReserveBean reserve = dao.findById(reserve_id);
					request.setAttribute("reserve", reserve);
					InnBean inn = inndao.findInnById(inn_id);
					request.setAttribute("inn", inn);
					gotoPage(request, response, "/updatereserve.jsp");
					return;
				}

				//予約情報の変更
				dao.updateReserve(reserve_id, people, stay_days, first_day, total_price);

				//変更したのち、予約一覧画面を表示
				List<InnBean> inns = inndao.findAllInn(view_id);
				request.setAttribute("inns", inns);

				List<CustomerBean> customers = customerdao.findAll();
				request.setAttribute("customers", customers);
				List<ReserveBean> reserves = dao.findByCustomerId(customer_id);
				request.setAttribute("reserves", reserves);
				gotoPage(request, response, "/reservelist.jsp");

				//予約キャンセル
			} else if (action.equals("delete")) {
				//予約番号を型変換
				int reserve_id = Integer.parseInt(request.getParameter("reserve_id"));
				int customer_id = customer.getId();

				view_id = customer.getId();
				//予約番号からその予約を非表示にしてキャンセル
				dao.deleteReserve(reserve_id);

				//キャンセルしたのち、予約一覧画面を表示
				List<InnBean> inns = inndao.findAllInn(view_id);
				request.setAttribute("inns", inns);
				List<CustomerBean> customers = customerdao.findAll();
				request.setAttribute("customers", customers);

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
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
