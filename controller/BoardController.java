package board.md.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.domain.Criteria;
import board.md.domain.PageMaker;
import board.md.domain.SearchList;
import board.md.service.BoardService;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	
	private BoardService boardService;
	
	@GetMapping("/list.do")
	public ModelAndView list(HttpServletRequest request,HttpSession session) {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		//(1) cp
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp =(Integer)cpObj;
			}
		}else {
			cpStr= cpStr.trim();
			cp=Integer.parseInt(cpStr);
		}
		session.setAttribute("cp",cp);
		// (2) ps
		int ps = 5;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			System.out.println("psObj : "+psObj);
			if(psObj != null) {
				ps =(Integer)psObj;
				System.out.println("ps : "+ps);
			}
		}else {
			psStr= psStr.trim();
			int psParam=Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			ps=psParam;
		}
		session.setAttribute("ps",ps);
		
		
		//(3) MOdelAndView
		BoardListResult listResult = boardService.getBoardListResult(cp, ps);
		long Count = boardService.selectCount();
		ModelAndView mv = new ModelAndView("board/list2","listResult",listResult);
		mv.addObject("Count",Count);
		if(listResult.getList().size() ==0) {
			if(cp > 1)
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else 
				return new ModelAndView("board/list2","listResult",null);
		}else {
			return mv;
		}
		
	}
	
	@GetMapping("/list2.do")
	public ModelAndView serchlist(HttpServletRequest request,HttpSession session,@RequestParam("subject") String subject) {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		
		log.info("SUBJECT : "+subject);
		//(1) cp
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp =(Integer)cpObj;
			}
		}else {
			cpStr= cpStr.trim();
			cp=Integer.parseInt(cpStr);
		}
		session.setAttribute("cp",cp);
		// (2) ps
		int ps = 5;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			System.out.println("psObj : "+psObj);
			if(psObj != null) {
				ps =(Integer)psObj;
				System.out.println("ps : "+ps);
			}
		}else {
			psStr= psStr.trim();
			int psParam=Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			ps=psParam;
		}
		session.setAttribute("ps",ps);
		
		log.info("3가기전");
		//(3) MOdelAndView
		SearchList listResult = boardService.getSerchList(cp, ps,subject);
		log.info("3이후 cp: "+cp+"ps :"+ps+"subject : " +subject);
		long Count = boardService.selectCount();
		ModelAndView mv = new ModelAndView("board/list2","listResult",listResult);
		log.info("listResult subject: "+listResult.getSubject());
		mv.addObject("Count",Count);
		if(listResult.getList().size() ==0) {
			if(cp > 1)
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else 
				return new ModelAndView("board/list2","listResult",null);
		}else {
			return mv;
		}
		
	}
	
	/*@GetMapping("/list.do")
	public ModelAndView list() {
		List<Board> list = boardService.listS();
		ModelAndView mv = new ModelAndView("board/list","list",list);
		mv.addObject(attributeValue)
		return mv;
	}*/
	
	/*@GetMapping("/pagelist.do")
	public String pagelist(Model model,Criteria cri) {
		List<Board> list = boardService.listS();
		int total=list.size();//총사이즈 구하기
		
		model.addAttribute("list",boardService.listPageS(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(total);
		
		model.addAttribute("pageMaker",pageMaker);
		
		return "board/list";
	}*/
	
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	@PostMapping("/write.do")
	public String write(Board board) {
		boardService.write(board);
		log.info("인서트 보드: "+board);
		return "redirect:list.do";
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
		return "redirect:list.do";
	}
	
	@GetMapping("/del.do")
	public String remove(long seq) {
		boardService.remove(seq);
		log.info("딜리트 시퀀스 보드: "+seq);
		return "redirect:list.do";
	}
}
