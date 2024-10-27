package nasaro.verses.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.common.utility.Pagination;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;
import nasaro.verses.model.mapper.ReferenceMapper;

@Service
public class ReferenceServiceImpl implements ReferenceService {

	@Autowired
	private ReferenceMapper mapper;
	
	/** 공지사항 리스트
	 *
	 */
	@Override
	public Map<String, Object> referenceList(int cp) {
		int listCount = mapper.referenceListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Reference> referenceList = mapper.referenceList(rowBounds);
		List<Reference> referenceBestList = mapper.bestReferenceList();
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("referenceList", referenceList);
		resultMap.put("referenceBestList", referenceBestList);
		
		
		return resultMap;
	}
	
	/** 공지사항 검색어를 포함한 리스트
	 *
	 */
	@Override
	public Map<String, Object> referenceList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.referenceListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Reference> referenceList = mapper.searchReferenceList(paramMap, rowBounds);
		List<Reference> referenceBestList = mapper.bestReferenceList();
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("referenceList", referenceList);
		map.put("referenceBestList", referenceBestList);
		
		return map;
	}

	@Override
	public Reference detailedReference(String no) {
		return mapper.detailedReference(no);
	}

	@Override
	public int insertReference(Reference reference) {
		return mapper.insertReference(reference);
	}

	@Override
	public int deleteReference(long referenceNo) {
		return mapper.deleteReference(referenceNo);
	}

	@Override
	public int updateReference(Reference reference) {
		return mapper.updateReference(reference);
	}

}
