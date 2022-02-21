package board.md.mapper;

import java.util.List;

import board.md.domain.Board;
import board.md.domain.Criteria;

public interface BoardMapper {
	List<Board> list();
	List<Board> listPage(Criteria cri);
	void insert(Board board);
	void delete(long seq);
	Board content(long seq);
	void update(Board board);
}
