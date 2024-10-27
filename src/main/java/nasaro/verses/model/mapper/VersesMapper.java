package nasaro.verses.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.community.model.dto.Notice;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface VersesMapper {


	/** 공지사항 count
	 * @return
	 */
	int versesListCount();
	
	/** 공지사항 검색어 count
	 * @param paramMap
	 * @return
	 */
	int versesListCountSearch(Map<String, Object> paramMap);
	
	/** 공지사항 목록
	 * @return
	 */
	List<Verses> versesList(RowBounds rowBounds);
	
	
	/** 공지사항 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Verses> searchVersesList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	/** verses 상세조회
	 * @param no 
	 * @return
	 */
	Verses detailedVerses(String no);

	int insertVerses(Verses verses);

	int deleteVerses(long versesNo);

	int updateVerses(Verses verses);

	List<Notice> bestVersesList();

}

