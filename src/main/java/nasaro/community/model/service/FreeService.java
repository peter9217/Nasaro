package nasaro.community.model.service;

import java.util.List;
import java.util.Map;

import nasaro.community.model.dto.Free;
import nasaro.community.model.dto.Notice;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface FreeService {

	/** 자유 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> freeList(int cp);
	
	/** 자유 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> freeList(Map<String, Object> paramMap, int cp);
	

	Free detailedFree(String no);

	int insertFree(Free free);

	int deleteFree(long freeNo);

	int updateFree(Free free);


	
}
