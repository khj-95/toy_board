package common.exception.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.exception.HandleableException;

@WebServlet("/exception-handler")
public class ExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExceptionHandler() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 컨테이너는 HandleableException이 발생하면 요청을 HandleableException으로 재지정
		// 이때 ExceptionHandler를 서비스 메서드로 넘겨주는 request의 속성(javax.servlet.error.exception)에 
		// 발생한 예외 객체를 함께 넘겨준다.
		HandleableException e = (HandleableException) request.getAttribute("javax.servlet.error.exception");
		
		request.setAttribute("msg", e.error.MSG);
		if(e.error.URL != null) {
			request.setAttribute("url", e.error.URL);
		} else {
			request.setAttribute("back", "back");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
