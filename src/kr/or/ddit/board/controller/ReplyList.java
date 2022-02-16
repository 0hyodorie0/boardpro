package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

@WebServlet("/ReplyList.do")
public class ReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트 요청시 전송 테이타  - bonum
		int bonum = Integer.parseInt(request.getParameter("bonum"));
		
		//2. service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3. service메소드 호출 - 결과값 얻기
		List<ReplyVO> list = service.listReply(bonum);
		
		//4. 결과값으로 json데이터 생성
		Gson gson = new Gson();
		
		String result = gson.toJson(list);
		
		
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		
		//4. 결과값으로 json데이터 생성 - jsp에 위임
		//request.setAttribute("list", list);
		//request.getRequestDispatcher("boardsearch/replylist.jsp").forward(request, response);
		
				
	}

}
