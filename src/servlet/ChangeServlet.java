package servlet;

import static check.AccountCheck.*;
import static check.ThreatMeasures.htmlspecialchars;
import static check.RetainMessage.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.Account;
import modelLogic.UpdateAccountLogic;
import modelLogic.GetAccountLogic;

/* ユーザー状態変更のサーブレット */

@WebServlet("/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = aliveCheck(request, response);
		boolean flagCheck = flagCheck(request, response);
		if(loginCheck==false){
			request.setAttribute("ErrorMsg", "ログインしてください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			return;
		}else{
			if(aliveCheck==false){
				request.setAttribute("ErrorMsg", "お使いのアカウントは現在凍結されています。");
				path = "/LogoutServlet";
			}
			else if(flagCheck==false){
				request.setAttribute("ErrorMsg", "管理者権限がありません。");
				path = "/WEB-INF/jsp/main.jsp";
			}else{
				GetAccountLogic.getAccountData(request, response);
				path = "/WEB-INF/jsp/manageUser.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
			return;
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean aliveCheck = aliveCheck(request, response);
		boolean flagCheck = flagCheck(request, response);
		if(aliveCheck==false){
			retainMessage("お使いのアカウントは現在凍結されています。", request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LogoutServlet");
			dispatcher.forward(request, response);
			return;
		}else if(flagCheck==false){
			request.setAttribute("ErrorMsg", "お使いのアカウントは管理者権限がありません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/RenewalServlet");
			dispatcher.forward(request, response);
			return;
		}
		// 取得したリクエストパラメータを取得
		int id = Integer.parseInt(htmlspecialchars(request.getParameter("id")));
		String accountId = htmlspecialchars(request.getParameter("accountId"));
		String pass = htmlspecialchars(request.getParameter("pass"));
		String name = htmlspecialchars(request.getParameter("name"));
		int flag = Integer.parseInt(htmlspecialchars(request.getParameter("flag")));
		int alive = Integer.parseInt(htmlspecialchars(request.getParameter("alive")));
		// 取得したリクエストパラメータをrunAccountに格納
		Account changeAccount = new Account(id, accountId, pass, name, flag, alive);
		boolean result = false;
		// ユーザー状態は1が有効,0が無効。Updateが正常に実行されたらtrueが入る。
		if(alive==0){
			result = new UpdateAccountLogic().run(changeAccount);
		}else if(alive==1){
			result = new UpdateAccountLogic().stop(changeAccount);
		}
		if(result==false){
			request.setAttribute("ErrorMsg", "ユーザー編集でエラーが生じました。");
		}
		// アカウントデータを取得してフォワードする
		GetAccountLogic.getAccountData(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
		dispatcher.forward(request, response);
	}
}
