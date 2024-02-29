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
import nasaro.community.model.dto.Sharing;
import nasaro.community.model.mapper.FreeMapper;
import nasaro.community.model.mapper.NoticeMapper;
import nasaro.community.model.mapper.SharingMapper;
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
public class SharingServiceImpl implements SharingService {

	@Autowired
	private SharingMapper mapper;

	/** 나눔 리스트
	 *
	 */
	@Override
	public Map<String, Object> sharingList(int cp) {
		int listCount = mapper.sharingListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Sharing> sharingList = mapper.sharingList(rowBounds);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("sharingList", sharingList);
		
		return resultMap;
	}
	
	/** 나눔 검색어를 포함한 리스트
	 *
	 */
	@Override
	public Map<String, Object> sharingList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.sharingListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Sharing> sharingList = mapper.searchSharingList(paramMap, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("sharingList", sharingList);
		return map;
	}

	@Override
	public Sharing detailedSharing(String no) {
		return mapper.detailedSharing(no);
	}

	@Override
	public int insertSharing(Sharing sharing) {
		return mapper.insertSharing(sharing);
	}

	@Override
	public int deleteSharing(long sharingNo) {
		return mapper.deleteSharing(sharingNo);
	}

	@Override
	public int updateSharing(Sharing sharing) {
		return mapper.updateSharing(sharing);
	}



	

}
