package servlet;
import static check.AccountCheck.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelLogic.GetAccountLogic;

import static check.RetainMessage.retainMessage;

@WebServlet("/ManageServlet")
public class ManageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// ログインチェック
		String path = null;
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = aliveCheck(request, response);
		boolean flagCheck = flagCheck(request, response);
		if(loginCheck==false){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else{
			if(aliveCheck==false){
				retainMessage("お使いのアカウントは現在凍結されています。", request);
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
		}
	}
}