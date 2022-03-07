package board.md.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import board.md.domain.Board;
import board.md.service.BoardAjaxService;
import lombok.AllArgsConstructor;



@Controller
@RequestMapping("ajax03")
@AllArgsConstructor
@ResponseBody//이밑에는 제이슨 형태로날라감 
public class AjaxT03Controller {
	
	private BoardAjaxService service;
	
	@GetMapping("search01")
	public Board search01(long seq, HttpServletResponse response) {
		Board board = service.selectBySeqS(seq);
		
		return board;
	}
	
	@PostMapping("search02")
	public List<Board> search02(String writer) {
		List<Board> list = service.selectByWriterS(writer);
	
		return list;
	}

}
