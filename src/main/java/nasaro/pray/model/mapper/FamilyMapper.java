package nasaro.pray.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.pray.model.dto.Family;

@Mapper
public interface FamilyMapper {


	/** 공지사항 count
	 * @return
	 */
	int familyListCount();
	
	/** 공지사항 검색어 count
	 * @param paramMap
	 * @return
	 */
	int familyListCountSearch(Map<String, Object> paramMap);
	
	/** 공지사항 목록
	 * @return
	 */
	List<Family> familyList(RowBounds rowBounds);
	
	
	/** 공지사항 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Family> searchFamilyList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	Family detailedFamily(String no);

	int insertFamily(Family family);

	int deleteFamily(long familyNo);

	int updateFamily(Family family);

	List<Family> bestFamilyList();

}

