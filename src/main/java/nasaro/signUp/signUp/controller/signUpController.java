package nasaro.signUp.signUp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class signUpController {
	@GetMapping("sign/signUp")
	public String signUp() {
		return "sign/signUp";
	}
}