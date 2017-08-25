package LoginCheck.Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by LHF on 2017/7/6.
 */

//登录过滤器，防止未经登录即进入页面
public class LoginFilter implements Filter {
    private static String LOGIN_PAGE="index.jsp";
    public void init(FilterConfig arg0) throws ServletException {

    }
    public void destory(){

    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
        HttpServletRequest request1=(HttpServletRequest)request;
        HttpServletResponse response1=(HttpServletResponse)response;
        HttpSession session=request1.getSession();
        String currentUrl=request1.getRequestURI();
        //此处语句的与或使用有待考究
        if (currentUrl.contains(LOGIN_PAGE)&&currentUrl.contains("login.jsp")){
        if(session.getAttribute("Username")==null){
            System.out.println("请求URL"+currentUrl);
            if(session==null||session.getAttribute("Username")==null){
                response1.sendRedirect(request1.getContextPath()+LOGIN_PAGE);
            }

        }
        }
        filterChain.doFilter(request,response);
    }
}
