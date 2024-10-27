package nasaro.verses.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nasaro.member.model.dto.Member;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.service.ReferenceService;

@Controller
public class ReferenceController {
	
	@Autowired
	private ReferenceService service;
	
	@GetMapping("/verses/reference")
	public String reference(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.referenceList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.referenceList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		return "verses/reference";
	}
	
	@GetMapping("/verses/referenceDetail/{no:[0-9]+}")
	public String detail(@PathVariable(name="no") String no, Model model) {
		Reference reference = service.detailedReference(no);
		model.addAttribute("reference", reference);
		System.out.println(reference);
		return "verses/referenceDetail";
		
	}
	@GetMapping("/verses/referenceWrite")
	public String referenceWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:reference";
		}
		return "verses/referenceWrite";
	}
	
	@PostMapping("/reference/insert")
	public String referenceInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Reference reference
			) {
		reference.setMemberNo(loginMember.getMemberNo());
		System.out.println(loginMember);
		int i = service.insertReference(reference);
		String path = "redirect:/verses/reference";
		return path;
	}
	
	// 공지사항 삭제
	@PostMapping(value="/reference/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String delete(
			RedirectAttributes ra
			,@RequestBody long referenceNo
			){
		int i=service.deleteReference(referenceNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "verses/reference";
	}
	
	// 공지사항 수정페이지
	@PostMapping("/reference/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Reference reference = service.detailedReference(no);
		model.addAttribute("reference", reference);
		return "verses/referenceModify";
	}

	// 공지사항 수정
	@PostMapping("/reference/update")
	public String update(Reference reference,
			Model model
			){
		int i = service.updateReference(reference);
		if(i==0) {
			
		}
		return "redirect:/verses/reference";
	}
}
