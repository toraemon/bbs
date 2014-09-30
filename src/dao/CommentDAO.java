package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelData.Comment;

public class CommentDAO{
	// 投稿データをすべて取り尽くすメソッド
	public List<Comment> findAll(){
		Connection con = null;
		List<Comment> commentList = new ArrayList<Comment>();
		try{
			con = CommonDAO.getConnection();
			String sql = "SELECT name, comment, date, userId, conId FROM comment"
					+ " INNER JOIN account ON comment.userId = account.id ORDER BY date DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String name = rs.getString("name");
				String comment = rs.getString("comment");
				String date = rs.getString("date");
				String userId = rs.getString("userId");
				String conId = rs.getString("conId");
				Comment commentData = new Comment(name, comment, date, userId, conId);
				commentList.add(commentData);
			}
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			CommonDAO.close(con);
		}
		return commentList;
	}


	
	// コメントをコメントDBに追加するメソッド
	public boolean create(Comment commentData){
		Connection con = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "INSERT INTO comment (userId, conId, comment) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, commentData.getUserId());
			ps.setString(2, commentData.getConId());
			ps.setString(3, commentData.getComment());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			CommonDAO.close(con);
		}
		return true;
	}
}