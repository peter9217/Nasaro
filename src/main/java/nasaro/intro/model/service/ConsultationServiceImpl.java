package nasaro.intro.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.admin.model.mapper.AdminMapper;
import nasaro.intro.model.dto.Consultation;

@Service
public class ConsultationServiceImpl implements ConsultationService {

	@Autowired
	private AdminMapper mapper;

	@Override
	public Consultation consultation() {
		return mapper.consultation();
	}


	

}
