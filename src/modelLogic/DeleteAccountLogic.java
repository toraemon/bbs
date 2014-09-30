package modelLogic;

import modelData.Account;
import dao.AccountDAO;

public class DeleteAccountLogic{
	public boolean execute(Account account){
		AccountDAO dao = new AccountDAO();
		boolean result = dao.delete(account);
		return result;
	}
}
