package common.wrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Wrapper extends HttpServletRequestWrapper {
	
	private HttpServletRequest request;

	public Wrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	
	public String[] getRequestURIArray() {
		String uri = this.getRequestURI();
		String[] uriArr = uri.split("/");
		return uriArr;
	}
	
	@Override
	public RequestDispatcher getRequestDispatcher(String uri) {
		return request.getRequestDispatcher("/WEB-INF/views/" + uri + ".jsp");
	}
}
