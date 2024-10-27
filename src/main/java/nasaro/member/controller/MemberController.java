package nasaro.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nasaro.member.model.dto.Member;
import nasaro.member.model.service.MemberService;

@Controller
@SessionAttributes({"loginMember"})
public class MemberController {

	@Autowired
	private MemberService service;
	
    // 로그인
	@PostMapping("/login")
	public String login(Member inputMember, Model model,
			@RequestHeader(value="referer") String referer
			, HttpServletResponse resp
			, RedirectAttributes ra
			, HttpServletRequest request) {
			
		Member loginMember = service.login(inputMember);
		String path = "redirect:"; 
		path += referer;
		if(loginMember != null) { // 로그인 성공 시
			model.addAttribute("loginMember", loginMember);
			// 기존에 있던 세션 정보를 초기화
			request.getSession().invalidate();
			request.getSession(true);
//			Cookie cookie = new Cookie("saveId", loginMember.getMemberId());
//			if(saveId != null) { // 체크 되었을 때
//				cookie.setMaxAge(60 * 60 * 24 * 30); // 한 달간 유지
//			}else {
//				cookie.setMaxAge(0); // 0초동안 유지(기존 쿠키 삭제)
//			};
//			cookie.setPath("/"); 
//			resp.addCookie(cookie);
		}else {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		System.out.println(loginMember);
		return path;
	}
	@GetMapping("/logout")
	 public String logout(SessionStatus status) {
		status.setComplete(); 
	    return "redirect:/";
	}
	
	@GetMapping("myPage/modifySignUp")
	public String modifySignUp(
			@SessionAttribute(name="loginMember", required = false) Member loginMember 
			) {
			
		return "sign/modifySignUp";
	}
	@GetMapping("myPage/modifyPassword")
	public String modifyPassword(
			@SessionAttribute(name="loginMember", required = false) Member loginMember 
			) {
		System.out.println("asd");
		return "sign/modifyPassword";
	}
	@GetMapping("myPage/signOut")
	public String signOutPage(
			@SessionAttribute(name="loginMember", required = false) Member loginMember 
			) {
		
		return "sign/signOut";
	}
	@PostMapping("signUp/signOut")
	public String signOut( Member member) {
		service.signOut(member);
		return "redirect:/";
	}
//	// 로그인 기능
//	@PostMapping("/login")
//	public String login(Member inputMember, Model model
//			, @RequestHeader(value="referer") String referer
//			, @RequestParam(value="saveId", required=false) String saveId
//			, HttpServletResponse resp
//			, RedirectAttributes ra
//			, HttpServletRequest request
//			) {
//		
//		Member loginMember = service.login(inputMember);
//		String path = "redirect:"; 
//		if(loginMember != null) { // 로그인 성공 시
//			
//			
//			// 비회원인지 조회 후 이전페이지로 리다이렉트
//			if(loginMember.getMemberNot().toUpperCase().equals("Y")) {
//				ra.addFlashAttribute("message", "회원가입이 되어있지 않은 회원입니다.");
//				return path += referer;
//			}
//			
//			// 기존에 있던 세션 정보를 초기화
//			request.getSession().invalidate();
//			request.getSession(true);
//
//			path += "/"; // 메인페이지로 리다이렉트
//			model.addAttribute("loginMember", loginMember);
//			
//			// 로그인한 회원의 찜 목록 리스트(productNo)
//			List<Long> likeLikst = likeServcie.selectLikeList(loginMember.getMemberNo());
//			model.addAttribute("likeList", likeLikst);
//		
//			// 로그인한 회원의 장바구니 상품 갯수
//			int cartCount = cartService.getCartCount(loginMember.getMemberNo());
//			model.addAttribute("cartCount", cartCount);
//			
//			
//			Cookie cookie = new Cookie("saveId", loginMember.getMemberId());
//			if(saveId != null) { // 체크 되었을 때
//				cookie.setMaxAge(60 * 60 * 24 * 30); // 한 달간 유지
//			}else {
//				cookie.setMaxAge(0); // 0초동안 유지(기존 쿠키 삭제)
//			};
//			cookie.setPath("/"); 
//			resp.addCookie(cookie);
//			
//			
//			
//		} else { // 로그인 실패 시
//			path += referer; // HTTP Header - referer(이전 주소)
//			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
//		}
//		
//		return path;
//	}
//
//	// 로그아웃 기능
//	@GetMapping("/logout")
//	 public String logout(SessionStatus status) {
//		status.setComplete(); 
//	    return "redirect:/";
//	}
}
