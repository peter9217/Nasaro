package nasaro.community.controller;

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

import nasaro.community.model.dto.Notice;
import nasaro.community.model.service.NoticeService;
import nasaro.member.model.dto.Member;
import nasaro.verses.model.dto.Verses;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping("/community/notice")
	public String notice(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.noticeList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.noticeList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		return "community/notice";
	}
	
	@GetMapping("/community/noticeDetail/{no}")
	public String board(@PathVariable(name="no") String no, Model model) {
		Notice notice = service.detailedNotice(no);
		model.addAttribute("notice", notice);
		return "community/noticeDetail";
	}
	
	@GetMapping("/community/noticeWrite")
	public String noticeWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:notice";
		}
		return "community/noticeWrite";
	}
	
	@PostMapping("/notice/insert")
	public String noticeInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Notice notice
			) {
		notice.setMemberNo(loginMember.getMemberNo());
		System.out.println("로그인멤버");
		System.out.println(loginMember);
		System.out.println(notice);
		int i = service.insertNotice(notice);
		String path = "redirect:/community/notice";
		return path;
	}

	// 공지사항 삭제
	@PostMapping(value="/notice/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String deleteNotice(
			RedirectAttributes ra
			,@RequestBody long noticeNo
			){
		int i=service.deleteNotice(noticeNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "community/notice";
	}
	
	// 공지사항 수정페이지
	@PostMapping("/notice/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Notice notice = service.detailedNotice(no);
		model.addAttribute("notice", notice);
		return "community/noticeModify";
	}

	// 공지사항 수정
	@PostMapping("/notice/update")
	public String update(Notice notice,
			Model model
			){
		int i = service.updateNotice(notice);
		if(i==0) {
			
		}
		return "redirect:/community/notice";
	}
}
