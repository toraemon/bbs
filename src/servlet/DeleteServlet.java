package servlet;

import static check.AccountCheck.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.Account;
import modelLogic.DeleteAccountLogic;
import modelLogic.GetAccountLogic;
import modelLogic.SetAccountLogic;
import check.RetainMessage;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = aliveCheck(request, response);
		boolean flagCheck = flagCheck(request, response);
		if(loginCheck==true && aliveCheck==true && flagCheck==true){
			doPost(request, response);
		}else if(loginCheck==false){
			response.sendRedirect("/bbs/index.jsp");
			return;
		}else if(aliveCheck==false){
			RetainMessage.retainMessage("お使いのアカウントは現在凍結されています。", request);
			response.sendRedirect("/LogoutServlet");
			return;
		}else{
			request.setAttribute("ErrorMsg", "管理者権限がありません。");
			response.sendRedirect("/bbs/RenewalServlet");
			return;
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Account account = GetAccountLogic.getAccount(request, response);
		boolean result = new DeleteAccountLogic().execute(account);
		if(result==false){
			request.setAttribute("ErrorMsg", "ユーザーを削除できませんでした。");
		}
		List<Account> accountList = new GetAccountLogic().execute();
		SetAccountLogic.setAccountList(request, response, accountList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
		dispatcher.forward(request, response);
	}
}