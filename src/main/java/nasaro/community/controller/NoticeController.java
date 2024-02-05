package nasaro.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nasaro.community.model.dto.Notice;
import nasaro.community.model.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping("community/notice")
	public String notice(
			Model model) {
		List<Notice> noticeList = new ArrayList<>();
		noticeList = service.noticeList();
		model.addAttribute("noticeList", noticeList);
		return "community/notice";
	}
	
	@GetMapping("community/noticeDetail/{no}")
	public String board(@PathVariable String no, Model model) {
		Notice notice = service.detailedNotice(no);
		model.addAttribute("notice", notice);
		return "community/noticeDetail";
	}


}
