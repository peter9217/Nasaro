package nasaro.community.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.common.utility.Pagination;
import nasaro.community.model.dto.Notice;
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
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;

	/** 공지사항 리스트
	 *
	 */
	@Override
	public Map<String, Object> noticeList(int cp) {
		int listCount = mapper.noticeListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Notice> noticeList = mapper.noticeList(rowBounds);
		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("noticeList", noticeList);
		List<Notice> noticeBestList = mapper.bestNoticeList();
		resultMap.put("noticeBestList", noticeBestList);
		
		return resultMap;
	}
	
	/** 공지사항 검색어를 포함한 리스트
	 *
	 */
	@Override
	public Map<String, Object> noticeList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.noticeListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Notice> noticeBestList = mapper.bestNoticeList();
		List<Notice> noticeList = mapper.searchNoticeList(paramMap, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		map.put("noticeBestList", noticeBestList);
		return map;
	}
	@Override
	public Notice detailedNotice(String no) {
		return mapper.detailedNotice(no);
	}

	@Override
	public int insertNotice(Notice notice) {
		return mapper.insertNotice(notice);
	}

	@Override
	public int deleteNotice(long noticeNo) {
		return mapper.deleteNotice(noticeNo);
	}

	@Override
	public int updateNotice(Notice notice) {
		return mapper.updateNotice(notice);
	}


	

}
