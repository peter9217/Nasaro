package nasaro.pray.model.service;

import java.util.List;

import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface FamilyService {

	List<Family> family();

	Family detailedFamily(String no);

	
}
