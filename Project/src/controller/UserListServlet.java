package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 未実装：ログインセッションがない場合、ログイン画面にリダイレクトさせる
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("userInfo");

		if (u == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		// ユーザ一覧情報を取得
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		// ユーザ一覧のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/User_List.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// TODO  未実装：検索処理全般
		String loginIdP = request.getParameter("login_id");
		String nameP = request.getParameter("name");
		String birthDateP = request.getParameter("birth_date");
		String birthDatePP = request.getParameter("birth_date1");


		UserDao UserListPDao = new UserDao();
		List<User> list = UserListPDao.findSearch(loginIdP, nameP,birthDateP,birthDatePP);

		request.setAttribute("userList",list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/User_List.jsp");
		dispatcher.forward(request, response);

		//		以下自分で考えていたもの。
		//
		//
		//		//ログインidで検索
		//		String SearchId = request.getParameter("login_id");
		//		UserDao SearchIdDao = new UserDao();
		//
		//		List<User> user =SearchIdDao.SearchId(SearchId);
		//
		//		//異常時
		//		if (user == null) {
		//			request.setAttribute("errMsg", "間違いです！。");
		//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserListServlet.jsp");
		//		dispatcher.forward(request, response);
		//		return;
		//		}
	}

}
