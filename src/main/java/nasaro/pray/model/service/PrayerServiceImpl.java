package nasaro.pray.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.admin.model.mapper.AdminMapper;
import nasaro.pray.model.dto.Prayer;

@Service
public class PrayerServiceImpl implements PrayerService {

	@Autowired
	private AdminMapper mapper;

	@Override
	public Prayer prayer() {
		return mapper.prayer();
	}

}
