package board.md.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.md.domain.Board;
import board.md.service.BoardAjaxService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("ajax01")
@AllArgsConstructor
public class AjaxT01Controller {
	
	private BoardAjaxService service;
	
	@GetMapping("search01.do")
	public void search01(long seq, HttpServletResponse response) {
		Board board = service.selectBySeqS(seq);
		log.info("#board : "+board);
		
		String boardJson = null;
		if(board !=null) {
			boardJson = "{\"seq\":"+board.getSeq()
						+",\"writer\":\""+board.getWriter() 
						+"\",\"email\":\""+board.getEmail()
						+"\",\"content\":\""+board.getContent()
						+"\"}";
		}
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(boardJson);
		}catch(IOException ie) {}
	}
	
	@PostMapping("search02.do")
	public void search02(String writer, HttpServletResponse response) {
		List<Board> list = service.selectByWriterS(writer);
		
		String boardJson = null;
		if(list.size() != 0) {
			boardJson = "[";
			for(Board board : list) {
				boardJson += "{\"seq\":"+board.getSeq()
							  +",\"writer\":\""+board.getWriter() 
							  +"\",\"email\":\""+board.getEmail()
							  +"\",\"content\":\""+board.getContent()
							  +"\"}";	
				boardJson+=",";
			}
			boardJson = boardJson.substring(0,boardJson.length()-1);
			boardJson += "]";
		}else {
			boardJson = "[]";
		}
		
		try {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(boardJson);
		}catch(IOException ie) {}
		
	}
}
