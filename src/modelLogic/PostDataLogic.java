package modelLogic;

import modelData.Account;
import modelData.Comment;
import modelData.Contribution;

import dao.AccountDAO;
import dao.CommentDAO;
import dao.ContributionDAO;

public class PostDataLogic {
	// アカウント情報を登録するメソッド
	public boolean execute(Account account){
		AccountDAO dao = new AccountDAO();
		boolean result = dao.create(account);
		return result;
	}
	// コメントを登録するメソッド
	public boolean execute(Comment commentData){
		CommentDAO dao = new CommentDAO();
		boolean result = dao.create(commentData);
		return result;
	}
	// 新規投稿を登録するメソッド
	public boolean execute(Contribution contribution){
		ContributionDAO dao = new ContributionDAO();
		boolean result = dao.create(contribution);
		return result;
	}
}