package nasaro.verses.model.service;

import java.util.Map;

import nasaro.verses.model.dto.Verses;

public interface VersesService {

	/** 말씀 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> versesList(int cp);
	
	/** 말씀 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> versesList(Map<String, Object> paramMap, int cp);
	
	

	Verses detailedVerses(String no);

	int insertVerses(Verses verses);

	int deleteVerses(long versesNo);

	int updateVerses(Verses verses);
	

}
