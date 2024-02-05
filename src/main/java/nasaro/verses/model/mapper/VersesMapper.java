package nasaro.verses.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nasaro.verses.model.dto.Verses;

@Mapper
public interface VersesMapper {


	/**verses 리스트 조회
	 * @return
	 */

	List<Verses> versesList();

	/** verses 상세조회
	 * @param no 
	 * @return
	 */
	Verses detailedVerses(String no);

	int insertVerses(Verses verses);

}

