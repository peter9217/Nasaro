package nasaro.verses.controller;


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
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.service.ReferenceService;
import nasaro.verses.model.service.ColumnService;

@Controller
public class ColumnController {
	
	@Autowired
	private ColumnService service;
	
	@GetMapping("/verses/column")
	public String column(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.columnList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.columnList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		return "verses/column";
	}
	
	@GetMapping("/verses/columnDetail/{no:[0-9]+}")
	public String detail(@PathVariable(name="no") String no, Model model) {
		Column column = service.detailedColumn(no);
		model.addAttribute("column", column);
		System.out.println(column);
		return "verses/columnDetail";
		
	}
	
	@GetMapping("/verses/columnWrite")
	public String referenceWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember
			,RedirectAttributes ra
			) {
		if(loginMember==null) {
			ra.addFlashAttribute("message", "로그인을 해주십시오");
			return "redirect:column";
		}
		return "verses/columnWrite";
	}
	
	@GetMapping("/column/insert")
	public String columnInsert(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Column column
			) {
		column.setMemberNo(loginMember.getMemberNo());
		System.out.println(loginMember);
		int i = service.insertColumn(column);
		String path = "verses/column";
		return path;
	}
	
	// 공지사항 삭제
	@GetMapping(value="/column/delete",produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String delete(
			RedirectAttributes ra
			,@RequestParam(name="columnNo") long columnNo
			){
		int i=service.deleteColumn(columnNo);
		if(i==1) {
			ra.addFlashAttribute("message", "삭제되었습니다.");
		}
		return "verses/column";
	}
	
	// 공지사항 수정페이지
	@GetMapping("/column/modify/{no:[0-9]+}")
	public String modify(@PathVariable(name="no") String no
			,Model model) {
		Column column = service.detailedColumn(no);
		model.addAttribute("column", column);
		return "verses/columnModify";
	}

	// 공지사항 수정
	@GetMapping("/column/update")
	public String update(Column column,
			Model model
			){
		int i = service.updateColumn(column);
		if(i==0) {
			
		}
		return "redirect:/verses/column";
	}
}
