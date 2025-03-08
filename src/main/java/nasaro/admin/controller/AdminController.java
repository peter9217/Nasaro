package nasaro.admin.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import nasaro.admin.model.dto.Slide;
import nasaro.admin.model.service.AdminService;
import nasaro.community.model.service.FreeService;
import nasaro.community.model.service.NoticeService;
import nasaro.community.model.service.SharingService;
import nasaro.intro.model.dto.Intro;
import nasaro.intro.model.dto.Consultation;
import nasaro.intro.model.dto.Guide;
import nasaro.member.model.dto.Member;
import nasaro.pray.model.dto.Prayer;
import nasaro.pray.model.service.FamilyService;
import nasaro.pray.model.service.NationsService;
import nasaro.verses.model.service.ColumnService;
import nasaro.verses.model.service.ReferenceService;
import nasaro.verses.model.service.VersesService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;
	@Autowired
	private VersesService versesService;
	@Autowired
	private ColumnService columnService;
	@Autowired
	private ReferenceService referenceService;
	@Autowired
	private NationsService nationsService;
	@Autowired
	private FamilyService familyService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private SharingService sharingService;
	@Autowired
	private FreeService freeService;
//	@GetMapping(value = {"/admin"})
//	public String admin(
//			){
//		return "admin/admin";
//	}
	// 인트로 수정
	@GetMapping("/admin/slide")
	public String adminSlide(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Model model
			) {
		Map<String, Object> resultMap = service.slideList();
		model.addAttribute("resultMap", resultMap);
		return "admin/slide";
	}
	
	@PostMapping("/admin/modifySlide")
	public String modifySlide(@RequestParam(value = "image", required = false) MultipartFile image
			, Model model
			, Slide slide
			) throws FileUploadException{
		
		service.modifySlide(image, slide);
		return "redirect:/admin/slide";
	}
	
	
	@GetMapping("/admin/intro")
	public String adminIntro(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Model model
			) {
		Intro intro = new Intro();
		intro = service.intro();
		model.addAttribute(intro);
		return "admin/intro";
	}
	
	@PostMapping("/admin/modifyIntro")
	public String introModify(Intro intro
			, Model model
			){
		service.modifyIntro(intro);
		
		return "redirect:/admin/intro";
	}
	
	
	@PostMapping(value="/admin/modifyIntroImg", produces = "application/json; charset=UTF-8")
	public String introImgModify(Intro intro
			, Model model, @RequestParam("images") List<MultipartFile> images
			)throws IllegalStateException, IOException{
		service.modifyImgIntro(intro, images);
		
		return "redirect:/admin/intro";
	}
	
	
//	상담안내 수정
	@GetMapping("/admin/consultation")
	public String adminConsultation(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Model model
			) {
		Consultation consultation = new Consultation();
		consultation = service.consultation();
		model.addAttribute(consultation);
		return "admin/consultation";
	}
	
	// 공지사항 수정
	@PostMapping("/admin/modifyConsultation")
	public String consultationModify(Consultation consultation
			, Model model
			){
		service.modifyConsultation(consultation);
		
		
		return "redirect:/admin/consultation";
	}
	// 공지사항 수정
	@PostMapping(value="/admin/modifyConsultationImg", produces = "application/json; charset=UTF-8")
	public String consultationImgModify(Consultation consultation
			, Model model, @RequestParam("images") List<MultipartFile> images
			)throws IllegalStateException, IOException{
		service.modifyImgConsultation(consultation, images);
		
		return "redirect:/admin/consultation";
	}
	
	
	
//	맵 수정
	@GetMapping("/admin/guide")
	public String adminGuide(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Model model
			) {
		Guide guide = new Guide();
		guide = service.guide();
		model.addAttribute(guide);
		return "admin/guide";
	}
	
	// 공지사항 수정
	@PostMapping("/admin/modifyGuide")
	public String guideModify(Guide guide
			, Model model
			){
		service.modifyGuide(guide);
		
		return "redirect:/admin/guide";
	}
	// 공지사항 수정
	@PostMapping(value="/admin/modifyGuideImg", produces = "application/json; charset=UTF-8")
	public String guideImgModify(Guide guide
			, Model model, @RequestParam("images") List<MultipartFile> images
			)throws IllegalStateException, IOException{
		service.modifyImgGuide(guide, images);
		
		return "redirect:/admin/guide";
	}
	
	
	
//	기도문 수정
	@GetMapping("/admin/prayer")
	public String adminPrayer(
			@SessionAttribute(name="loginMember", required = false) Member loginMember
			,Model model
			) {
		Prayer prayer = new Prayer();
		prayer = service.prayer();
		model.addAttribute(prayer);
		return "admin/prayer";
	}
	
	// 공지사항 수정
	@PostMapping("/admin/modifyPrayer")
	public String prayerModify(Prayer prayer
			, Model model
			){
		service.modifyPrayer(prayer);
		return "redirect:/admin/prayer";
	}
	// 공지사항 수정
	@PostMapping("/admin/modifyPrayerImg")
	public String prayerImgModify(Prayer prayer
			, Model model, @RequestParam("images") List<MultipartFile> images
			)throws IllegalStateException, IOException{
		int i = service.modifyImgPrayer(prayer, images);
		if(i==0) {
			
		}
		return "redirect:/admin/prayer";
	}
	
	
	
	@GetMapping("/admin/member")
	public String adminMember(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap = service.memberList(cp);
			model.addAttribute("resultMap", resultMap);
			
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = service.memberList(paramMap, cp);
			model.addAttribute("resultMap", resultMap);
		}
		return "admin/member";
	}

	@ResponseBody
	@PostMapping("/admin/modifyMember")
	public String modifyMember(@RequestBody Member member,
			Model model
			) {
		int result = service.modifyMember(member);
		return "수정되었습니다.";
	}
	
	@PostMapping("/admin/modifyBoard")
	public String modifyBoard(
			RedirectAttributes attributes
			, @RequestParam(value="board", required=false) String board
			, @RequestParam(value="boardNo", required=false) int boardNo
			, @RequestParam(value="boardChoice", required=false) String boardChoice
			) {
		int i = service.modifyBoard(board, boardNo, boardChoice);
		attributes.addAttribute("board", board);
		return "redirect:/admin/board";
	}
	
	@GetMapping("/admin/board")
	public String adminBoard(
			Model model
			, @RequestParam(value="cp", required=false, defaultValue="1") int cp
			, @RequestParam Map<String, Object> paramMap
			, @RequestParam(value="board", required=false, defaultValue="verses") String board
			) {
		if(paramMap.get("key") == null) { 
			Map<String, Object> resultMap =new HashMap<>();
			switch (board) {
				case "verses": {
					resultMap=versesService.versesList(cp);
					break;
				}
				case "column": {
					resultMap=columnService.columnList(cp);
					break;
				}
				case "reference": {
					resultMap=referenceService.referenceList(cp);
					break;
				}
				case "nations": {
					resultMap=nationsService.nationsList(cp);
					break;
				}
				case "family": {
					resultMap=familyService.familyList(cp);
					break;
				}
				case "notice": {
					resultMap=noticeService.noticeList(cp);
					break;
				}
				case "sharing": {
					resultMap=sharingService.sharingList(cp);
					break;
				}
				case "free": {
					resultMap=freeService.freeList(cp);
					break;
				}
			}
			model.addAttribute("resultMap", resultMap);
		//검색어 있을 때
		}else {
			Map<String, Object> resultMap = new HashMap<>();
			switch (board) {
				case "verses": {
					resultMap=versesService.versesList(paramMap, cp);
					break;
				}
				case "column": {
					resultMap=columnService.columnList(paramMap, cp);
					break;
				}
				case "reference": {
					resultMap=referenceService.referenceList(paramMap, cp);
					break;
				}
				case "nations": {
					resultMap=nationsService.nationsList(paramMap, cp);
					break;
				}
				case "family": {
					resultMap=familyService.familyList(paramMap, cp);
					break;
				}
				case "notice": {
					resultMap=noticeService.noticeList(paramMap, cp);
					break;
				}
				case "sharing": {
					resultMap=sharingService.sharingList(paramMap, cp);
					break;
				}
				case "free": {
					resultMap=freeService.freeList(paramMap, cp);
					break;
				}
			}
			model.addAttribute("resultMap", resultMap);
		}
		model.addAttribute("board",board);
		return "admin/board";
	}
}
