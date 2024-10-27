package nasaro.intro.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.admin.model.mapper.AdminMapper;
import nasaro.common.utility.Pagination;
import nasaro.intro.model.dto.Consultation;
import nasaro.intro.model.dto.Intro;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;
import nasaro.verses.model.mapper.ColumnMapper;
import nasaro.verses.model.mapper.ReferenceMapper;

@Service
public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	private AdminMapper mapper;

	@Override
	public Consultation consultation() {
		return mapper.consultation();
	}


	

}
