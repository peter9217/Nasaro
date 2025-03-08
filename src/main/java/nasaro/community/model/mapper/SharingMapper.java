package nasaro.community.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.community.model.dto.Sharing;

@Mapper
public interface SharingMapper {


	/** 나눔 count
	 * @return
	 */
	int sharingListCount();
	
	/** 나눔 검색어 count
	 * @param paramMap
	 * @return
	 */
	int sharingListCountSearch(Map<String, Object> paramMap);
	
	/** 나눔 목록
	 * @return
	 */
	List<Sharing> sharingList(RowBounds rowBounds);
	
	
	/** 나눔 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Sharing> searchSharingList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	Sharing detailedSharing(String no);

	int insertSharing(Sharing sharing);

	int deleteSharing(long sharingNo);

	int updateSharing(Sharing sharing);

	List<Sharing> bestSharingList();

}

