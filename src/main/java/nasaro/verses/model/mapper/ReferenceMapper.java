package nasaro.verses.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface ReferenceMapper {


	/** 공지사항 count
	 * @return
	 */
	int referenceListCount();
	
	/** 공지사항 검색어 count
	 * @param paramMap
	 * @return
	 */
	int referenceListCountSearch(Map<String, Object> paramMap);
	
	/** 공지사항 목록
	 * @return
	 */
	List<Reference> referenceList(RowBounds rowBounds);
	
	
	/** 공지사항 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Reference> searchReferenceList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	Reference detailedReference(String no);

	int insertReference(Reference reference);

	int deleteReference(long referenceNo);

	int updateReference(Reference reference);

	List<Reference> bestReferenceList();

}

