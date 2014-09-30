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

@WebServlet("/RunServlet")
public class RunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RunServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result = loginCheck(request, response);
		if(result==true){
			response.sendRedirect("/bbs/RenewalServlet");
		}else{
			response.sendRedirect("/bbs/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		if(alive==1){
			result = new UpdateAccountLogic().run(changeAccount);
		}else if(alive==0){
			result = new UpdateAccountLogic().stop(changeAccount);
		}
		if(result==false){
			request.setAttribute("ErrorMsg", "ユーザー編集でエラーが生じました。");
		}
	// 変更後のアカウントを取得してリクエストスコープに保存
		GetAccountListLogic.getAccountData(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/manageUser.jsp");
		dispatcher.forward(request, response);
	}
}
*/