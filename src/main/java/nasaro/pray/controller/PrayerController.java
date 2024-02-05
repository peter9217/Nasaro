package nasaro.pray.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PrayerController {
	
	@GetMapping("pray/prayer")
	public String prayer() {
		return "pray/prayer";
	}
}
