package modelData;

import java.io.Serializable;

public class Contribution implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private String userId;
	private String title;
	private String text;
	private String date;
	private String name;
	private int page;
	
	public Contribution(String title, String text){
		this.title = title;
		this.text = text;
	}
	public Contribution(String userId, String title, String text, String date){
		this.userId = userId;
		this.title = title;
		this.text = text;
		this.date = date;
	}
	public Contribution(int id, String name, String title, String text, String date){
		this.id = id;
		this.name = name;
		this.title = title;
		this.text = text;
		this.date = date;
		this.name = name;
	}
	public int getId(){
		return id;
	}
	public String getUserId(){
		return userId;
	}
	public String getTitle(){
		return title;
	}
	public String getText(){
		return text;
	}
	public String getDate(){
		return date;
	}
	public String getName(){
		return name;
	}
	public int getPage(){
		return page;
	}
}