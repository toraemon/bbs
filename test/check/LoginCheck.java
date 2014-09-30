/*package check;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import modelData.Account;
import modelLogic.GetAccountLogic;

public class LoginCheck{
	public static boolean loginCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Account loginAccount = GetAccountLogic.getLoginAccount(request, response);
		if(loginAccount==null){
			return false;
		}else{
			return true;
		}
	}
	public static boolean aliveCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = GetAccountLogic.getLoginAccount(request, response).getId();
		Account loginAccount = GetAccountLogic.accountState(id);
		if(loginAccount.getAlive()==0){
			return false;
		}else{
			return true;
		}
	}
	public static boolean flagCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = GetAccountLogic.getLoginAccount(request, response).getId();
		Account loginAccount = GetAccountLogic.accountState(id);
		if(loginAccount.getFlag()==0){
			return false;
		}else{
			return true;
		}
	}
}*/
