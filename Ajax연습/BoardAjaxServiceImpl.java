package board.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.md.domain.Board;
import board.md.mapper.BoardAjaxMapper;
@Service
public class BoardAjaxServiceImpl implements BoardAjaxService {
	@Autowired
	private BoardAjaxMapper boardAjaxMapper;
	@Override
	public Board selectBySeqS(long seq) {
		return boardAjaxMapper.selectBySeq(seq);
		
	}

	@Override
	public List<Board> selectByWriterS(String writer) {
		return boardAjaxMapper.selectByWriter(writer);
		
	}

}
