package nasaro.verses.model.service;

import java.util.Map;

import nasaro.verses.model.dto.Reference;

public interface ReferenceService {

	/** ckarh 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> referenceList(int cp);
	
	/** ckarh 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> referenceList(Map<String, Object> paramMap, int cp);
	

	Reference detailedReference(String no);

	int insertReference(Reference reference);

	int deleteReference(long referenceNo);

	int updateReference(Reference reference);
	

}
