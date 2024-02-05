package nasaro.verses.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;
import nasaro.verses.model.mapper.ReferenceMapper;

@Service
public class ReferenceServiceImpl implements ReferenceService {

	@Autowired
	private ReferenceMapper mapper;
	
// verses 리스트 조회
	@Override
	public List<Reference> referenceList() {
		return mapper.referenceList();
	}

	@Override
	public Reference detailedReference(String no) {
		return mapper.detailedReference(no);
	}

}
