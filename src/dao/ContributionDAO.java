package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelData.Contribution;

public class ContributionDAO{
	// 投稿データをすべて取り尽くすメソッド
	public List<Contribution> findAll(){
		Connection con = null;
		List<Contribution> contributionList = new ArrayList<Contribution>();
		try{
			con = CommonDAO.getConnection();
			String sql = "SELECT contribution.id, name, title, text, date FROM contribution"
							+ " INNER JOIN account ON account.id = contribution.userId"
							+ " WHERE date <= NOW() ORDER BY date DESC";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String date = rs.getString("date");
				Contribution contribution = new Contribution(id, name, title, text, date);
				contributionList.add(contribution);
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
		return contributionList;
	}		
	
	// 投稿を掲示板に追加するメソッド
	public boolean create(Contribution contribution){
		Connection con = null;
		try{
			con = CommonDAO.getConnection();
			PreparedStatement ps;
			if(!contribution.getDate().equals("")){
				String sql = "INSERT INTO contribution (userId, title, text, date) VALUES (?, ?, ?, ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, contribution.getUserId());
				ps.setString(2, contribution.getTitle());
				ps.setString(3, contribution.getText());
				ps.setString(4, contribution.getDate());
			}else{
				String sql = "INSERT INTO contribution (userId, title, text) VALUES (?, ?, ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, contribution.getUserId());
				ps.setString(2, contribution.getTitle());
				ps.setString(3, contribution.getText());
			}
			int result = ps.executeUpdate();
			if(result!=1){
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			CommonDAO.close(con);
		}
		return true;
	}
	
	/* 投稿数を返すメソッド */
	public int getCount(){
		Connection con = null;
		int count = 0;
		try{
			con = CommonDAO.getConnection();
			String sql = "SELECT COUNT(id) FROM contribution";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count(id)");
			}
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			CommonDAO.close(con);
		}
		return count;
	}
}