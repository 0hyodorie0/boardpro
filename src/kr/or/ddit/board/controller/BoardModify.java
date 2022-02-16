package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;


@WebServlet("/BoardModify.do")
public class BoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1.클라이언트 요청시 전송값 - num, subject, pass, mail, content
		int num = Integer.parseInt(request.getParameter("num"));
		String subject = request.getParameter("subject");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String cont = request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setContent(cont);
		vo.setMail(mail);
		vo.setNum(num);
		vo.setPassword(pass);
		vo.setWip(request.getRemoteAddr());
		vo.setSubject(subject);
				
		//2.service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3.service메소드 호출 - 결과값
		int res = service.updateBoard(vo);
		
		//4.결과값을 request에 저장
		request.setAttribute("resnum", res);
		
		//5.jsp로 forward
		request.getRequestDispatcher("boardsearch/replyupdate.jsp").forward(request, response);
		
	}

}
