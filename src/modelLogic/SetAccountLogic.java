package modelLogic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelData.Account;

public class SetAccountLogic {
	public static void setAccount(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
	}
	public static void setAccountList(HttpServletRequest request, HttpServletResponse response, List<Account> accountList) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("accountList", accountList);
	}
	public static void setLoginAccount(HttpServletRequest request, HttpServletResponse response, Account loginAccount) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("loginAccount", loginAccount);
	}
	public static void setUpdateAccount(HttpServletRequest request, HttpServletResponse response, Account updateAccount) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("updateAccount", updateAccount);
	}
}
