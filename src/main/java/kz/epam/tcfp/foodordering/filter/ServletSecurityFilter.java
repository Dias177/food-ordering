package kz.epam.tcfp.foodordering.filter;

import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletSecurityFilter implements Filter {

    private static final String ATTR_NAME_USER_ROLE = "userRole";
    private static final String PATH_PAGE_LOGIN = "path.page.login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session.getAttribute(ATTR_NAME_USER_ROLE) == null) {
            RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty(PATH_PAGE_LOGIN));
            dispatcher.forward(req, res);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
