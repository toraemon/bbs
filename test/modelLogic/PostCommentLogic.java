/*package modelLogic;

import modelData.Comment;
import dao.CommentDAO;

public class PostCommentLogic{
	public boolean execute(Comment commentData){
		CommentDAO dao = new CommentDAO();
		boolean result;
		try{
			result = dao.create(commentData);
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
}*/