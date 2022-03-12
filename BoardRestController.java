package board.md.controller;

import java.util.HashMap;
import net.nurigo.java_sdk.api.Message;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import board.md.domain.Board;
import board.md.domain.BoardListResult;
import board.md.service.BoardAjaxService;
import board.md.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Log4j
@AllArgsConstructor
@RequestMapping("rest_board")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class BoardRestController {
	@Autowired
	private BoardAjaxService service;
	//@RestController//제이슨 xml 
	/*@PostMapping("create")
	public void create(Board board) {
		log.info("#BoardController`s create() board: "+board);
		service.insert(board);
	}*/
	@GetMapping("list.do")
	public ModelAndView list(HttpServletRequest request,HttpSession session) {
		System.out.println("들어옴");
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		String keyword= request.getParameter("keyword");
		String catgo= request.getParameter("catgo");
		
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
			if(psObj != null) {
				ps =(Integer)psObj;
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
		
		//(3)keyword
		if(keyword == null || keyword.equals("") ||catgo.equals("")) {		
			System.out.println("처음키워드 :"+keyword);
			System.out.println("처음카테고리 :"+catgo);
			session.setAttribute("keyword",keyword);
			session.setAttribute("catgo",catgo);
			//(4) MOdelAndView
			BoardListResult listResult = service.getBoardListResult(catgo,keyword,cp, ps);
		
			long Count = service.selectCount();
			ModelAndView mv = new ModelAndView("rest_board/list2","listResult",listResult);
			mv.addObject("Count",Count);
			if(listResult.getList().size() ==0) {
				if(cp > 1)
					return new ModelAndView("redirect:list.do?cp="+(cp-1));
				else 
					return new ModelAndView("rest_board/list2","listResult",null);
			}else {
				return mv;
			}
		}else {//keyword가 있을때
			System.out.println("두번째카테고리 :"+catgo);
			//(4) MOdelAndView
			BoardListResult listResult = service.getBoardListResult2(catgo,keyword,cp, ps);
			long Count = listResult.getTotalCount();
			ModelAndView mv = new ModelAndView("list2","listResult",listResult);
			mv.addObject("Count",Count);
			mv.addObject("keyword",keyword);
			mv.addObject("catgo",catgo);
			if(listResult.getList().size() ==0) {
				if(cp > 1)
					return new ModelAndView("redirect:list.do?cp="+(cp-1));
				else 
					return new ModelAndView("list2","listResult",null);
			}else {
				return mv;
			}
		}
		
	}
	@GetMapping(value="sms")//
	public void sms() {
		String api_key = "API키";
	    String api_secret = "SECRET";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", "받을사람 ");
	    params.put("from", "내번호넣기");
	    params.put("type", "SMS");
	    params.put("text", "스프링으로 문자 보내기");
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	  
	}
	
	
	@PostMapping("create")
	public void create(@RequestBody Board board) {
		log.info("#BoardController`s create() board: "+board);
		service.insert(board);
	}
	
	@GetMapping(value="read")//여러개 넘길때
	public List<Board> read(){
		List<Board> list = service.select();
		
		return list;
	}
	@GetMapping(value="read",params= {"na"})//여러개 넘길때
	public List<Board> read1(@RequestParam("na") String writer){
		List<Board> list = service.selectByWriterS(writer);
		for(Board board:list) {
			System.out.println(board.getWriter());
		}
		return list;
	}
	
	/*
	@GetMapping(value="read",params= {"na"})//여러개 넘길때
	public Board read(@RequestParam("na") String writer){
		Board board = service.selectByWriterS2(writer);
		System.out.println("실행 :"+board.getWriter());
		return board;
	}*/
	//http://127.0.0.1:8080/rest_board/read.json?na=공
	
	/*@GetMapping("read/{seq}")//하나데이터 넘길때
	public Board read(@PathVariable long seq){
		Board board = service.selectBySeqS(seq);
		return board;
	}*/
	//http://127.0.0.1:8080/rest_board/read/13.json
	/*
	@GetMapping("read/{writer}")//하나데이터 넘길때 가능은 하나 위의 방법 추천
	public List<Board> read01(@PathVariable String writer){
		List<Board> board = service.selectByWriterS(writer);
		return board;
	}
	//http://127.0.0.1:8080/rest_board/read/공.json*/
	
	@DeleteMapping("delete/{seq}")
	public void delete(@PathVariable long seq) {
		service.delete(seq);
	}
	//http://127.0.0.1:8080/rest_board/delete/26.json
	
}
