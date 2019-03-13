package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// TODO 未実装：ログインセッションがある場合、ユーザ一覧画面にリダイレクトさせる
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("userInfo");

		if (!(u == null)) {
			response.sendRedirect("UserListServlet");
			return;
		}

		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/User_Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("name");
		String password = request.getParameter("password");

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		try {
			String a = Common.Ango(password);
			User user = userDao.findByLoginInfo(loginId, a);

			/** テーブルに該当のデータが見つからなかった場合 **/
			if (user == null) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("errMsg", "ログインIDまたはパスワードが異なります。");

				// ログインjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/User_Login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			/** テーブルに該当のデータが見つかった場合 **/
			// セッションにユーザの情報をセット
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);

		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");

	}

}
