package nasaro.verses.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.common.utility.Pagination;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;

@Service
public class VersesServiceImpl implements VersesService {

	@Autowired
	private VersesMapper mapper;
	
	/** 공지사항 리스트
	 *
	 */
	@Override
	public Map<String, Object> versesList(int cp) {
		int listCount = mapper.versesListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Verses> versesList = mapper.versesList(rowBounds);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("versesList", versesList);
		
		return resultMap;
	}
	
	/** 공지사항 검색어를 포함한 리스트
	 *
	 */
	@Override
	public Map<String, Object> versesList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.versesListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Verses> versesList = mapper.searchVersesList(paramMap, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("versesList", versesList);
		return map;
	}

	/** verses 상세조회
	 *
	 */
	@Override
	public Verses detailedVerses(String no) {
		return mapper.detailedVerses(no);
	}


	/**verses 쓰기
	 *
	 */
	@Override
	public int insertVerses(Verses verses) {
		return mapper.insertVerses(verses);
	}

	@Override
	public int deleteVerses(long versesNo) {
		return mapper.deleteVerses(versesNo);
	}

	@Override
	public int updateVerses(Verses verses) {
		return mapper.updateVerses(verses);
	}

}
