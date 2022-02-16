package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

/**
 * Servlet implementation class ReplyDelete
 */
@WebServlet("/ReplyDelete.do")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트 요청시 전송값  - renum
		int renum = Integer.parseInt(request.getParameter("renum"));
		
		//2. service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3. service메소드 호출 - 결과값 받기
		int res = service.deleteReply(renum);
		
		//4. request에 결과값 저장
		request.setAttribute("result", res);
		
		//5. 결과값으로 json데이타 생성 - jsp위임
		request.getRequestDispatcher("boardsearch/replydelete.jsp").forward(request, response);
	}

}
