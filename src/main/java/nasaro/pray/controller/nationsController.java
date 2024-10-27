package nasaro.pray.controller;

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
import nasaro.pray.model.dto.Nations;
import nasaro.pray.model.service.NationsService;
import nasaro.verses.model.dto.Verses;

@Controller
public class nationsController {
	
	@Autowired
	private NationsService service;
	
	@GetMapping("/pray/nations")
	public String nations(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.nationsList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.nationsList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		return "pray/nations";
	}
	
	@GetMapping("pray/nationsDetail/{no:[0-9]+}")
	public String detail(@PathVariable(name="no") String no, Model model) {
		Nations nations = service.detailedNations(no);
		model.addAttribute("nations", nations);
		System.out.println(nations);
		return "pray/nationsDetail";
	}
	
	@GetMapping("/pray/nationsWrite")
	public String versesWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:nations";
		}
		return "pray/nationsWrite";
	}
	
	@PostMapping("/nations/insert")
	public String nationsInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Nations nations
			) {
		nations.setMemberNo(loginMember.getMemberNo());
		System.out.println("로그인멤버");
		System.out.println(loginMember);
		System.out.println(nations);
		int i = service.insertNations(nations);
		String path = "redirect:/pray/nations";
		return path;
	}
	
	
	// 공지사항 삭제
	@PostMapping(value="/nations/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String delete(
			RedirectAttributes ra
			,@RequestBody long nationsNo
			){
		int i=service.deleteNations(nationsNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "pray/nations";
	}
	
	// 공지사항 수정페이지
	@PostMapping("/nations/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Nations nations = service.detailedNations(no);
		model.addAttribute("nations", nations);
		return "pray/nationsModify";
	}

	// 공지사항 수정
	@PostMapping("/nations/update")
	public String update(Nations nations,
			Model model
			){
		int i = service.updatenations(nations);
		if(i==0) {
			
		}
		return "redirect:/pray/nations";
	}
}
