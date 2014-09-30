package modelLogic;

import modelData.Account;
import dao.AccountDAO;

public class UpdateAccountLogic {
	public boolean execute(Account updateAccount, Account account){
		AccountDAO dao = new AccountDAO();
		boolean result = dao.update(updateAccount, account);
		return result;
	}
	public boolean stop(Account stopAccount){
		AccountDAO dao = new AccountDAO();
		boolean result = dao.stop(stopAccount);
		return result;
	}
	public boolean run(Account runAccount){
		AccountDAO dao = new AccountDAO();
		boolean result = dao.run(runAccount);
		return result;
	}
	public boolean reRegist(Account reRegistAccount){
		AccountDAO dao = new AccountDAO();
		boolean result = dao.reRegist(reRegistAccount);
		return result;
	}
	/*
	public boolean exist(String accountId){
		AccountDAO dao = new AccountDAO();
		boolean check = dao.exist(accountId);
		return check;
	}
	*/
}