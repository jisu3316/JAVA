package team1.togather.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import team1.togather.domain.Block;
import team1.togather.domain.Member;
import team1.togather.mapper.MemberMapper;
import team1.togather.service.MemberService;



@Controller
@AllArgsConstructor
@RequestMapping("/member/")
public class MemberController {
	private MemberService service;
	
	@GetMapping("/joinform.do")
	public String join() {
		return "member/join";
	}
	
	
	@PostMapping("/join")
	@ResponseBody
	public int join(Member member) {
		int join = service.join(member);
		System.out.println("join :"+join);
		return join;
	}
	
	@GetMapping("/login.do")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("/memberInfo")
	public ModelAndView memberInfo(Member member,HttpSession session) {
		Member memberInfo = service.memberInfo(member);
		List<String> blockedNameList = service.blockedNameList(session);
		
		System.out.println("멤버인포안 멤버 : "+memberInfo);
		System.out.println("blockedName: "+blockedNameList);
		String blockedCheck="";
		if(blockedNameList.size()!=0) {
			for(int i =0;i<blockedNameList.size();i++) {
				System.out.println("for문if밖 : "+blockedNameList.get(i));
				if(blockedNameList.get(i).equals(memberInfo.getMname())) {
					System.out.println("for문if안 : "+blockedNameList.get(i));
					System.out.println("for문if안 : "+memberInfo.getMname());
					blockedCheck="차단한 회원입니다";
				}
			}
		}
		ModelAndView mv = new ModelAndView("/member/memberInfo", "memberInfo", memberInfo);
		mv.addObject("blockedCheck", blockedCheck);
		System.out.println("blockedCheck: "+blockedCheck);
		return mv;
	}
	
	@PostMapping("/blocking")
	@ResponseBody
	public int blocking(Block block) {
		service.blocking(block);
		System.out.println("block: "+block);
		return 1;
	}
	@PostMapping("/blockingCancel")
	@ResponseBody
	public int blockingCancel(Block block) {
		service.blockingCancel(block);
		System.out.println("block: "+block);
		return 1;
	}
	@PostMapping("/login")
	@ResponseBody
	public int logincheck(Member member,HttpSession session) {
		//login.jsp에 아이디 확인 반환 로직
		//int 에서 모델앤뷰로 바꿔서 리턴해주기 
		int logincheck = service.logincheck(member);
		
		if(logincheck==0) {//아이디없음
			
			return logincheck;
		}else if(logincheck==1) {//비번다를때
			
			return logincheck;
		}else {//로그인성공
			Member m = service.login(member);
			String sessioncheck = "sessioncheck";
			session.setAttribute("m", m);
			session.setAttribute("sessioncheck", sessioncheck);
			return  logincheck;
		}
	}
	@GetMapping(value="/test",produces = "application/text; charset=utf8")
	@ResponseBody
	public void test(@RequestParam("name")String test,HttpServletResponse response) throws IOException {
		//login.jsp에 아이디 확인 반환 로직
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("test", test);
		response.setHeader("Content-Type", "application/xml");
	    response.setContentType("text/xml;charset=UTF-8");
	    response.setCharacterEncoding("utf-8");
	    response.getWriter().print(jsonObject);
		System.out.println(test);
		
	}
	
	@PostMapping("/kakaologin")
	@ResponseBody
	public int kakaologincheck(Member member,HttpSession session) {
		//login.jsp에 아이디 확인 반환 로직

		 int kakaologincheck = service.kakaologincheck(member);
		
		if(kakaologincheck==0) {//아이디없음
			
			return kakaologincheck;
		}else {//로그인성공
			Member m = service.kakaologin(member);
			String sessioncheck = "sessioncheck";
			session.setAttribute("m", m);
			session.setAttribute("sessioncheck", sessioncheck);
			System.out.println("로그인성공");
			return  kakaologincheck;
		}
	}
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
