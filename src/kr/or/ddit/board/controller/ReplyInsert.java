package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

@WebServlet("/ReplyInsert.do")
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//1.클라이언트 요청시 전송값 - bonum, name, cont
		int bonum = Integer.parseInt(request.getParameter("bonum"));
		String name = request.getParameter("name");
		String cont = request.getParameter("cont");
		
		ReplyVO vo = new ReplyVO();
		vo.setBonum(bonum);
		vo.setName(name);
		vo.setCont(cont);
		
		//2.service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3.service메소드 호출 - 결과값 받기
		int renum = service.insertReply(vo);
		
		//4.결과값으로 json데이터 생성하기 - jsp로 위임해서 forward
		request.setAttribute("renum", renum);
		request.getRequestDispatcher("boardsearch/replyInsert.jsp").forward(request, response);
		
		
	}

}
