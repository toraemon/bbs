/*package modelLogic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import modelData.Account;

public class AccountLogic{
	public static void getAccountData(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Account> accountList = new GetAccountListLogic().execute();
		request.setAttribute("accountList", accountList);
	}
}*/