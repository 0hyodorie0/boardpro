package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.config.SqlMapClientFactory;

public class BoardDaoImpl implements IBoardDao {

	private static IBoardDao  dao ;
	private SqlMapClient  client;
	
	private BoardDaoImpl() {
		client = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IBoardDao  getInstance() {
		if(dao == null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public List<BoardVO> selectBoard() {
		List<BoardVO>   list = null; 
		
		try {
			list =  client.queryForList("board.selectBoard");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Object> map) {
		List<BoardVO>  list = null;
		
		
		try {
			list = client.queryForList("board.selectByPage", map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getTotalCount() {
		int   cnt = 0;
		
		try {
			cnt = (int)client.queryForObject("board.getTotalCount");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> selectByPS(Map<String, Object> map) {
		List<BoardVO> list = null;
		
		try {
			list = client.queryForList("board.selectByPS", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int searchCount(Map<String, Object> map) {
		int count = 0;
		
		try {
			count = (int) client.queryForObject("board.searchCount", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int insertReply(ReplyVO vo) {
		int renum = 0;
		
		try {
			renum = (int) client.insert("board.insertReply",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return renum;
	}

	@Override
	public List<ReplyVO> listReply(int bonum) {
		List<ReplyVO> list = null;
		try {
			list = client.queryForList("board.listReply", bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteReply(int renum) {
		int res = 0;
		
		try {
			res = client.delete("board.deleteReply",renum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateReply(ReplyVO replyVo) {
		int res = 0;
		
		try {
			res = client.update("board.updateReply", replyVo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteBoard(int num) {
		int res = 0;
		
		try {
			res = client.update("board.deleteBoard", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateBoard(BoardVO vo) {
		int res = 0;
		try {
			res = client.delete("board.updateBoard", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateHit(int num) {
		int res = 0;
		try {
			res = client.update("board.updateHit", num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int insertBoard(BoardVO vo) {
		int res = 0;
		try {
			res = (int) client.insert("board.insertBoard", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}





