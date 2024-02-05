package nasaro.pray.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nasaro.pray.model.dto.Nations;
import nasaro.pray.model.service.NationsService;
import nasaro.verses.model.dto.Verses;

@Controller
public class nationsController {
	
	@Autowired
	private NationsService service;
	
	@GetMapping("pray/nations")
	public String nations(Model model) {
		List<Nations> nationsList = new ArrayList();
		nationsList = service.nations();
		model.addAttribute("nationsList", nationsList);
		System.out.println(nationsList);
		return "pray/nations";
	}
	
	@GetMapping("pray/nationsDetail/{no:[0-9]+}")
	public String detail(@PathVariable String no, Model model) {
		Nations nations = service.detailedNations(no);
		model.addAttribute("nations", nations);
		System.out.println(nations);
		return "pray/nationsDetail";
		
	}
}
