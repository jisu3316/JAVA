package board.md.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.domain.BoardVo2;
import board.md.domain.Criteria;


public interface BoardService {
	//새로만든거
	//List<Board> selectPerPage(BoardVo boardvo);//페이지 처리
	BoardListResult getBoardListResult(String catgo,String subject,int cp,int ps);
	BoardListResult getBoardListResult2(String catgo,String subject,int cp,int ps);
	long selectCount();//총갯수
	String selectsearchCount(BoardVo2 boardVo2);//검색 총갯수
	//새로만든거
	//String saveStore(MultipartFile file);
		
	List<Board> listS();
	List<Board> listPageS(Criteria cri);
	//void insertS(Board board);
	void write(Board board,MultipartFile file);
	//void deleteS(long seq);
	void remove(long seq);
	Board contentS(long seq);
	//Board getBoard(long seq);
	void updateS(Board board);
	
	//검색
	//SearchList getSerchList(int cp,int ps,String subject);
}
