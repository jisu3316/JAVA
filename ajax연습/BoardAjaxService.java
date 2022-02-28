package board.md.service;

import java.util.List;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.domain.BoardVo2;

public interface BoardAjaxService {
	Board selectBySeqS(long seq);
	List<Board> selectByWriterS(String writer);
	void insert(Board board);
	void delete(long seq);
	BoardListResult getBoardListResult(String catgo,String subject,int cp,int ps);
	BoardListResult getBoardListResult2(String catgo,String subject,int cp,int ps);
	long selectCount();//총갯수
	String selectsearchCount(BoardVo2 boardVo2);//검색 총갯수
}
