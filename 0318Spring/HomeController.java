package team1.togather.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import team1.togather.domain.GroupTab;
import team1.togather.domain.Member;
import team1.togather.service.GroupTabService;
import team1.togather.service.MemberService;

@Controller
public class HomeController {
	@Autowired
	private GroupTabService groupTabService;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		List<GroupTab> list = groupTabService.selectAllS();
		List<Long> groupMemberCount = new ArrayList<>();
		for(int i =0;i<list.size();i++) {
			groupMemberCount.add(groupTabService.groupMemberCount(list.get(i).getGseq()));
		}
		System.out.println("groupMemberCount: "+groupMemberCount);
		List<Member> namelist = groupTabService.selectAllname();
		long membercount = memberService.memberCount();
		long groupcount = groupTabService.groupCount();
		
		ModelAndView mv = new ModelAndView("index", "list", list);
		mv.addObject("namelist", namelist);
		mv.addObject("membercount",membercount);
		mv.addObject("groupcount",groupcount);
		mv.addObject("groupMemberCount", groupMemberCount);
		return mv;
	}
	
}
