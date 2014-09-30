package modelLogic;

import java.util.List;

import modelData.Comment;
import dao.CommentDAO;

public class GetCommentListLogic{
	public List<Comment> execute(){
		CommentDAO dao = new CommentDAO();
		List<Comment> commentList = dao.findAll();
		return commentList;
	}
}