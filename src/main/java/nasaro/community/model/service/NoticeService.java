package nasaro.community.model.service;

import java.util.List;

import nasaro.community.model.dto.Notice;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface NoticeService {

	List<Notice> noticeList();

	Notice detailedNotice(String no);

	
}
