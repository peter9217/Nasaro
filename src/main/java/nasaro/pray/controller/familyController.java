package nasaro.pray.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nasaro.member.model.dto.Member;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.service.FamilyService;
import nasaro.verses.model.dto.Verses;

@Controller
public class familyController {
	
	@Autowired
	private FamilyService service;
	
	@GetMapping("/pray/family")
	public String family(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.familyList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.familyList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		return "pray/family";
	}
	
	@GetMapping("/pray/familyDetail/{no:[0-9]+}")
	public String detail(@PathVariable(name="no") String no, Model model) {
		Family family = service.detailedFamily(no);
		model.addAttribute("family", family);
		System.out.println(family);
		return "pray/familyDetail";
	}
	
	@GetMapping("/pray/familyWrite")
	public String familyWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:family";
		}
		return "pray/familyWrite";
	}
	
	@GetMapping("/family/insert")
	public String familyInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Family family
			) {
		family.setMemberNo(loginMember.getMemberNo());
		System.out.println("로그인멤버");
		System.out.println(loginMember);
		System.out.println(family);
		int i = service.insertFamily(family);
		String path = "redirect:/pray/family";
		return path;
	}
	
	// 공지사항 삭제
	@GetMapping(value="/family/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String delete(
			RedirectAttributes ra
			,@RequestParam(name="familyNo") long familyNo
			){
		int i=service.deleteFamily(familyNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "pray/family";
	}
	
	// 공지사항 수정페이지
	@GetMapping("/family/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Family family = service.detailedFamily(no);
		model.addAttribute("family", family);
		return "pray/familyModify";
	}

	// 공지사항 수정
	@GetMapping("/family/update")
	public String update(Family family,
			Model model
			){
		int i = service.updateFamily(family);
		if(i==0) {
			
		}
		return "redirect:/pray/family";
	}
}
