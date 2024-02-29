package nasaro.verses.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.verses.model.dto.Column;

@Mapper
public interface ColumnMapper {


	/** 컬럼 count
	 * @return
	 */
	int columnListCount();
	
	/** 컬럼 검색어 count
	 * @param paramMap
	 * @return
	 */
	int columnListCountSearch(Map<String, Object> paramMap);
	
	/** 컬럼 목록
	 * @return
	 */
	List<Column> columnList(RowBounds rowBounds);
	
	
	/** 컬럼 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Column> searchColumnList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	/** verses 상세조회
	 * @param no 
	 * @return
	 */

	Column detailedColumn(String no);

	int insertColumn(Column column);

	int deleteColumn(long columnNo);

	int updateColumn(Column column);

}

