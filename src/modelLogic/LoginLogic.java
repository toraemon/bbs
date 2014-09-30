package modelLogic;

import modelData.Account;
import dao.AccountDAO;

public class LoginLogic{
	public Account execute(Account login){
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(login);
		return account;
	}
}