package servlet;

import static check.AccountCheck.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelLogic.GetAccountLogic;
import modelLogic.GetDataLogic;

@WebServlet("/RenewalServlet")
public class RenewalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = aliveCheck(request, response);
		if(loginCheck==false){
			if(aliveCheck==false){
				request.setAttribute("ErrorMsg", "お使いのアカウントは現在凍結されています。");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else{
			doPost(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 投稿とコメントを取得するメソッドの呼び出し
		GetDataLogic.getDataLogic(request, response);
		int id = GetAccountLogic.getLoginAccount(request, response).getId();
		request.setAttribute("loginAccount", GetAccountLogic.accountState(id));
		// 取得した後にホーム画面に遷移させる。
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
