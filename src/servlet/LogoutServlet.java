package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		request.setAttribute("ErrorMsg", session.getAttribute("ErrorMsg"));
		request.setAttribute("LogoutMsg", "ログアウトしました。<br>またのご利用をお待ちしています。<br>5秒後にトップページに戻ります。");
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp");
		dispatcher.forward(request, response);
	}
}