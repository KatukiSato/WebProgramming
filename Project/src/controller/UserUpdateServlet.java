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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("userInfo");

		if (u == null) {
			response.sendRedirect("LoginServlet");
			return;
		}		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");

		UserDao UpdateDao = new UserDao();
		User use = UpdateDao.detailAll(id);
		request.setAttribute("id", use);

		User U = (User) request.getAttribute("id");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String Name = request.getParameter("Name");
		String birthDate = request.getParameter("Birth_date");
		String password = request.getParameter("Password");
		String checkPassword = request.getParameter("checkpass");

		//パスワードは更新しない時
		if (password.equals("") && checkPassword.equals("")) {
			UserDao UpDao = new UserDao();
			User user = UpDao.UpdateAllX(Name, birthDate, Integer.parseInt(id));

			if (Name.equals("") || birthDate.equals("")) {

				request.setAttribute("errMsg", "入力された内容は正しくありません。");

				UserDao UpdateDao = new UserDao();
				User use = UpdateDao.detailAll(id);
				request.setAttribute("id", use);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserUpdate.jsp");
				dispatcher.forward(request, response);
				return;
			}
			response.sendRedirect("UserListServlet");
		}

		//全部更新するとき
		else {


			try {
				String PasA = Common.Ango(password);
				String PasB = Common.Ango(checkPassword);

				UserDao UpDao = new UserDao();
				UpDao.UpdateAll(PasA, Name, birthDate, Integer.parseInt(id));

				if (PasA.equals("") || Name.equals("") || birthDate.equals("") || !(PasA.equals(PasB))) {

					request.setAttribute("errMsg", "入力された内容は正しくありません。");

					UserDao UpdateDao = new UserDao();
					User use = UpdateDao.detailAll(id);
					request.setAttribute("id", use);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserUpdate.jsp");
					dispatcher.forward(request, response);
					return;

				}

			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}

			response.sendRedirect("UserListServlet");
		}
	}
}

