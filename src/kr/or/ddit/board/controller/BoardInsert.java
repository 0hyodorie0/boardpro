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

/**
 * Servlet implementation class BoardInsert
 */
@WebServlet("/BoardInsert.do")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1. 클라이언트 요청시 전송데이터
		BoardVO vo = new BoardVO();
		
		vo.setSubject(request.getParameter("subject"));
		vo.setWriter(request.getParameter("writer"));
		vo.setMail(request.getParameter("mail"));
		vo.setPassword(request.getParameter("password"));
		vo.setContent(request.getParameter("content"));
		
		vo.setWip(request.getRemoteAddr());
		
		//2. service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3. service메소드 호출 - 결과값
		int res = service.insertBoard(vo);
		
		//4. 결과값을 request에 저장
		request.setAttribute("renum", res);
		
		//5. jsp로 forward
		request.getRequestDispatcher("boardsearch/replyInsert.jsp").forward(request, response);
	}

}
