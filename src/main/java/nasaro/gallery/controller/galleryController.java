package nasaro.gallery.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import nasaro.gallery.model.dto.Gallery;
import nasaro.gallery.model.service.GalleryService;
import nasaro.member.model.dto.Member;

@Controller
public class galleryController {
	
	@Autowired
	private GalleryService service;
	
	@GetMapping(value = {"/gallery/gallery/{categoryNo:[0-9]+}"})
	public String gallery(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap
			, @PathVariable("categoryNo") String categoryNo){
		if(categoryNo.equals("4")) {
			categoryNo= null;
		};
		paramMap.put("galleryCategory", categoryNo);
		if(paramMap.get("query") == null) { 
			Map<String, Object> resultMap = service.galleryList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
			
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.galleryList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
			System.out.println(resultMap);
		}
		model.addAttribute("categoryNo", categoryNo);
		return "gallery/gallery";
	}
	
	@GetMapping("/gallery/write")
	public String galleryWrite(@SessionAttribute(name="loginMember", required = false) Member loginMember){
		
		return "gallery/gallerywrite";
	}
	
	/**
	 * 갤러리 추가
	 * @param gallery
	 * @param images
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping(value="/gallery/insert", produces = "application/json; charset=UTF-8")
	public String insert(Gallery gallery, @RequestParam("images") List<MultipartFile> images)
			throws IllegalStateException, IOException {
		System.out.println(images);
		System.out.println(gallery);
		
		service.insertGallery(gallery, images);
		return "redirect:gallery";
	}
}