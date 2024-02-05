package nasaro.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nasaro.community.model.dto.Free;
import nasaro.community.model.service.FreeService;
import nasaro.verses.model.dto.Verses;

@Controller
public class FreeContoroller {
	
	@Autowired
	private FreeService service;
	
	@GetMapping("community/free")
	public String free(
			Model model) {
		List<Free> freeList = new ArrayList();
		freeList = service.freeList();
		model.addAttribute("freeList", freeList);
		System.out.println(freeList);
		return "community/free";
	}

	@GetMapping("community/freeDetail/{no}")
	public String board(@PathVariable String no, Model model) {
		Free free = service.detailedFree();
		model.addAttribute("free", free);
		System.out.println(no);
		return "community/freeDetail";
	}
}
