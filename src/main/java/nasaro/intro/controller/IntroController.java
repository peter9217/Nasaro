package nasaro.intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nasaro.intro.model.dto.Intro;
import nasaro.intro.model.service.IntroService;

@Controller
public class IntroController {
	
	@Autowired
	private IntroService service;
	
	@GetMapping("intro/intro")
	public String intro(Model model) {
		Intro intro = new Intro();
		intro = service.intro();
		model.addAttribute(intro);
		return "intro/intro";
	}
}
