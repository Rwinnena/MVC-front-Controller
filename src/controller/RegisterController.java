package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import domain.UserVO;

public class RegisterController implements Controller {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("POST")) {
			String id = req.getParameter("id");
			String pass = req.getParameter("password");
			String name = req.getParameter("name");
			UserVO user = new UserVO();
			user.setId(id);
			user.setName(name);
			user.setPass(pass);
			
			HttpSession s = req.getSession();
			if(MemberDAO.getIns().register(user)) {
				s.setAttribute("msg", "성공적으로 회원가입 되었습니다.");
				return "r::/"; //로그인 성공 후 리다이렉트
			}else {
				s.setAttribute("msg", "회원가입 실패");
				return "register";
			}
		}
		
		return "register";
	}
}
