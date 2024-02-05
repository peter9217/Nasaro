package nasaro.member.model.service;

import java.util.List;

import nasaro.member.model.dto.Member;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface MemberService {

	Member login(Member inputMember);

	
}
