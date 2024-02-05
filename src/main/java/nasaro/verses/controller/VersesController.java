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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nasaro.member.model.dto.Member;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.service.VersesService;

@Controller
public class VersesController {
	@Autowired
	private VersesService service;
			
	@GetMapping("verses/verses")
	public String verse(
			Model model) {
		List<Verses> versesList = new ArrayList();
		versesList = service.versesList();
		model.addAttribute("versesList", versesList);
		System.out.println(versesList);
		return "verses/verses";
	}
	
	@GetMapping("verses/versesDetail/{no:[0-9]+}")
	public String detail(@PathVariable String no, 
			Model model
			
			) {
		Verses verses = service.detailedVerses(no);
		model.addAttribute("verses", verses);
		System.out.println(verses);
		return "verses/versesDetail";
	}
	@GetMapping("verses/versesWrite")
	public String versesWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:verses";
		}
		return "verses/versesWrite";
	}
	
	@GetMapping("verses/insert")
	public String versesInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Verses verses
			) {
		verses.setMemberNo(loginMember.getMemberNo());
		System.out.println(loginMember);
		int i = service.insertVerses(verses);
		String path = "redirect:verses";
		return path;
	}
}
