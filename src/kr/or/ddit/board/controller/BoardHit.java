package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;


@WebServlet("/BoardHit.do")
public class BoardHit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardHit() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트 요청시 전송 테이터 - num
		int num = Integer.parseInt(request.getParameter("num"));
		
		//service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//service메소드 호출 - 결과값
		int res = service.updateHit(num);
		
		//결과값을 request에 저장
		request.setAttribute("resnum", res);
		
		//jsp로 forward
		request.getRequestDispatcher("boardsearch/replyupdate.jsp").forward(request, response);
	}


}
