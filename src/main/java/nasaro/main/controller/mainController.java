
package nasaro.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nasaro.admin.model.service.AdminService;
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

    	// familyList 서비스 결과를 resultMap에 추가
    	Map<String, Object> sharingListResult = SharingService.sharingList(cp);
    	resultMap.putAll(sharingListResult);
    	
    	Map<String, Object> paramMap = new HashMap<>();
    	String categoryNo = null;
		paramMap.put("galleryCategory", categoryNo);
		Map<String, Object> galleryListResult = galleryService.galleryList(paramMap, cp);
		resultMap.putAll(galleryListResult);
    	
		model.addAttribute("resultMap", resultMap);
        return "home";
    }
}