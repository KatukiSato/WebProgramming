package controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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
 * Servlet implementation class UserNewEntry
 */
@WebServlet("/UserNewEntryServlet")
public class UserNewEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserNewEntryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("userInfo");

		if (u == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserNewEntry.jsp");
		dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("login_Id");
		String password = request.getParameter("password");
		String userName = request.getParameter("name");
		String birthDate = request.getParameter("birth_date");
		String checkPassword = request.getParameter("pass");


//取得したパラメータをentryDaoで実行する?

		if (loginId.equals("") || password. equals ("") || userName. equals ("") || birthDate. equals ("") || !(password. equals (checkPassword))) {

			request.setAttribute("errMsg", "入力された内容は正しくありません。");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserNewEntry.jsp");
			dispatcher.forward(request, response);
			return;
		}

		UserDao entryDao = new UserDao();
		try {
			String a =Common.Ango(password);
			String b =Common.Ango(checkPassword);

			try {
				entryDao.entry(loginId, userName, birthDate, a,b);
			} catch (SQLException e) {
				request.setAttribute("errMsg", "このIDは既に使用されています。");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserNewEntry.jsp");
				dispatcher.forward(request, response);
				return;
			}
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		response.sendRedirect("UserListServlet");
	}
}
