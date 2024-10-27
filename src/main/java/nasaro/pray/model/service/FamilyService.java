package nasaro.pray.model.service;

import java.util.List;
import java.util.Map;

import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface FamilyService {

	/** free 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> familyList(int cp);
	
	/** free 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> familyList(Map<String, Object> paramMap, int cp);
	
	

	Family detailedFamily(String no);

	int insertFamily(Family family);

	int deleteFamily(long familyNo);

	int updateFamily(Family family);


	
}
