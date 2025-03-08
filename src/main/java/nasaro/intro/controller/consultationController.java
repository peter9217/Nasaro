package nasaro.intro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nasaro.intro.model.dto.Consultation;
import nasaro.intro.model.service.ConsultationService;

@Controller
public class ConsultationController {
	@Autowired
	private ConsultationService service;
	
	@GetMapping("intro/consultation")
	public String home(Model model) {
		Consultation consultation = new Consultation();
		consultation = service.consultation();
		model.addAttribute(consultation);
		return "intro/consultation";
	}
}
