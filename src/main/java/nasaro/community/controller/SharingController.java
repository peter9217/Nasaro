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

import nasaro.community.model.dto.Sharing;
import nasaro.community.model.service.SharingService;
import nasaro.member.model.dto.Member;

@Controller
public class SharingController {
	
	@Autowired
	private SharingService service;
	@GetMapping("community/sharing")
	public String sharing(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.sharingList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.sharingList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
		}
		return "community/sharing";
	}

	@GetMapping("community/sharingDetail/{no}")
	public String board(@PathVariable(name="no") String no, Model model) {
		Sharing sharing = service.detailedSharing(no);
		model.addAttribute(sharing);
		return "community/sharingDetail";
	}
	
	@GetMapping("/community/sharingWrite")
	public String sharingWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:sharing";
		}
		return "community/sharingWrite";
	}
	
	@PostMapping("/sharing/insert")
	public String sharingInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Sharing sharing
			) {
		sharing.setMemberNo(loginMember.getMemberNo());
		service.insertSharing(sharing);
		String path = "redirect:/community/sharing";
		return path;
	}
	
	// 공지사항 삭제
	@PostMapping(value="/sharing/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String delete(
			RedirectAttributes ra
			,@RequestBody long sharingNo
			){
		int i=service.deleteSharing(sharingNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "community/sharing";
	}
	
	// 공지사항 수정페이지
	@PostMapping("/sharing/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Sharing sharing = service.detailedSharing(no);
		model.addAttribute("sharing", sharing);
		return "community/sharingModify";
	}

	// 공지사항 수정
	@PostMapping("/sharing/update")
	public String update(Sharing sharing,
			Model model
			){
		service.updateSharing(sharing);
		
		return "redirect:/community/sharing";
	}
}
