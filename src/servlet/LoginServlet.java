package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.Account;
import modelLogic.LoginLogic;
import modelLogic.SetAccountLogic;
import static check.AccountCheck.*;
import static check.ThreatMeasures.htmlspecialchars;
import static check.RetainMessage.retainMessage;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = aliveCheck(request, response);
		if(loginCheck==false){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else if(aliveCheck==false){
			retainMessage("お使いのアカウントは現在凍結されています。", request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LogoutServlet");
			dispatcher.forward(request, response);
		}else{
			doPost(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String accountId = htmlspecialchars(request.getParameter("accountId"));
		String pass = htmlspecialchars(request.getParameter("pass"));
		
		Account login = new Account(accountId, pass);
		Account loginAccount = new LoginLogic().execute(login);
		if(loginAccount==null || loginAccount.getAlive()==0){
			if(loginAccount==null){
				request.setAttribute("ErrorMsg", "登録情報が確認できません。");
			}else{
				request.setAttribute("ErrorMsg", "お使いのアカウントは現在凍結されています。");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else{
			SetAccountLogic.setLoginAccount(request, response, loginAccount);
			response.sendRedirect("/bbs/RenewalServlet");
		}
	}
}