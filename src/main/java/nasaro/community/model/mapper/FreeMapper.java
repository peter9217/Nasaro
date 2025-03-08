package nasaro.community.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.community.model.dto.Free;

@Mapper
public interface FreeMapper {


	/** 공지사항 count
	 * @return
	 */
	int freeListCount();
	
	/** 공지사항 검색어 count
	 * @param paramMap
	 * @return
	 */
	int freeListCountSearch(Map<String, Object> paramMap);
	
	/** 공지사항 목록
	 * @return
	 */
	List<Free> freeList(RowBounds rowBounds);
	
	
	/** 공지사항 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Free> searchFreeList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	Free detailedFree(String no);

	int insertFree(Free free);

	int deleteFree(long freeNo);

	int updateFree(Free free);

	List<Free> bestFreeList();

}

