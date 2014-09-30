package check;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class RetainMessage {
	public static void retainMessage(String message, HttpServletRequest request) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("ErrorMsg", message);
	}
}
