package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import common.wrapper.Wrapper;

@WebFilter("/*")
public class WrapperFilter implements Filter {

    public WrapperFilter() {}

	public void destroy() {}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		Wrapper requestWrapper = new Wrapper((HttpServletRequest) request);
		
		chain.doFilter(requestWrapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
