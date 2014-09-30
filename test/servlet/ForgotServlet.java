/*package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelData.Account;
import modelLogic.UpdateAccountLogic;
import static check.LoginCheck.loginCheck;
import static check.ThreatMeasures.htmlspecialchars;

/**
 * Servlet implementation class ForgotServlet
 *//*
@WebServlet("/ForgotServlet")
public class ForgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /*public ForgotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean result = loginCheck(request, response);
		if(result==true){
			request.setAttribute("ErrorMsg", "不正ログインの可能性があるので一度ログアウトします。");
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forgotUserForm.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accountId = htmlspecialchars(request.getParameter("accountId"));
		String pass = htmlspecialchars(request.getParameter("pass"));
		// 取得したリクエストパラメータをrunAccountに格納
		Account reRegistAccount = new Account(accountId, pass);
		boolean check = new UpdateAccountLogic().exist(accountId);
		boolean result = new UpdateAccountLogic().reRegist(reRegistAccount);
		RequestDispatcher dispatcher;
		if(check==false || result==false){
			if(check==false){
				request.setAttribute("ErrorMsg", "アカウントIDが見つかりません。");
			}else{
				request.setAttribute("ErrorMsg", "アカウントIDとパスワードが既に登録されている可能性があります。");
			}
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forgotUserForm.jsp");
		}else{
			request.setAttribute("Msg", "パスワードの変更が完了しました。");
			dispatcher = request.getRequestDispatcher("/index.jsp");
		}
		dispatcher.forward(request, response);
	}

}*/
