package nasaro.community.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.common.utility.Pagination;
import nasaro.community.model.dto.Free;
import nasaro.community.model.dto.Notice;
import nasaro.community.model.mapper.FreeMapper;
import nasaro.community.model.mapper.NoticeMapper;
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
public class FreeServiceImpl implements FreeService {

	@Autowired
	private FreeMapper mapper;

	/** 자유 리스트
	 *
	 */
	@Override
	public Map<String, Object> freeList(int cp) {
		int listCount = mapper.freeListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Free> freeList = mapper.freeList(rowBounds);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("freeList", freeList);
		
		return resultMap;
	}
	
	/** 자유 검색어를 포함한 리스트
	 *
	 */
	@Override
	public Map<String, Object> freeList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.freeListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Free> freeList = mapper.searchFreeList(paramMap, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("freeList", freeList);
		return map;
	}

	@Override
	public Free detailedFree(String no) {
		return mapper.detailedFree(no);
	}

	@Override
	public int insertFree(Free free) {
		return mapper.insertFree(free);
	}

	@Override
	public int deleteFree(long freeNo) {
		return mapper.deleteFree(freeNo);
	}


	@Override
	public int updateFree(Free free) {
		return mapper.updateFree(free);
	}




	

}
