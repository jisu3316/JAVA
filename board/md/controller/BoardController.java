package board.md.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.md.domain.Board;
import board.md.domain.Criteria;
import board.md.domain.PageMaker;
import board.md.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("/board/*")
public class BoardController {
	private BoardService boardService;
	
	/*@GetMapping("/list.do")
	public ModelAndView list() {
		List<Board> list = boardService.listS();
		ModelAndView mv = new ModelAndView("board/list","list",list);
		return mv;
	}*/
	
	@GetMapping("/pagelist.do")
	public String pagelist(Model model,Criteria cri) {
		List<Board> list = boardService.listS();
		int total=list.size();
		model.addAttribute("list",boardService.listPageS(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(total);
		model.addAttribute("pageMaker",pageMaker);
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	@PostMapping("/write.do")
	public String insert(Board board) {
		boardService.insertS(board);
		log.info("인서트 보드: "+board);
		return "redirect:pagelist.do";
	}
	@GetMapping("/content.do")
	public ModelAndView content(long seq) {
		Board board=boardService.contentS(seq);
		
		ModelAndView mv = new ModelAndView("board/content","boardlist",board);
		return mv;
	}
	@GetMapping("/update.do")
	public ModelAndView update1(long seq) {
		Board board=boardService.contentS(seq);
		ModelAndView mv = new ModelAndView("board/update","update",board);
		return mv;
	}
	@PostMapping("/update2.do")
	public String update2(Board board) {
		boardService.updateS(board);
		log.info("업데이트2 보드: "+board);
		return "redirect:pagelist.do";
	}
	
	@GetMapping("/del.do")
	public String delete(long seq) {
		boardService.deleteS(seq);
		log.info("딜리트 시퀀스 보드: "+seq);
		return "redirect:list.do";
	}
}
