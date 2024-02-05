package nasaro.verses.model.service;

import java.util.List;

import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface ReferenceService {

	List<Reference> referenceList();

	Reference detailedReference(String no);
	

}
