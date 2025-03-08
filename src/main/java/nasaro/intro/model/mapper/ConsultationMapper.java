package nasaro.intro.model.mapper;


import org.apache.ibatis.annotations.Mapper;

import nasaro.intro.model.dto.Intro;

@Mapper
public interface ConsultationMapper {

	Intro intro();

	int modifyIntro(Intro intro);



}

