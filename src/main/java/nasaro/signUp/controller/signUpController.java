package nasaro.signUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import nasaro.common.utility.SmsUtil;
import nasaro.member.model.dto.Member;
import nasaro.signUp.model.dto.Sms;
import nasaro.signUp.model.service.SignUpService;


@Controller
public class signUpController {
	@Autowired
	private SignUpService service;
	@Autowired
	private SmsUtil smsUtil;

	@GetMapping("sign/signUp")
	public String sign() {
		return "sign/signUp";
	}
	@GetMapping("sign/findMember")
	public String findMember() {
		return "sign/findMember";
	}
	
	
	@ResponseBody
	@PostMapping("signUp/sendSms")
	public String sendSms(@RequestBody String memberTel) {
		smsUtil.sendOne(memberTel);
		
		return "문자를 확인해주세요";
	}
	
	@ResponseBody
	@PostMapping(value="signUp/smsConfirm" )
	public String smsConfirm(@RequestBody Sms sms) {
		String r = smsUtil.checkCode(sms);
		return r;
	}
	@PostMapping("signUp/signUp")
	public String signUp(Member member) {
		service.signUp(member);
		return "redirect:/";
	}

	
	@ResponseBody
	@GetMapping("sign/checkId")
	public int checkId(@RequestParam(name="memberId") String memberId) {
		System.out.println(memberId);
		int i = service.checkId(memberId);
		return i;
	}
	@ResponseBody
	@PostMapping("signUp/checkNickname")
	public String checkNickname(@RequestBody String nick
			,@SessionAttribute(name="loginMember", required = false) Member loginMember) {
		loginMember.setMemberNickname(nick);
		int i = service.checkNickname(nick);
		String message = "";
		if(i==1) {
			message = "이미 있는 닉네임입니다.";
		}else {
			service.modifyNickname(loginMember);
			
		}
		return message;
	}
	
	
	@ResponseBody
	@PostMapping("signUp/modifyNickname")
	public String modifyNickname(@RequestBody String nick
			,@SessionAttribute(name="loginMember", required = false) Member loginMember) {
		loginMember.setMemberNickname(nick);
		service.modifyNickname(loginMember);
		String message = "true";
		return message;
	}
	
	@ResponseBody
	@PostMapping("signUp/modifyTel")
	public String modifyTel(@RequestBody  Sms sms) {
		String r = smsUtil.checkCode(sms);
		if (r.equals("코드가 일치합니다.")) {
			service.modifyTel(sms);
		}
		return r;
	}
	@PostMapping("signUp/findMember")
	public String findMember(Member member) {
		service.findMember(member);
		return "redirect:/";
	}
	

	
}