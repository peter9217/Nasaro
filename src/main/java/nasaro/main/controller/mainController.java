
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nasaro.admin.model.service.AdminService;
import nasaro.community.model.dto.Notice;
import nasaro.community.model.service.NoticeService;
import nasaro.gallery.model.service.GalleryService;

@Controller
public class mainController {
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private nasaro.community.model.service.SharingService SharingService;
	@Autowired
	private GalleryService galleryService;
	
	@ResponseBody
	@PostMapping("/main/csrf")
	public String csrfTest() {
		System.out.println("asd");
		return "asd";
	}
	
    @GetMapping("/")
    public String main(Model model) {
    	int cp = 1;
    	Map<String, Object> resultMap = new HashMap<>();

    	// slideList result
    	Map<String, Object> slideListResult = adminService.slideList();
    	resultMap.putAll(slideListResult);
    	
    	
    	// noticeList 서비스 결과를 resultMap에 추가
    	Map<String, Object> noticeListResult = noticeService.noticeList(cp);
    	resultMap.putAll(noticeListResult);
    	System.out.println(noticeListResult);

    	// familyList 서비스 결과를 resultMap에 추가
    	Map<String, Object> sharingListResult = SharingService.sharingList(cp);
    	resultMap.putAll(sharingListResult);
    	System.out.println(sharingListResult);
    	
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