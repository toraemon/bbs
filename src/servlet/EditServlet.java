package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.Account;
import modelLogic.GetAccountLogic;
import modelLogic.UpdateAccountLogic;
import static modelLogic.SetAccountLogic.*;
import static check.ThreatMeasures.htmlspecialchars;
import static check.AccountCheck.*;
import static check.FormCheck.editAccountCheck;
import static check.RetainMessage.*;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet{
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
				path = "/LogoutServlet";
			}
			else if(flagCheck==false){
				request.setAttribute("ErrorMsg", "管理者権限がありません。");
				path = "/RenewalServlet";
			}else{
				GetAccountLogic.getAccountData(request, response);
				path = "/WEB-INF/jsp/editUserForm.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
		
		String action = request.getParameter("action");
		int id = Integer.parseInt(htmlspecialchars(request.getParameter("id")));
		String accountId = htmlspecialchars(request.getParameter("accountId"));
		String pass = htmlspecialchars(request.getParameter("pass"));
		String name = htmlspecialchars(request.getParameter("name"));
		int flag = Integer.parseInt(htmlspecialchars(request.getParameter("flag")));
		int alive = Integer.parseInt(htmlspecialchars(request.getParameter("alive")));
		
		if(action.equals("edit")){
			Account account = new Account(id, accountId, pass, name, flag, alive);
			// 更新するために必要な変更前の情報をセッションに保存
			setAccount(request, response, account);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editUserForm.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("editDone")){
			String passCheck = htmlspecialchars(request.getParameter("passCheck"));
			String check = editAccountCheck(accountId, pass, passCheck, name);
			if(!check.equals("")){
				Account editAccount = new Account(accountId, pass, passCheck, name);
				request.setAttribute("editAccount", editAccount);
				request.setAttribute("ErrorMsg", check);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editUserForm.jsp");
				dispatcher.forward(request, response);
				return;
			}
			Account updateAccount = new Account(id, accountId, pass, name, flag, alive);
			// 更新する変更後の情報をセッション保存。
			setUpdateAccount(request, response, updateAccount);
			// 事前に保存していた更新前の情報を取得。
			Account account = GetAccountLogic.getAccount(request, response);
			boolean result = new UpdateAccountLogic().execute(updateAccount, account);
			if(result==false){
				request.setAttribute("ErrorMsg", "ユーザー編集でエラーが生じました。");
			}
			// ログインアカウントが自身を変更するとき、ずれが生じるため、変更後の情報をログイン情報を格納する
			Account loginAccount = GetAccountLogic.getLoginAccount(request, response);
			if(loginAccount.getId()==updateAccount.getId()){
				setLoginAccount(request, response, updateAccount);
			}
			GetAccountLogic.getAccountData(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
			dispatcher.forward(request, response);
		}
	}
}