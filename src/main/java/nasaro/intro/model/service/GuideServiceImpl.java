package nasaro.intro.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.admin.model.mapper.AdminMapper;
import nasaro.intro.model.dto.Guide;

@Service
public class GuideServiceImpl implements GuideService {

	@Autowired
	private AdminMapper mapper;

	@Override
	public Guide guide() {
		return mapper.guide();
	}


}
