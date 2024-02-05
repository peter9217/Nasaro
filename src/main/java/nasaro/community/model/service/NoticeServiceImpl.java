package nasaro.community.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public List<Notice> noticeList() {
		return mapper.noticeList();
	}

	@Override
	public Notice detailedNotice(String no) {
		return mapper.detailedNotice(no);
	}


	

}
