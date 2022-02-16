package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;


@WebServlet("/ListSearch.do")
public class ListSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ListSearch() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.클라이언트 요청시 전송값 - page, type, word
		int cpage = Integer.parseInt(request.getParameter("page"));
		String stype = request.getParameter("type");
		String sword = request.getParameter("word");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stype", stype);
		map.put("sword", sword);
		map.put("cpage", cpage);
		
		//2.service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3.service메소드 호출 - 결과값얻기 
		Map<String, Object> rmap = service.pageInfo(map);
		//ramp = start, end, startPage, endPage, totalPage
		
		rmap.put("stype", stype);
		rmap.put("sword", sword);
		
		List<BoardVO> list = service.selectByPS(rmap);
		
		
		//4. 결과값 으로 json데이타 생성 
		response.setContentType("application/json; charset=utf-8");
		PrintWriter  out = response.getWriter();
		
		JsonObject  obj = new JsonObject();
		
		obj.addProperty("totalpage", (Integer)rmap.get("totalPage"));
		obj.addProperty("startpage", (Integer)rmap.get("startPage"));
		obj.addProperty("endpage", (Integer)rmap.get("endPage"));
		
		Gson    gson = new Gson();
		JsonElement  ele =   gson.toJsonTree(list);
		obj.add("datas" , ele);
		
		out.print(obj);
				
	}

}
