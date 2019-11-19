package controller;

import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.BoardDAO;
import dao.BoardVO;
import domain.UserVO;

public class WriteController implements Controller{
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("post")) {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			Part filePart = req.getPart("file");
			
			String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			
	
			System.out.println(req.getServletContext().getRealPath("/WEB-INF/" + filename));
			filePart.write(req.getServletContext().getRealPath("/WEB-INF/" + filename));
			
			
			//파일업로드할꺼면 여기서 파일도 받아줘야해
			UserVO user = (UserVO)req.getSession().getAttribute("user");
			
			BoardVO data = new BoardVO();
			data.setTitle(title);
			data.setContent(content);
			data.setWriter(user.getId());
			data.setFiles(filename);
			
			int res = BoardDAO.getIns().write(data);
			
			if(res != 1) {
				req.getSession().setAttribute("msg", "글쓰기중 오류 발생");
				return "write";
			}else {
				req.getSession().setAttribute("msg", "성공적으로 글 작성");
				return "r::/board";
			}
			
		}
		
		return "write";
	}
}
