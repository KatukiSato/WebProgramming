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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("userInfo");

		if (u == null) {
			response.sendRedirect("LoginServlet");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");

		UserDao DeleteDao = new UserDao();
		User use = DeleteDao.detailAll(id);
		request.setAttribute("id", use);

		User U = (User) request.getAttribute("id");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		UserDao DeleteDao = new UserDao();
		User use = DeleteDao.Delete(Integer.parseInt(id));

		response.sendRedirect("UserListServlet");
	}

}
