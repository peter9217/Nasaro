package nasaro.community.controller;

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

import nasaro.community.model.dto.Free;
import nasaro.community.model.service.FreeService;
import nasaro.member.model.dto.Member;

@Controller
public class FreeContoroller {
	
	@Autowired
	private FreeService service;
	
	@GetMapping("community/free")
	public String free(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.freeList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.freeList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		return "community/free";
	}

	@GetMapping("community/freeDetail/{no}")
	public String board(@PathVariable(name="no") String no, Model model) {
		Free free = service.detailedFree(no);
		model.addAttribute("free", free);
		return "community/freeDetail";
	}
	
	@PostMapping("/community/freeWrite")
	public String freeWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:free";
		}
		return "community/freeWrite";
	}
	
	@GetMapping("/free/insert")
	public String versesInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Free free
			) {
		free.setMemberNo(loginMember.getMemberNo());
		service.insertFree(free);
		String path = "redirect:/community/free";
		return path;
	}
	
	// 공지사항 삭제
	@GetMapping(value="/free/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String deleteFree(
			RedirectAttributes ra
			,@RequestBody long freeNo
			){
		int i=service.deleteFree(freeNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "community/free";
	}
	
	// 공지사항 수정페이지
	@GetMapping("/free/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Free free = service.detailedFree(no);
		model.addAttribute("free", free);
		return "community/freeModify";
	}

	// 공지사항 수정
	@GetMapping("/free/update")
	public String update(Free free,
			Model model
			){
		service.updateFree(free);
		return "redirect:/community/free";
	}
}
