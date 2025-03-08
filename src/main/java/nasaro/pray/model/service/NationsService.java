package nasaro.pray.model.service;

import java.util.Map;

import nasaro.pray.model.dto.Nations;

public interface NationsService {

	/** nations 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> nationsList(int cp);
	
	/** nations 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> nationsList(Map<String, Object> paramMap, int cp);
	

	Nations detailedNations(String no);

	int insertNations(Nations nations);

	int deleteNations(long nationsNo);

	int updatenations(Nations nations);

	
}
