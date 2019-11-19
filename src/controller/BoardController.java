package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dao.BoardVO;

public class BoardController implements Controller {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		
		int page = 1;
		try {
			page = Integer.parseInt(req.getParameter("p"));
			if(page < 1) {
				page = 1;
			}
		} catch (Exception e) {
			page = 1;
		}
		
		
		List<BoardVO> list = BoardDAO.getIns().getList(page);
		req.setAttribute("list", list);
		return "board";
	}
}
