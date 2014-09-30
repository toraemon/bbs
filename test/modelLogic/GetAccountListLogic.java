/*package modelLogic;

import java.io.IOException;
import java.util.List;

import modelData.Account;
import dao.AccountDAO;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class GetAccountListLogic {
	public List<Account> execute(){
		AccountDAO dao = new AccountDAO();
		List<Account> accountList = dao.findAll();
		return accountList;
	}
	public static void getAccountData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Account> accountList = new GetAccountListLogic().execute();
		request.setAttribute("accountList", accountList);
	}
	public static Account getLoginAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		return (Account)session.getAttribute("loginAccount");
	}
	public static Account getAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		return (Account)session.getAttribute("account");
	}
}*/