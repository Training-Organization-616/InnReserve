package la.servlet;

import java.io.IOException;
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
import la.dao.DAOException;
import la.dao.InnDAO;
import la.dao.PlanDAO;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/PlanServlet")
public class PlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanServlet() {
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
			InnDAO inndao = new InnDAO();
			PlanDAO plandao = new PlanDAO();
			int view_id = 0;
			//宿一覧の表示
			if (action == null || action.length() == 0 || action.equals("list")) {
				if (customer == null) {
					view_id = 0;
				} else {
					view_id = customer.getId();
				}

				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				List<PlanBean> plans = plandao.findByInnId(inn_id, view_id);
				InnBean inn = inndao.findInnById(inn_id);

				request.setAttribute("inn", inn);
				request.setAttribute("plans", plans);
				gotoPage(request, response, "/planlist.jsp");

			} else if (action.equals("regist")) {
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));
				InnBean inn = inndao.findInnById(inn_id);
				request.setAttribute("inn", inn);
				gotoPage(request, response, "/registplan.jsp");
			} else if (action.equals("add")) {
				if (customer == null) {
					view_id = 0;
				} else {
					view_id = customer.getId();
				}
				int flag = 0;
				String title = "";
				int max_people = 0;
				int price = 0;
				String detail = "";
				//入力された予約情報を型変換
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				if (request.getParameter("title") == "") {
					request.setAttribute("detail_msg", "詳細を入力してください");
					flag = 1;
				} else {
					title = request.getParameter("title");
				}

				if (request.getParameter("max_people") == "") {
					request.setAttribute("max_people_msg", "人数を入力してください");
					flag = 1;
				} else if (isNumber(request.getParameter("max_people"))) {
					max_people = Integer.parseInt(request.getParameter("max_people"));
				} else {
					request.setAttribute("people_msg", "数字を入力してください");
					flag = 1;
				}
				if (request.getParameter("price") == "") {
					request.setAttribute("price_msg", "金額を入力してください");
					flag = 1;
				} else if (isNumber(request.getParameter("price"))) {
					price = Integer.parseInt(request.getParameter("price"));
				} else {
					request.setAttribute("days_msg", "数字を入力してください");
					flag = 1;
				}
				if (request.getParameter("detail") == "") {
					request.setAttribute("detail_msg", "詳細を入力してください");
					flag = 1;
				} else {
					detail = request.getParameter("detail");
				}

				if (flag == 1) {
					InnBean inn = inndao.findInnById(inn_id);
					request.setAttribute("inn", inn);
					gotoPage(request, response, "/registplan.jsp");
					return;
				}

				plandao.addPlan(inn_id, title, max_people, price, detail);
				inndao.calcMinPrice(inn_id);

				//変更したのち、プラン一覧画面を表示
				InnBean inn = inndao.findInnById(inn_id);
				request.setAttribute("inn", inn);

				List<PlanBean> plans = plandao.findByInnId(inn_id, view_id);
				request.setAttribute("plans", plans);

				gotoPage(request, response, "/planlist.jsp");

			} else if (action.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("plan_id"));
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				String title = request.getParameter("title");
				int max_people = Integer.parseInt(request.getParameter("max_people"));
				int price = Integer.parseInt(request.getParameter("price"));
				String detail = request.getParameter("detail");
				// 宿情報をリクエストスコープで渡す
				request.setAttribute("plan_id", id);
				request.setAttribute("inn_id", inn_id);
				request.setAttribute("title", title);
				request.setAttribute("max_people", max_people);
				request.setAttribute("price", price);
				request.setAttribute("detail", detail);
				//宿情報を表示
				InnBean inn = inndao.findInnById(inn_id);
				request.setAttribute("inn", inn);

				//予約番号から予約情報を表示
				PlanBean plan = plandao.findById(id);
				request.setAttribute("plan", plan);

				gotoPage(request, response, "/updateplan.jsp");

				//予約情報の変更
			} else if (action.equals("update")) {
				if (customer == null) {
					view_id = 0;
				} else {
					view_id = customer.getId();
				}
				int flag = 0;
				String title = "";
				int max_people = 0;
				int price = 0;
				String detail = "";
				//入力された予約情報を型変換
				int plan_id = Integer.parseInt(request.getParameter("plan_id"));
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				if (request.getParameter("title") == "") {
					request.setAttribute("detail_msg", "詳細を入力してください");
					flag = 1;
				} else {
					title = request.getParameter("title");
				}

				if (request.getParameter("max_people") == "") {
					request.setAttribute("max_people_msg", "人数を入力してください");
					flag = 1;
				} else if (isNumber(request.getParameter("max_people"))) {
					max_people = Integer.parseInt(request.getParameter("max_people"));
				} else {
					request.setAttribute("people_msg", "数字を入力してください");
					flag = 1;
				}
				if (request.getParameter("price") == "") {
					request.setAttribute("price_msg", "金額を入力してください");
					flag = 1;
				} else if (isNumber(request.getParameter("price"))) {
					price = Integer.parseInt(request.getParameter("price"));
				} else {
					request.setAttribute("days_msg", "数字を入力してください");
					flag = 1;
				}
				if (request.getParameter("detail") == "") {
					request.setAttribute("detail_msg", "詳細を入力してください");
					flag = 1;
				} else {
					detail = request.getParameter("detail");
				}

				if (flag == 1) {
					InnBean inn = inndao.findInnById(inn_id);
					request.setAttribute("inn", inn);
					gotoPage(request, response, "/updateplan.jsp");
					return;
				}

				//プラン情報の変更
				plandao.updatePlan(plan_id, inn_id, title, max_people, price, detail);
				inndao.calcMinPrice(inn_id);
				//変更したのち、プラン一覧画面を表示
				InnBean inn = inndao.findInnById(inn_id);
				request.setAttribute("inn", inn);

				List<PlanBean> plans = plandao.findByInnId(inn_id, view_id);
				request.setAttribute("plans", plans);

				gotoPage(request, response, "/planlist.jsp");

				//予約キャンセル
			} else if (action.equals("delete")) {
				if (customer == null) {
					view_id = 0;
				} else {
					view_id = customer.getId();
				}
				//予約番号を型変換
				int plan_id = Integer.parseInt(request.getParameter("plan_id"));
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				view_id = customer.getId();
				//予約番号からそのプランを非表示
				plandao.deletePlan(plan_id);
				inndao.calcMinPrice(inn_id);

				//キャンセルしたのち、プラン一覧画面を表示
				InnBean inn = inndao.findInnById(inn_id);

				request.setAttribute("inn", inn);
				List<PlanBean> plans = plandao.findByInnId(inn_id, view_id);
				request.setAttribute("plans", plans);

				gotoPage(request, response, "/planlist.jsp");

			} else if (action.equals("truedelete")) {
				if (customer == null) {
					view_id = 0;
				} else {
					view_id = customer.getId();
				}
				//予約番号を型変換
				int plan_id = Integer.parseInt(request.getParameter("plan_id"));
				int inn_id = Integer.parseInt(request.getParameter("inn_id"));

				view_id = customer.getId();
				//予約番号からそのプランを非表示
				plandao.trueDeletePlan(plan_id);
				inndao.calcMinPrice(inn_id);

				//キャンセルしたのち、プラン一覧画面を表示
				InnBean inn = inndao.findInnById(inn_id);

				request.setAttribute("inn", inn);
				List<PlanBean> plans = plandao.findByInnId(inn_id, view_id);
				request.setAttribute("plans", plans);

				gotoPage(request, response, "/planlist.jsp");

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
