package nasaro.verses.model.service;

import java.util.Map;

import nasaro.verses.model.dto.Column;

public interface ColumnService {

	/** 커ㅏㄹ럼 리스트
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> columnList(int cp);
	
	/** 컬럼 검색어를 포함한 리스트
	 * @param paramMap
	 * @param cp
	 * @return
	 */
	Map<String, Object> columnList(Map<String, Object> paramMap, int cp);
	

	Column detailedColumn(String no);

	int insertColumn(Column column);

	int deleteColumn(long columnNo);

	int updateColumn(Column column);
	

}
