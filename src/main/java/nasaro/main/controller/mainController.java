package nasaro.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nasaro.community.model.dto.Notice;
import nasaro.community.model.service.NoticeService;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.service.FamilyService;

@Controller
public class mainController {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private FamilyService familyService;
	
    @GetMapping("/")
    public String main(Model model) {
		List<Notice> noticeList = new ArrayList<>();
		noticeList = noticeService.noticeList();
		model.addAttribute("noticeList", noticeList);
		List<Family> familyList = new ArrayList<>();
		familyList = familyService.family();
		model.addAttribute("familyList", familyList);
		System.out.println(noticeList);
		System.out.println(familyList);
        return "home";
    }
}