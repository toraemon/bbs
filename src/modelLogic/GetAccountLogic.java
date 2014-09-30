/* アカウント情報を取得する処理 */

package modelLogic;

import java.io.IOException;
import java.util.List;

import modelData.Account;
import dao.AccountDAO;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class GetAccountLogic{
	public List<Account> execute(){
		AccountDAO dao = new AccountDAO();
		List<Account> accountList = dao.findAll();
		return accountList;
	}
	public static Account accountState(int id){
		AccountDAO dao = new AccountDAO();
		Account account = dao.accountState(id);
		return account;
	}
	public static void getAccountData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Account> accountList = new GetAccountLogic().execute();
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
}