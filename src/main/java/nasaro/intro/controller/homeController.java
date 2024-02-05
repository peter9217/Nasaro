package nasaro.intro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
	@GetMapping("intro/home")
	public String home() {
		return "intro/home";
	}
}
