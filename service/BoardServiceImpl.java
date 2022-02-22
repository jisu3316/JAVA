package board.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.domain.BoardVo;
import board.md.domain.Criteria;
import board.md.domain.SearchList;
import board.md.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	
	@Override
	public BoardListResult getBoardListResult(int cp, int ps) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp,ps);
		List<Board> list = boardMapper.selectPerPage(boardVo);
		System.out.println("boardvo : "+boardVo.getEndRow());
		return new BoardListResult(cp,totalCount,ps,list);
	}
	
	@Override
	public  SearchList getSerchList(int cp ,int ps,String subject) {
		log.info("들어옴");
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(cp,ps);
		Board board = new Board(subject);
		List<Board> list = boardMapper.searchSubject(cp,ps,board);
		System.out.println("boardvo startRow: "+boardVo.getStartRow());
		System.out.println("boardvo : "+boardVo.getEndRow());
		
		return new SearchList(cp,totalCount,ps,list);
		
	}
	
	@Override
	public List<Board> listS() {
		
		return boardMapper.list();
	}
	
	@Override
	public List<Board> listPageS(Criteria cri) {
		return boardMapper.listPage(cri);
	}

	@Override
	public void write(Board board) {
		boardMapper.insert(board);

	}

	@Override
	public void remove(long seq) {
		boardMapper.delete(seq);

	}
	
	@Override
	public Board contentS(long seq) {
		return boardMapper.content(seq);

	}
	@Override
	public void updateS(Board board) {
		boardMapper.update(board);

	}


	@Override
	public long selectCount() {
		long Count = boardMapper.selectCount();
		return Count;
	}


	
	
	/*@Override
	public List<Board> selectPerPage(BoardVo boardvo) {

		return null;
	}
	@Override
	public long selectCount() {

		return 0;
	}*/
	

}
