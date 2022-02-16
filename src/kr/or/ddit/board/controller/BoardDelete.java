package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

/**
 * Servlet implementation class BoardDelete
 */
@WebServlet("/BoardDelete.do")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트 요청시 전송데이타 - num
		int num = Integer.parseInt(request.getParameter("num"));
		String type =request.getParameter("type");
		String word = request.getParameter("word");
		
		//2. service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3. service메소드 호출 - 결과값 받기
		int res = service.deleteBoard(num);
		
		//totalPage다시 구하기 - 전체 글갯수
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", type);
		map.put("sword", word);
		
		int count = service.searchCount(map); //검색 실행
		
		//한페이지당 출력할 글 갯수 = 3
		int perList = 3;
		
		//한화면에 출력할 페이지 갯수 
		int perPage = 2;
		
		//전체페이지  - 7
		int totalPage =  (int)Math.ceil((double)count / perList);
		System.out.println("tatalPage========="+totalPage);
		
		
		//4. 결과값으로 출력 또는 json데이터 생성
		request.setAttribute("result", res);
		request.setAttribute("totalpage", totalPage);
		
		request.getRequestDispatcher("boardsearch/boarddelete.jsp").forward(request, response);
		
	}

}
