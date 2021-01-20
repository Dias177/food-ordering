package kz.epam.tcfp.foodordering.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final String INIT_PARAM_NAME_ENCODING = "encoding";
    private static final String INIT_PARAM_NAME_TYPE = "type";
    private String code;
    private String type;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(INIT_PARAM_NAME_ENCODING);
        type = filterConfig.getInitParameter(INIT_PARAM_NAME_TYPE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        servletResponse.setContentType(type);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        code = null;
        type = null;
    }
}
