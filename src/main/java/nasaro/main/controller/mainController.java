package nasaro.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nasaro.community.model.dto.Notice;
import nasaro.community.model.service.NoticeService;
import nasaro.gallery.model.service.GalleryService;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.service.FamilyService;

@Controller
public class mainController {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private FamilyService familyService;
	@Autowired
	private GalleryService galleryService;
    @GetMapping("/")
    public String main(Model model) {
    	int cp = 1;
    	Map<String, Object> resultMap = new HashMap<>();

    	// noticeList 서비스 결과를 resultMap에 추가
    	Map<String, Object> noticeListResult = noticeService.noticeList(cp);
    	resultMap.putAll(noticeListResult);
    	System.out.println(noticeListResult);

    	// familyList 서비스 결과를 resultMap에 추가
    	Map<String, Object> familyListResult = familyService.familyList(cp);
    	resultMap.putAll(familyListResult);
    	System.out.println(familyListResult);
    	
    	Map<String, Object> paramMap = new HashMap<>();
    	String categoryNo = null;
		paramMap.put("galleryCategory", categoryNo);
		Map<String, Object> galleryListResult = galleryService.galleryList(paramMap, cp);
		resultMap.putAll(galleryListResult);
		System.out.println(galleryListResult);
    	
		model.addAttribute("resultMap", resultMap);
        return "home";
    }
}