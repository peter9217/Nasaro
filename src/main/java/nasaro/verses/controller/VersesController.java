package nasaro.verses.controller;


import java.util.ArrayList;
import java.util.HashMap;
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
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.service.VersesService;

@Controller
public class VersesController {
	@Autowired
	private VersesService service;
			
	@GetMapping("/verses/verses")
	public String verse(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.versesList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.versesList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		return "verses/verses";
	}
	
	@GetMapping("/verses/versesDetail/{no:[0-9]+}")
	public String detail(@PathVariable(name="no") String no, 
			Model model
			
			) {
		Verses verses = service.detailedVerses(no);
		model.addAttribute("verses", verses);
		System.out.println(verses);
		return "verses/versesDetail";
	}
	@GetMapping("/verses/versesWrite")
	public String versesWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:verses";
		}
		return "verses/versesWrite";
	}
	
	@GetMapping("/verses/insert")
	public String versesInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Verses verses
			) {
		verses.setMemberNo(loginMember.getMemberNo());
		System.out.println("로그인멤버");
		System.out.println(loginMember);
		System.out.println(verses);
		int i = service.insertVerses(verses);
		String path = "redirect:verses";
		return path;
	}
	
	
	// 공지사항 삭제
	@GetMapping(value="/verses/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String delete(
			RedirectAttributes ra
			,@RequestParam(name="versesNo") long versesNo
			){
		int i=service.deleteVerses(versesNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "verses/verses";
	}
	
	// 공지사항 수정페이지
	@GetMapping("/verses/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Verses verses = service.detailedVerses(no);
		model.addAttribute("verses", verses);
		return "verses/versesModify";
	}

	// 공지사항 수정
	@GetMapping("/verses/update")
	public String update(Verses verses,
			Model model
			){
		int i = service.updateVerses(verses);
		if(i==0) {
			
		}
		return "redirect:/verses/verses";
	}
}
