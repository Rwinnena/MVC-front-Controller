package router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.BoardController;
import controller.Controller;
import controller.MainController;
import controller.RegisterController;
import controller.WriteController;

@MultipartConfig
public class URIRouter extends HttpServlet{
	
	private Map<String, Controller> urlMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		urlMap.put("/", new MainController());
		urlMap.put("/register", new RegisterController());
		urlMap.put("/board", new BoardController());
		urlMap.put("/board/write", new WriteController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uri = req.getRequestURI(); //사용자가 요청한 URL=Location, I indicator
		String context = req.getContextPath();
		
		uri = uri.substring(context.length());
		
		Controller c = urlMap.get(uri);
		
		String view = null;
		try {
			view = c.service(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			view = "notfound";
		}
		
		if(view.startsWith("r::")) {
			String target = view.substring("r::".length());
			resp.sendRedirect(target);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp");
			rd.forward(req, resp);
		}
		
	}
}






