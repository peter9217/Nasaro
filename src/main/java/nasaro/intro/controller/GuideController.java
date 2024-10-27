package nasaro.intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nasaro.intro.model.dto.Consultation;
import nasaro.intro.model.dto.Guide;
import nasaro.intro.model.service.GuideService;

@Controller
public class GuideController {
	@Autowired
	private GuideService service;
	
	@GetMapping("intro/guide")
	public String home(Model model) {
		Guide guide = new Guide();
		guide = service.guide();
		model.addAttribute(guide);
		return "intro/guide";
	}
}
