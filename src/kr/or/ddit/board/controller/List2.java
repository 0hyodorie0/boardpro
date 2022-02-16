package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class List2
 */
@WebServlet("/List2.do")
public class List2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트 요청시 전송데이타 받기 
		
		//2. service객체 얻기 
		IBoardService  service = BoardServiceImpl.getInstance();
		
		//3. service메소드 호출 - 결과값 받기
		java.util.List<BoardVO>  list = service.selectBoard();
		
		
		//4. 결과값으로  json데이타 생성 
		Gson  gson = new Gson();
		
		String result = gson.toJson(list);
		
	    response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter  out = response.getWriter();
		
		out.print(result);
				
	}

}
