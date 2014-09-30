package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modelData.Account;
import modelLogic.PostDataLogic;
import modelLogic.GetAccountLogic;
import static check.AccountCheck.*;
import static check.ThreatMeasures.htmlspecialchars;
import static check.FormCheck.registAccountCheck;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = null;
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = aliveCheck(request, response);
		boolean flagCheck = flagCheck(request, response);
		if(loginCheck==false){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else{
			if(aliveCheck==false){
				request.setAttribute("ErrorMsg", "お使いのアカウントは現在凍結されています。");
				path = "/index.jsp";
			}
			else if(flagCheck==false){
				request.setAttribute("ErrorMsg", "管理者権限がありません。");
				path = "/WEB-INF/jsp/main.jsp";
			}else{
				GetAccountLogic.getAccountData(request, response);
				path = "/WEB-INF/jsp/registUserForm.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// リクエストパラメータを取得
		boolean aliveCheck = aliveCheck(request, response);
		boolean flagCheck = flagCheck(request, response);
		if(aliveCheck==false){
			request.setAttribute("ErrorMsg", "お使いのアカウントは現在凍結されています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LogoutServlet");
			dispatcher.forward(request, response);
		}else if(flagCheck==false){
			request.setAttribute("ErrorMsg", "お使いのアカウントは管理者権限がありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
		String accountId = htmlspecialchars(request.getParameter("accountId"));
		String pass = htmlspecialchars(request.getParameter("pass"));
		String passCheck = htmlspecialchars(request.getParameter("passCheck"));
		String name = htmlspecialchars(request.getParameter("name"));
		int flag = Integer.parseInt(htmlspecialchars(request.getParameter("flag")));
		int alive = Integer.parseInt(htmlspecialchars(request.getParameter("alive")));
		String check = registAccountCheck(accountId, pass, passCheck, name);
		if(!check.equals("")){
			Account registAccount = new Account(accountId, pass, passCheck, name);
			request.setAttribute("registAccount", registAccount);
			request.setAttribute("ErrorMsg", check);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registUserForm.jsp");
			dispatcher.forward(request, response);
			return;
		}
		Account account = new Account(accountId, pass, name, flag, alive);
		boolean result = new PostDataLogic().execute(account);
		if(result==false){
			request.setAttribute("ErrorMsg", "情報登録が正常にできませんでした。");
		}
		GetAccountLogic.getAccountData(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
		dispatcher.forward(request, response);
	}
}