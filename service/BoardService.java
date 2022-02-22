package board.md.service;

import java.util.List;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.domain.BoardVo;
import board.md.domain.Criteria;
import board.md.domain.SearchList;

public interface BoardService {
	//새로만든거
	//List<Board> selectPerPage(BoardVo boardvo);//페이지 처리
	BoardListResult getBoardListResult(int cp,int ps);
	long selectCount();//총갯수
	//새로만든거
	
		
	List<Board> listS();
	List<Board> listPageS(Criteria cri);
	//void insertS(Board board);
	void write(Board board);
	//void deleteS(long seq);
	void remove(long seq);
	Board contentS(long seq);
	//Board getBoard(long seq);
	void updateS(Board board);
	
	//검색
	SearchList getSerchList(int cp,int ps,String subject);
}
