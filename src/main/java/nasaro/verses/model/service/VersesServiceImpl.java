package nasaro.verses.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;

@Service
public class VersesServiceImpl implements VersesService {

	@Autowired
	private VersesMapper mapper;
	
// verses 리스트 조회
	@Override
	public List<Verses> versesList() {
		return mapper.versesList();
	}

	/** verses 상세조회
	 *
	 */
	@Override
	public Verses detailedVerses(String no) {
		return mapper.detailedVerses(no);
	}


	/**verses 쓰기
	 *
	 */
	@Override
	public int insertVerses(Verses verses) {
		return mapper.insertVerses(verses);
	}

}
