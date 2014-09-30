package modelData;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String accountId;
	private String pass;
	private String passCheck;
	private String name;
	private int flag;
	private int alive;
	
	public Account(){}
	public Account(String accountId, String pass){
		this.accountId = accountId;
		this.pass = pass;
	}
	public Account(int id, String pass){
		this.id = id;
		this.pass = pass;
	}
	public Account(int id, String accountId, String name, int flag, int alive){
		this.id = id;
		this.accountId = accountId;
		this.name = name;
		this.flag = flag;
		this.alive = alive;
	}
	public Account(String accountId, String pass, String passCheck, String name){
		this.accountId = accountId;
		this.pass = pass;
		this.passCheck = passCheck;
		this.name = name;
	}
	public Account(String accountId, String pass, String name, int flag, int alive){
		this.accountId = accountId;
		this.pass = pass;
		this.name = name;
		this.flag = flag;
		this.alive = alive;
	}
	public Account(int id, String accountId, String pass, String name, int flag, int alive){
		this.id = id;
		this.accountId = accountId;
		this.pass = pass;
		this.name = name;
		this.flag = flag;
		this.alive = alive;
	}
	public int getId(){
		return id;
	}
	public String getAccountId(){
		return accountId;
	}
	public String getPass(){
		return pass;
	}
	public String getPassCheck(){
		return passCheck;
	}
	public String getName(){
		return name;
	}
	public int getFlag(){
		return flag;
	}
	public int getAlive(){
		return alive;
	}
}