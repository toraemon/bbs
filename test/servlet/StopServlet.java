/*package servlet;

import static check.LoginCheck.loginCheck;
import static check.ThreatMeasures.htmlspecialchars;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.Account;
import modelLogic.GetAccountListLogic;
import modelLogic.UpdateAccountLogic;

/**
 * Servlet implementation class StopServlet
 *//*
@WebServlet("/StopServlet")
public class StopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   /* public StopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result = loginCheck(request, response);
		if(result==true){
			response.sendRedirect("/bbs/");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 取得したリクエストパラメータを取得
		int id = Integer.parseInt(htmlspecialchars(request.getParameter("id")));
		String accountId = htmlspecialchars(request.getParameter("accountId"));
		String pass = htmlspecialchars(request.getParameter("pass"));
		String name = htmlspecialchars(request.getParameter("name"));
		int flag = Integer.parseInt(htmlspecialchars(request.getParameter("flag")));
		int alive = Integer.parseInt(htmlspecialchars(request.getParameter("alive")));
		
		// 取得したリクエストパラメータをstopAccountに格納
		Account stopAccount = new Account(id, accountId, pass, name, flag, alive);
		
		boolean result = new UpdateAccountLogic().stop(stopAccount);
		if(result==false){
			request.setAttribute("ErrorMsg", "ユーザー編集でエラーが生じました。");
		}
		GetAccountListLogic.getAccountData(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
		dispatcher.forward(request, response);
	}

}*/
