package nasaro.pray.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import nasaro.pray.model.dto.Prayer;
import nasaro.pray.model.service.PrayerService;


@Controller
public class PrayerController {
	
	@Autowired
	private PrayerService service;
	
	@GetMapping("pray/prayer")
	public String prayer(Model model) {
		Prayer prayer = new Prayer();
		prayer = service.prayer();
		model.addAttribute(prayer);
		return "pray/prayer";
	}
}
