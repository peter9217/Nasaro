package nasaro.verses.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.service.ReferenceService;

@Controller
public class ReferenceController {
	
	@Autowired
	private ReferenceService service;
	
	@GetMapping("verses/reference")
	public String reference(
			Model model) {
		List<Reference> referenceList = new ArrayList();
		referenceList = service.referenceList();
		model.addAttribute("referenceList", referenceList);
		System.out.println(referenceList);
		return "verses/reference";
	}
	
	@GetMapping("reference/referenceDetail/{no:[0-9]+}")
	public String detail(@PathVariable String no, Model model) {
		Reference reference = service.detailedReference(no);
		model.addAttribute("reference", reference);
		System.out.println(reference);
		return "verses/referenceDetail";
		
	}
}
