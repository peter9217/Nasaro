package nasaro.verses.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface ReferenceMapper {


	/**verses 리스트 조회
	 * @return
	 */

	List<Reference> referenceList();

	Reference detailedReference(String no);

}

