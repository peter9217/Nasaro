package nasaro.pray.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface NationsMapper {


	/** 공지사항 count
	 * @return
	 */
	int nationsListCount();
	
	/** 공지사항 검색어 count
	 * @param paramMap
	 * @return
	 */
	int nationsListCountSearch(Map<String, Object> paramMap);
	
	/** 공지사항 목록
	 * @return
	 */
	List<Nations> nationsList(RowBounds rowBounds);
	
	
	/** 공지사항 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Nations> searchNationsList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	Nations detailedNations(String no);

	int insertNations(Nations nations);

	int deleteNations(long nationsNo);

	int updatenations(Nations nations);

}

