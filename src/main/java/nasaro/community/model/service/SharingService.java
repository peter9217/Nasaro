package nasaro.community.model.service;

import java.util.Map;

import nasaro.community.model.dto.Sharing;

public interface SharingService {

	/** 나눔 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> sharingList(int cp);
	
	/** 나눔 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> sharingList(Map<String, Object> paramMap, int cp);
	

	Sharing detailedSharing(String no);

	int insertSharing(Sharing sharing);

	int deleteSharing(long sharingNo);

	int updateSharing(Sharing sharing);


	
}
