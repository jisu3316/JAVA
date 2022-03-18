package team1.togather.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import team1.togather.domain.GroupTab;
import team1.togather.domain.MemInGroup;
import team1.togather.domain.Member;
import team1.togather.service.GroupTabService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("groupTab")
public class GroupTabController {
	private GroupTabService groupTabService;
	
	@GetMapping("groupList.do")
	public ModelAndView list() {
		List<GroupTab> list = groupTabService.selectAllS();
		ModelAndView mv = new ModelAndView("groupTab/groupList", "list", list);
		return mv;
	}
	@GetMapping("groupInfo.do")
	public ModelAndView groupInfo(long gseq,MemInGroup memInGroup) {
		GroupTab groupInfo = groupTabService.selectByGSeqS(gseq);
		Long groupMemberCount = groupTabService.groupMemberCount(gseq);
		Member groupMemberName = groupTabService.groupInfoMemberName(gseq);
		List<String> memInGroupName = groupTabService.memInGroupName(memInGroup);
		Long memInGroupCheck = groupTabService.memInGroupCheck(memInGroup);
		
		System.out.println("memInGroupName: "+memInGroupName);
		ModelAndView mv = new ModelAndView("groupTab/groupInfo", "groupInfo", groupInfo);
		mv.addObject("groupMemberCount", groupMemberCount);
		mv.addObject("groupMemberName", groupMemberName);
		mv.addObject("memInGroupCheck",memInGroupCheck);
		mv.addObject("memInGroupName",memInGroupName);
		return mv;
	}
	@GetMapping("groupCreate.do")
	public String groupCreate() {
		return "groupTab/groupCreate";
	}
	@PostMapping("groupCreate.do")
	public String groupCreate(GroupTab groupTab) {
		groupTabService.insertS(groupTab);
		
		return "redirect:../";
		// 현재는 모임을 만들고 인덱스로 감 => 모임정보로 가기위해서는 gseq를 가져오는 방법을 알아야함. (create할 때 gseq값은 따로 입력하지않아서 null을 가져옴)
	}
	@GetMapping("groupDelete.do")
	public String groupDelete(long gseq) {
		groupTabService.memInGroupDelete(gseq);
		groupTabService.deleteS(gseq);
		return "redirect:/";
	} 
	@PostMapping("groupUpdatecheck")
	@ResponseBody
	public Long groupUpdatecheck(MemInGroup memInGroup) {
		//0=모임장 1=운영진 2=일반
		Long grade = groupTabService.grade(memInGroup);
		System.out.println("grade : "+ grade);
		//ModelAndView mv = new ModelAndView("groupTab/groupUpdate", "updateList", updateList);
		if(grade ==null) {//가입안한 사람
			System.out.println("널일때"+grade);
			grade=(long) 3;
			return grade;
		}else {	
			if(grade==0 || grade ==1) {//모임장이거나 운영자
				System.out.println("0,1일때"+grade);
				return grade;
			}else {//일반회원
				System.out.println("일반일때"+grade);
				return grade;
			}
		}
	}
	@PostMapping("groupDeletecheck")
	@ResponseBody
	public Long groupDeletecheck(MemInGroup memInGroup) {
		//0=모임장 1=운영진 2=일반
		Long grade = groupTabService.grade(memInGroup);
		System.out.println("grade : "+ grade);
		if(grade ==null) {//가입안한 사람
			System.out.println("널일때"+grade);
			grade=(long) 3;
			return grade;
		}else {	
			return grade;
		}
	}
	@GetMapping("groupUpdate.do")
	public ModelAndView groupUpdate(long gseq) {
		GroupTab updateList = groupTabService.selectByGSeqS(gseq);
		ModelAndView mv = new ModelAndView("groupTab/groupUpdate", "updateList", updateList);
		return mv;
		
	}
	@PostMapping("groupUpdate.do")
	public String groupUpdate(GroupTab groupTab, HttpServletRequest request) {
		long gseq = getGSeq(request);
		log.info("groupUpdate_gseq: " + gseq + "groupUpdate_groupTab: " + groupTab);
		
		groupTabService.updateS(groupTab);
		
		return "redirect:groupInfo.do?gseq="+gseq;
	}
	@PostMapping("memInGroup")
	@ResponseBody
	public String memInGroup(MemInGroup memInGroup,HttpSession session) {
		groupTabService.memInGroup(memInGroup);
		return "ok";
	}
	@PostMapping("groupQuit")
	@ResponseBody
	public String groupQuit(MemInGroup memInGroup,long gseq) {
		System.out.println("groupQuit"+memInGroup);
		groupTabService.groupQuit(memInGroup);
		Long groupMemberCount = groupTabService.groupMemberCount(gseq);
		System.out.println("groupMemberCount: "+groupMemberCount);
		String result="";
		if(groupMemberCount==0) {
			groupTabService.memInGroupDelete(gseq);
			groupTabService.deleteS(gseq);
			result="0";
		}else {
			result="1";
		}
		System.out.println("result: "+result);
		return result;
	}
	private long getGSeq(HttpServletRequest request) {
		long gseq = -1;
		String gseqStr = request.getParameter("gseq");
		log.info("getGSeq_gseqStr: " + gseqStr);
		if(gseqStr != null) {
			gseqStr = gseqStr.trim();
			if(gseqStr.length() != 0) {
				try {
					gseq = Long.parseLong(gseqStr);
					return gseq;
				}catch(NumberFormatException nfe) {}
			}
		}
		return gseq;
	}
}
