package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardDao {

	
	//전체리스트 가져오기  
	public List<BoardVO> selectBoard();
	
	//페이지별 리스트 가져오기
	public List<BoardVO> selectByPage(Map<String, Object> map);
	
	//페이지별 리스트 가져오기  - 검색 포함 - boardsearch - boardpage.jsp 
	public List<BoardVO> selectByPS(Map<String, Object> map);
	
	//전체 글갯수 가져오기 - 검색포함
	public int searchCount(Map<String, Object> map);
	
	//글저장 
	public int insertBoard(BoardVO vo);
	
	//글삭제 
	public int deleteBoard(int num);
	
	//글수정 
	public int updateBoard(BoardVO vo);
	
	//댓글 등록 
	public int insertReply(ReplyVO vo);
	
	//댓글 수정 
	public int updateReply(ReplyVO replyVo);
	
	//댓글 삭제 
	public int deleteReply(int renum);
	
	//조회수 증가 
	public int updateHit(int num);
	
	//댓글 리스트
	public List<ReplyVO> listReply(int bonum);
	
	//전체 글갯수 가져오기 
	public int getTotalCount();

	
		
}
