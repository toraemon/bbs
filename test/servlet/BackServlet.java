/*package servlet;

import static check.LoginCheck.loginCheck;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 各ページから名ページに戻るためのサーブレット */
/*@WebServlet("/BackServlet")
public class BackServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// ログインチェックを行い、ログインしていたらメイン画面へ、していなければトップ画面へ飛ばす
		boolean result = loginCheck(request, response);
		if(result==true){
			RequestDispatcher dispathcer = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispathcer.forward(request, response);
		}else{
			response.sendRedirect("/bbs/index.jsp");
		}
	}
}*/