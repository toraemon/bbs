/*package check;

import java.io.*;
import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("/servlet/*")
public class LoginFilter implements Filter{
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException{

        HttpSession session = ((HttpServletRequest)req).getSession();

        if (session.getAttribute("Login.done") == null){
            System.out.println("LoginFilter:Login not done");
            session.setAttribute("Login.to", ((HttpServletRequest)req).getRequestURI());
            ((HttpServletResponse)res).sendRedirect("/index.jsp");
        }else{
            System.out.println("LoginFilter:Login done!");
            chain.doFilter(req, res);
            return;
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException{
    }

    public void destroy(){
    }
}
*/