package nasaro.community.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.community.model.dto.Notice;

@Mapper
public interface NoticeMapper {

	/** 공지사항 count
	 * @return
	 */
	int noticeListCount();
	
	/** 공지사항 검색어 count
	 * @param paramMap
	 * @return
	 */
	int noticeListCountSearch(Map<String, Object> paramMap);
	
	/** 공지사항 목록
	 * @return
	 */
	List<Notice> noticeList(RowBounds rowBounds);
	
	
	/** 공지사항 검색어를 포함한 목록
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Notice> searchNoticeList(Map<String, Object> paramMap, RowBounds rowBounds);
	

	Notice detailedNotice(String no);

	int insertNotice(Notice notice);

	int deleteNotice(long noticeNo);

	int updateNotice(Notice notice);

}

