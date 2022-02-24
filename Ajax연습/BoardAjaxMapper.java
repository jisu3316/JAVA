package board.md.mapper;

import java.util.List;

import board.md.domain.Board;

public interface BoardAjaxMapper {
	Board selectBySeq(long seq);
	List<Board> selectByWriter(String writer);
}
