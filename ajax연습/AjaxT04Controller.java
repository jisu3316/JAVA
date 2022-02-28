package board.md.controller;


import java.util.List;


import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import board.md.domain.Board;
import board.md.service.BoardAjaxService;
import lombok.AllArgsConstructor;



@RestController //모델엔뷰나 일반 리턴할 수 없다 
@RequestMapping("ajax04")
@AllArgsConstructor
public class AjaxT04Controller {
	
	private BoardAjaxService service;
	
	@GetMapping(value = "search01",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE} )
	public Board search01(long seq, HttpServletResponse response) {
		Board board = service.selectBySeqS(seq);
		
		return board;
	}
	
	@PostMapping(value = "search02", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Board> search02(String writer, HttpServletResponse response) {
		List<Board> list = service.selectByWriterS(writer);
	
		return list;
	}
	@GetMapping("txt")
	public String getText() {
		return "good"; //일반 컨트롤러면 good.jsp를 리턴함. 여기서는 good 스트링을 리턴함.
		
	}

}
