package nasaro.pray.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.common.utility.Pagination;
import nasaro.community.model.dto.Sharing;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.pray.model.mapper.FamilyMapper;
import nasaro.pray.model.mapper.NationsMapper;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;
import nasaro.verses.model.mapper.ColumnMapper;
import nasaro.verses.model.mapper.ReferenceMapper;

@Service
public class FamilyServiceImpl implements FamilyService {

	@Autowired
	private FamilyMapper mapper;

	/** 공지사항 리스트
	 *
	 */
	@Override
	public Map<String, Object> familyList(int cp) {
		int listCount = mapper.familyListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Family> familyList = mapper.familyList(rowBounds);
		List<Family> familyBestList = mapper.bestFamilyList();
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("familyList", familyList);
		resultMap.put("familyBestList", familyBestList);
		
		return resultMap;
	}
	
	/** 공지사항 검색어를 포함한 리스트
	 *
	 */
	@Override
	public Map<String, Object> familyList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.familyListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Family> familyList = mapper.searchFamilyList(paramMap, rowBounds);
		List<Family> familyBestList = mapper.bestFamilyList();
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pagination", pagination);
		resultMap.put("familyList", familyList);
		resultMap.put("familyBestList", familyBestList);
		return resultMap;
	}

	@Override
	public Family detailedFamily(String no) {
		return mapper.detailedFamily(no);
	}

	@Override
	public int insertFamily(Family family) {
		return mapper.insertFamily(family);
	}

	@Override
	public int deleteFamily(long familyNo) {
		return mapper.deleteFamily(familyNo);
	}

	@Override
	public int updateFamily(Family family) {
		return mapper.updateFamily(family);
	}


	

}
