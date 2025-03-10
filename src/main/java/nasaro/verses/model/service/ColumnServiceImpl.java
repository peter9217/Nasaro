package nasaro.verses.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.common.utility.Pagination;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.mapper.ColumnMapper;

@Service
public class ColumnServiceImpl implements ColumnService {

	@Autowired
	private ColumnMapper mapper;
	
	@Override
	public Map<String, Object> columnList(int cp) {
		int listCount = mapper.columnListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Column> columnList = mapper.columnList(rowBounds);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("columnList", columnList);
		List<Column> columnBestList = mapper.bestColumnList();
		resultMap.put("columnBestList", columnBestList);
		
		return resultMap;
	}
	
	/** 공지사항 검색어를 포함한 리스트
	 *
	 */
	@Override
	public Map<String, Object> columnList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.columnListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Column> columnList = mapper.searchColumnList(paramMap, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("columnList", columnList);
		List<Column> columnBestList = mapper.bestColumnList();
		map.put("columnBestList", columnBestList);
		
		return map;
	}

	@Override
	public Column detailedColumn(String no) {
		return mapper.detailedColumn(no);
	}

	@Override
	public int insertColumn(Column column) {
		return mapper.insertColumn(column);
	}

	@Override
	public int deleteColumn(long columnNo) {
		return mapper.deleteColumn(columnNo);	
	}

	@Override
	public int updateColumn(Column column) {
		return mapper.updateColumn(column);
	}

}
