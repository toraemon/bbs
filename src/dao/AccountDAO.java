package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelData.Account;

public class AccountDAO{
	public boolean exist(String accountId){
		Connection con = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "SELECT accountId FROM account WHERE accountID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, accountId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				accountId = rs.getString("accountId");
				return true;
			}
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			CommonDAO.close(con);
		}
		return false;
	}
	public Account accountState(int id){
		Connection con = null;
		Account account = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "SELECT id, accountId, name, flag, alive FROM account WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
				String accountId = rs.getString("accountId");
				String name = rs.getString("name");
				int flag = rs.getInt("flag");
				int alive = rs.getInt("alive");
				account = new Account(id, accountId, name, flag, alive);
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
		return account;
	}
	// データベースに該当するデータがあるかチェックし、該当すれば情報を返すメソッド
	public Account findByLogin(Account login){
		Connection con = null;
		Account account = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "SELECT id, accountId, pass, name, flag, alive FROM account WHERE accountId=? AND pass=PASSWORD(?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login.getAccountId());
			ps.setString(2, login.getPass());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				int id = rs.getInt("id");
				String accountId = rs.getString("accountId");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int flag = rs.getInt("flag");
				int alive = rs.getInt("alive");
				account = new Account(id, accountId, pass, name, flag, alive);
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
		return account;
	}
	
	// 新しいアカウントをアカウント管理に追加するメソッド
	public boolean create(Account account){
		Connection con = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "INSERT INTO account (accountId, pass, name, flag) VALUES (?, PASSWORD(?), ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account.getAccountId());
			ps.setString(2, account.getPass());
			ps.setString(3, account.getName());
			ps.setInt(4, account.getFlag());
			int result = ps.executeUpdate();
			if(result!=1){
				return false;
			}
		}catch(SQLException|ClassNotFoundException e){
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
	
	// アカウントデータをすべて取得するメソッド
	public List<Account> findAll(){
		Connection con = null;
		List<Account> accountList = new ArrayList<Account>();
		try{
			con = CommonDAO.getConnection();
			String sql = "SELECT id, accountId, pass, name, flag, alive FROM account";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String accountId = rs.getString("accountId");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int flag = rs.getInt("flag");
				int alive = rs.getInt("alive");
				Account account = new Account(id, accountId, pass, name, flag, alive);
				accountList.add(account);
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
		return accountList;
	}
	
	// アカウントを停止状態に変更するメソッド
	public boolean stop(Account stopAccount){
		Connection con = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "UPDATE account SET alive=0 WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, stopAccount.getId());
			ps.executeUpdate();
		}catch(SQLException|ClassNotFoundException e){
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
	
	// アカウントを復活状態に変更させるメソッド
	public boolean run(Account runAccount){
		Connection con = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "UPDATE account SET alive=1 WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, runAccount.getId());
			ps.executeUpdate();
		}catch(SQLException|ClassNotFoundException e){
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
	
	// アカウント情報を変更するメソッド
	public boolean update(Account updateAccount, Account account){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = CommonDAO.getConnection();
		if(!updateAccount.getPass().equals("")){
			String sql = "UPDATE account SET accountId=?, pass=PASSWORD(?), name=?, flag=?, alive=? WHERE id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, updateAccount.getAccountId());
			ps.setString(2, updateAccount.getPass());
			ps.setString(3, updateAccount.getName());
			ps.setInt(4, updateAccount.getFlag());
			ps.setInt(5, updateAccount.getAlive());
			ps.setInt(6, account.getId());
		}else{
			String sql = "UPDATE account SET accountId=?, name=?, flag=?, alive=? WHERE id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, updateAccount.getAccountId());
			ps.setString(2, updateAccount.getName());
			ps.setInt(3, updateAccount.getFlag());
			ps.setInt(4, updateAccount.getAlive());
			ps.setInt(5, account.getId());
		}
			ps.executeUpdate();
		}catch(SQLException|ClassNotFoundException e){
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			CommonDAO.close(con);
		}
		return true;
	}
	
	// アカウントを削除するメソッド
	public boolean delete(Account account){
		Connection con = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "DELETE FROM account WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, account.getId());
			ps.executeUpdate();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			CommonDAO.close(con);
			System.out.println("delete");
		}
		return true;
	}
	
	/* アカウントのパスワードを変更するメソッド */
	public boolean reRegist(Account reRegistAccount){
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = CommonDAO.getConnection();
			String sql = "UPDATE account SET pass=PASSWORD(?) WHERE accountId=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, reRegistAccount.getPass());
			ps.setString(2, reRegistAccount.getAccountId());
			ps.executeUpdate();
		}catch(SQLException|ClassNotFoundException e){
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