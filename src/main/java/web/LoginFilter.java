package web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //有3个请求不需要过滤，排除它们
        String[] paths = new String[] {"/toLogin.do", "/login.do", "/createImg.do"};
        //获取当前请求路径
        String sp = request.getServletPath();
        for (String p : paths) {
            //若当前路径是上述3个路径之一
            if (p.equals(sp)) {
                chain.doFilter(request, response);
                return;
            }
        }
        //从session里尝试获取账号
        HttpSession session = request.getSession();
        String adminCode = (String) session.getAttribute("adminCode");
        //根据账号判断用户是否登录
        if (adminCode == null) {
            //未登录,重定向到登录界面
            response.sendRedirect("toLogin.do");
        } else {
            //已登录，请求继续执行
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
