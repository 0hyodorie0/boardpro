package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public class BoardServiceImpl implements IBoardService {

	private static IBoardService  service;
	private IBoardDao  dao;
	
	private  BoardServiceImpl() {
		 dao = BoardDaoImpl.getInstance();	
	}
	
	
	public static IBoardService getInstance() {
			if(service == null)  service = new BoardServiceImpl();
			
			return service ;	
	}
	
	@Override
	public List<BoardVO> selectBoard() {
		// TODO Auto-generated method stub
		return dao.selectBoard();
	}


	@Override
	public List<BoardVO> selectByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectByPage(map);
	}


	@Override
	public Map<String, Object> pageInfo(int page) {
		//전체글갯수 가져오기  - 20 개 
		int count = this.getTotalCount();
		
		//한페이지당 출력할 글 갯수 = 3
		int perList = 3;
		
		//한화면에 출력할 페이지 갯수 
		int perPage = 2;
		
		//전체페이지  - 7
		int totalPage =  (int)Math.ceil((double)count / perList); 
		
		//보여줄  rownum 번호 -게시판 글 리스트 
		//page가 1일때 start=1 end = 3
		//page가 2일때 start=4 end =6
		//page가 3일때 start=7 end = 9
	    int start = (page-1) * perList + 1 ;
		int end  = start + perList - 1;
		if(end > count) end = count; 
		
		//보여줄 페이지번호 
		//page가 1일때 startPage=1 endPage = 2
		//page가 2일때 startPage=1 endPage = 2
		//page가 3일때 startPage=3 endPage = 4
		//page가 4일때 startPage=3 endPage = 4
		//page가 5일때 startPage=5 endPage =6
		//page가6일때  startPage=5 endPage =6
		//page가7일때 startPage=7 endPage =8
		int startPage = ( (page- 1) / perPage * perPage ) + 1;
		int endPage = startPage + perPage - 1;
		if(endPage > totalPage ) endPage = totalPage; 
		
		
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", totalPage);
		
		
		return map;
	}

	
//전체글갯수 가져오기 
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return dao.getTotalCount();
	}


	@Override
	public List<BoardVO> selectByPS(Map<String, Object> map) {
		return dao.selectByPS(map);
	}

	//전체글갯수 가져오기 - 검색조건에 따라
	@Override
	public int searchCount(Map<String, Object> map) {
		return dao.searchCount(map);
	}


	@Override
	public Map<String, Object> pageInfo(Map<String, Object> map) {
		//parameter map - cpage stype sword
		
		//전체글갯수 가져오기  - - 11 ? 3 ? 2 ?
		int count = this.searchCount(map);
		
		//한페이지당 출력할 글 갯수 = 3
		int perList = 3;
		
		//한화면에 출력할 페이지 갯수 
		int perPage = 2;
		
		//전체페이지  - 7
		int totalPage =  (int)Math.ceil((double)count / perList);
		System.out.println("tatalPage========="+totalPage);
		
		int page = (int)(map.get("cpage"));
		
		//보여줄  rownum 번호 -게시판 글 리스트 
		//page가 1일때 start=1 end = 3
		//page가 2일때 start=4 end =6
		//page가 3일때 start=7 end = 9
	    int start = (page-1) * perList + 1 ;
		int end  = start + perList - 1;
		if(end > count) end = count; 
		
		//보여줄 페이지번호 
		//page가 1일때 startPage=1 endPage = 2
		//page가 2일때 startPage=1 endPage = 2
		//page가 3일때 startPage=3 endPage = 4
		//page가 4일때 startPage=3 endPage = 4
		//page가 5일때 startPage=5 endPage =6
		//page가6일때  startPage=5 endPage =6
		//page가7일때 startPage=7 endPage =8
		int startPage = ( (page- 1) / perPage * perPage ) + 1;
		int endPage = startPage + perPage - 1;
		if(endPage > totalPage ) endPage = totalPage; 
		
		
		Map<String, Object>  rmap = new HashMap<String, Object>();
		rmap.put("start", start);
		rmap.put("end", end);
		rmap.put("startPage", startPage);
		rmap.put("endPage", endPage);
		rmap.put("totalPage", totalPage);
		
		
		return rmap;
	}


	@Override
	public int insertReply(ReplyVO vo) {
		return dao.insertReply(vo);
	}


	@Override
	public List<ReplyVO> listReply(int bonum) {
		// TODO Auto-generated method stub
		return dao.listReply(bonum);
	}


	@Override
	public int deleteReply(int renum) {
		// TODO Auto-generated method stub
		return dao.deleteReply(renum);
	}


	@Override
	public int updateReply(ReplyVO replyVo) {
		// TODO Auto-generated method stub
		return dao.updateReply(replyVo);
	}


	@Override
	public int deleteBoard(int num) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(num);
	}


	@Override
	public int updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(vo);
	}


	@Override
	public int updateHit(int num) {
		// TODO Auto-generated method stub
		return dao.updateHit(num);
	}


	@Override
	public int insertBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(vo);
	}

}
