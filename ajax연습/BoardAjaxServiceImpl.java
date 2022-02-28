package board.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.domain.BoardVo;
import board.md.domain.BoardVo2;
import board.md.mapper.BoardAjaxMapper;
@Service
public class BoardAjaxServiceImpl implements BoardAjaxService {
	@Autowired
	private BoardAjaxMapper boardAjaxMapper;
	@Override
	public Board selectBySeqS(long seq) {
		return boardAjaxMapper.selectBySeq(seq);
		
	}
	
	@Override
	public BoardListResult getBoardListResult(String catgo,String keyword,int cp, int ps) {
		long totalCount = boardAjaxMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp,ps);
		List<Board> list = boardAjaxMapper.selectPerPage(boardVo);
		
		return new BoardListResult(cp,totalCount,ps,list);
	}
	@Override
	public BoardListResult getBoardListResult2(String catgo,String keyword,int cp, int ps) {
		
		BoardVo2 boardVo2 = new BoardVo2(catgo,keyword,cp,ps);
		System.out.println("서비스안 :"+keyword+catgo);
		List<Board> list = boardAjaxMapper.selectSearchPerPage(boardVo2);
		System.out.println("서비스안 리스트  사이즈 :"+list.size());
		String totalCountstr = boardAjaxMapper.selectsearchCount(boardVo2);
		long totalCount=0;
		if(totalCountstr==null) {
			totalCount=0;
		}else {
			totalCount = Long.parseLong(totalCountstr);
		}
		return new BoardListResult(cp,totalCount,ps,list);
	}
	@Override
	public long selectCount() {
		long Count = boardAjaxMapper.selectCount();
		return Count;
	}
	@Override
	public String selectsearchCount(BoardVo2 boardVo2) {
		String totalCountstr = boardAjaxMapper.selectsearchCount(boardVo2);
		return "totalCountstr";
	}
	@Override
	public List<Board> selectByWriterS(String writer) {
		return boardAjaxMapper.selectByWriter(writer);
		
	}

	@Override
	public void insert(Board board) {
		boardAjaxMapper.insert(board);
		
	}

	@Override
	public void delete(long seq) {
		boardAjaxMapper.delete(seq);
		
	}

}
