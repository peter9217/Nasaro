package nasaro.gallery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class galleryController {
	
	@GetMapping("gallery/gallery")
	public String gallery(){
		return "gallery/gallery";
	}
}
