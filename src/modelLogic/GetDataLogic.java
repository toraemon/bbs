/* 投稿内容とコメントのデータを格納する処理 */

package modelLogic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

// import dao.ContributionDAO;
import modelData.Comment;
import modelData.Contribution;

public class GetDataLogic{
	public static void getDataLogic(HttpServletRequest request, HttpServletResponse response/*,int page*/) throws ServletException, IOException{
		HttpSession session = request.getSession();
		List<Contribution> contributionList = new GetContributionListLogic().execute();
		session.setAttribute("contributionList", contributionList);
		List<Comment> commentList = new GetCommentListLogic().execute();
		session.setAttribute("commentList", commentList);
	}
}
