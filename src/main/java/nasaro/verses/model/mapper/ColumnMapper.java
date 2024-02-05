package nasaro.verses.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface ColumnMapper {


	/**Column 리스트 조회
	 * @return
	 */

	List<Column> columnList();

	Column detailedColumn(String no);

}

