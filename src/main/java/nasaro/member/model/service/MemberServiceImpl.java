package nasaro.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nasaro.member.model.dto.Member;
import nasaro.member.model.mapper.MemberMapper;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.pray.model.mapper.FamilyMapper;
import nasaro.pray.model.mapper.NationsMapper;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;
import nasaro.verses.model.mapper.ColumnMapper;
import nasaro.verses.model.mapper.ReferenceMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;


	@Override
	public Member login(Member inputMember) {
		return mapper.login(inputMember);
	}

	

}
