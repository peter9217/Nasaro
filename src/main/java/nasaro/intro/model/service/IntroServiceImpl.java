package nasaro.intro.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.admin.model.mapper.AdminMapper;
import nasaro.intro.model.dto.Intro;

@Service
public class IntroServiceImpl implements IntroService {

	@Autowired
	private AdminMapper mapper;

	@Override
	public Intro intro() {
		return mapper.intro();
	}


}
