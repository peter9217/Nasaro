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
import nasaro.verses.model.service.ColumnService;

@Controller
public class ColumnController {
	
	@Autowired
	private ColumnService service;
	
	@GetMapping("verses/column")
	public String column(
			Model model) {
		List<Column> columnList = new ArrayList();
		columnList = service.columnList();
		model.addAttribute("columnList", columnList);
		System.out.println(columnList);
		return "verses/column";
	}
	
	@GetMapping("column/columnDetail/{no:[0-9]+}")
	public String detail(@PathVariable String no, Model model) {
		Column column = service.detailedColumn(no);
		model.addAttribute("column", column);
		System.out.println(column);
		return "verses/columnDetail";
		
	}
}
