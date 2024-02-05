package nasaro.verses.model.service;

import java.util.List;

import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface ColumnService {

	List<Column> columnList();

	Column detailedColumn(String no);
	

}
