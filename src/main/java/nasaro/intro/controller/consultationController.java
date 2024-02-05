package nasaro.intro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class consultationController {
	@GetMapping("intro/consultation")
	public String home() {
		return "intro/consultation";
	}
}
