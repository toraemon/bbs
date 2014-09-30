package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelData.Contribution;
import modelLogic.GetAccountLogic;
import modelLogic.PostDataLogic;
import static check.AccountCheck.*;
import static check.ThreatMeasures.htmlspecialchars;
import static check.FormCheck.contributionFormCheck;
import static check.RetainMessage.*;

@WebServlet("/ContributionServlet")
public class ContributionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = null;
		boolean loginCheck = loginCheck(request, response);
		boolean aliveCheck = false;
		if (loginCheck == false) {
			request.setAttribute("ErrorMsg", "ログインしてください。");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			aliveCheck = aliveCheck(request, response);
			if (aliveCheck == false) {
				retainMessage("お使いのアカウントは現在凍結されています。", request);
				path = "/LogoutServlet";
			} else {
				GetAccountLogic.getAccountData(request, response);
				path = "/WEB-INF/jsp/contributionForm.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean aliveCheck = aliveCheck(request, response);
		if (aliveCheck == false) {
			retainMessage("お使いのアカウントは現在凍結されています。", request);
			response.sendRedirect("/bbs/LogoutServlet");
			return;
		}
		String userId = htmlspecialchars(request.getParameter("userId"));
		String title = htmlspecialchars(request.getParameter("title"));
		String text = htmlspecialchars(request.getParameter("text"));
		String date = htmlspecialchars(request.getParameter("date"));
		// サーバー側のフォームチェック
		String check = contributionFormCheck(title, text, date);
		if (!check.equals("")) {
			Contribution contribution = new Contribution(title, text);
			request.setAttribute("ErrorMsg", check);
			request.setAttribute("contribution", contribution);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsp/contributionForm.jsp");
			dispatcher.forward(request, response);
			return;
		}
		// コンストラクタを生成し、投稿をINSERTする処理に飛ばす
		Contribution contribution = new Contribution(userId, title, text, date);
		boolean result = new PostDataLogic().execute(contribution);
		if (result == false) {
			request.setAttribute("ErrorMsg", "新規投稿時にエラーが発生しました。");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}