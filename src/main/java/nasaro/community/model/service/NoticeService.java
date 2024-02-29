package nasaro.community.model.service;

import java.util.List;
import java.util.Map;

import nasaro.community.model.dto.Notice;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface NoticeService {

	/** 말씀 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> noticeList(int cp);
	
	/** 말씀 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> noticeList(Map<String, Object> paramMap, int cp);
	

	Notice detailedNotice(String no);

	int insertNotice(Notice notice);

	int deleteNotice(long noticeNo);

	int updateNotice(Notice notice);

	
}
