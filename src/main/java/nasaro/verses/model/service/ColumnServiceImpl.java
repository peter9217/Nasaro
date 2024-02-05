package nasaro.verses.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;
import nasaro.verses.model.mapper.ColumnMapper;
import nasaro.verses.model.mapper.ReferenceMapper;

@Service
public class ColumnServiceImpl implements ColumnService {

	@Autowired
	private ColumnMapper mapper;
	
// verses 리스트 조회
	@Override
	public List<Column> columnList() {
		return mapper.columnList();
	}

	@Override
	public Column detailedColumn(String no) {
		return mapper.detailedColumn(no);
	}

}
