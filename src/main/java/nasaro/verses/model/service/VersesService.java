package nasaro.verses.model.service;

import java.util.List;

import nasaro.verses.model.dto.Verses;

public interface VersesService {

	List<Verses> versesList();

	Verses detailedVerses(String no);

	int insertVerses(Verses verses);
	

}
