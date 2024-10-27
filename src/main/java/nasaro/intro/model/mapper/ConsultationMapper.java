package nasaro.intro.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.intro.model.dto.Intro;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface ConsultationMapper {

	Intro intro();

	int modifyIntro(Intro intro);



}

