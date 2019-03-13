package controller;

import java.io.IOException;

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
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDetailServlet() {
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
		}

		// URLからGETパラメータとしてIDを受け取る
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");

		// 確認用：idをコンソールに出力
		//System.out.println(id);

		// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する

		UserDao detailDao = new UserDao();
		User use = detailDao.detailAll(id);
		request.setAttribute("id", use);

		User U = (User) request.getAttribute("id");

		// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserDtail.jsp");
		dispatcher.forward(request, response);

	}

}
