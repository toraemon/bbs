package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.Comment;
import modelLogic.PostDataLogic;
import static check.AccountCheck.*;
import static check.ThreatMeasures.htmlspecialchars;
import static check.FormCheck.commentFormatCheck;
import static check.RetainMessage.retainMessage;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = false;
		if(loginCheck==false){
			request.setAttribute("ErrorMsg", "ログインしてください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			return;
		}else{
			aliveCheck = aliveCheck(request, response);
			if(aliveCheck==false){
				retainMessage("お使いのアカウントは現在凍結されています。", request);
				response.sendRedirect("/bbs/LogoutServlet");
				return;
			}else{
				response.sendRedirect("/bbs/RenewalServlet");
			}
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean aliveCheck = aliveCheck(request, response);
		if(aliveCheck==false){
			retainMessage("お使いのアカウントは現在凍結されています。", request);
			response.sendRedirect("/bbs/LogoutServlet");
			return;
		}
		String userId = htmlspecialchars(request.getParameter("userId"));
		String conId = htmlspecialchars(request.getParameter("conId"));
		String comment = htmlspecialchars(request.getParameter("comment"));
		// サーバー側のフォームチェック
		String check = commentFormatCheck(comment);
		if(!check.equals("")){
			request.setAttribute("ErrorMsg", check);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
			return;
		}
		Comment commentData = new Comment(userId, conId, comment);
		boolean result = new PostDataLogic().execute(commentData);
		if(result==false){
			request.setAttribute("ErrorMsg", "コメント投稿時にエラーが発生しました。");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/RenwalServlet");
		dispatcher.forward(request, response);
	}
}