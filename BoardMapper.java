package board.md.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import board.md.domain.Board;
import board.md.domain.BoardVo;
import board.md.domain.BoardVo2;
import board.md.domain.Criteria;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;



public interface BoardMapper {
	List<Board> list();
	List<Board> listPage(Criteria cri);
	
	//새로만든거
	List<Board> selectPerPage(BoardVo boardVo);//페이지 처리
	List<Board> selectSearchPerPage(BoardVo2 boardVo2);//페이지 처리
	long selectCount();//총갯수
	//새로만든거
	
	void insert(Board board);
	String selectsearchCount(BoardVo2 boardVo2);
	
	
	void delete(long seq);
	Board content(long seq);
	void update(Board board);

}
