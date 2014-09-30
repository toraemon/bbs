package modelData;

import java.io.Serializable;

public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userId;
	private String conId;
	private String comment;
	private String date;
	private String name;
	
	public Comment(){}
	public Comment(String userId, String conId, String comment){
		this.userId = userId;
		this.conId = conId;
		this.comment = comment;
	}
	public Comment(String name, String comment, String date, String userId, String conId){
		this.name = name;
		this.comment = comment;
		this.date = date;
		this.userId = userId;
		this.conId = conId;
	}
	public int getId(){
		return id;
	}
	public String getUserId(){
		return userId;
	}
	public String getConId(){
		return conId;
	}
	public String getComment(){
		return comment;
	}
	public String getDate(){
		return date;
	}
	public String getName(){
		return name;
	}
}