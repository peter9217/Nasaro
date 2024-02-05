package nasaro.pray.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public List<Family> family() {
		return mapper.family();
	}

	@Override
	public Family detailedFamily(String no) {
		return mapper.detailedFamily(no);
	}

	

}
