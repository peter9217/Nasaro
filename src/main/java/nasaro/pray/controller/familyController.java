package nasaro.pray.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nasaro.pray.model.dto.Family;
import nasaro.pray.model.service.FamilyService;

@Controller
public class familyController {
	
	@Autowired
	private FamilyService service;
	
	@GetMapping("pray/family")
	public String family(Model model) {
		List<Family> familyList = new ArrayList<>();
		familyList = service.family();
		model.addAttribute("familyList", familyList);
		System.out.println(familyList);
		return "pray/family";
	}
	
	@GetMapping("pray/familyDetail/{no:[0-9]+}")
	public String detail(@PathVariable String no, Model model) {
		Family family = service.detailedFamily(no);
		model.addAttribute("family", family);
		System.out.println(family);
		return "pray/familyDetail";
		
	}
}
