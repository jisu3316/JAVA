package board.md.service;

import java.util.List;

import board.md.domain.Board;

public interface BoardAjaxService {
	Board selectBySeqS(long seq);
	List<Board> selectByWriterS(String writer);
}
