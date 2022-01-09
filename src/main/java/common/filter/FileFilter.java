package common.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/file/*")
public class FileFilter implements Filter {

    public FileFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//파일명이 한글인 경우도 있어서 URLEncoder 클래스의 encode 메서드를 사용하여 UTF-8로 인코딩
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String originFileName = httpRequest.getParameter("originName");
		
		if(originFileName != null) {
			originFileName = URLEncoder.encode(httpRequest.getParameter("originName"),"UTF-8");
			httpResponse.setHeader("Content-Disposition", "attachment; fileName=" + originFileName);
		}
		chain.doFilter(request, httpResponse);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
