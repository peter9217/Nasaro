package nasaro.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nasaro.community.model.dto.Sharing;
import nasaro.community.model.service.SharingService;

@Controller
public class SharingController {
	
	@Autowired
	private SharingService service;
	@GetMapping("community/sharing")
	public String sharing(
			Model model) {
		List<Sharing> sharingList = new ArrayList<>();
		sharingList = service.sharingList();
		model.addAttribute(sharingList);
		return "community/sharing";
	}

	@GetMapping("community/sharingDetail/{no}")
	public String board(@PathVariable String no, Model model) {
		Sharing sharing = service.detailedSharing(no);
		model.addAttribute(sharing);
		System.out.println(no);
		return "community/sharingDetail";
	}
}
