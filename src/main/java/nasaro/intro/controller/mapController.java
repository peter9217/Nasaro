package nasaro.intro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mapController {
	@GetMapping("intro/map")
	public String home() {
		return "intro/map";
	}
}
