package board.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.md.domain.Board;
import board.md.domain.Criteria;
import board.md.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Override
	public List<Board> listS() {
		
		return boardMapper.list();
	}
	@Override
	public List<Board> listPageS(Criteria cri) {
		return boardMapper.listPage(cri);
	}

	@Override
	public void insertS(Board board) {
		boardMapper.insert(board);

	}

	@Override
	public void deleteS(long seq) {
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
	

}
