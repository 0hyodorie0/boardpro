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

/**
 * Servlet implementation class ReplyUpdate
 */
@WebServlet("/ReplyUpdate.do")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.클라이언트 요청시 전송값 - renum, cont - vo에 저장하기
		int renum = Integer.parseInt(request.getParameter("renum"));
		String cont = request.getParameter("cont");
		
		ReplyVO vo = new ReplyVO();
		vo.setRenum(renum);
		vo.setCont(cont);
		
		//2.service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3. service메소드 호출 - 결과값 얻기
		int res = service.updateReply(vo);
		
		//4. 결과값을 request에 저장
		request.setAttribute("resnum", res);
		
		//5. jsp로 forward
		request.getRequestDispatcher("boardsearch/replyupdate.jsp").forward(request, response);
		
	}

}
