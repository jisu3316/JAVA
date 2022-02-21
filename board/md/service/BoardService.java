package board.md.service;

import java.util.List;

import board.md.domain.Board;
import board.md.domain.Criteria;

public interface BoardService {
	List<Board> listS();
	List<Board> listPageS(Criteria cri);
	void insertS(Board board);
	void deleteS(long seq);
	Board contentS(long seq);
	void updateS(Board board);
}
