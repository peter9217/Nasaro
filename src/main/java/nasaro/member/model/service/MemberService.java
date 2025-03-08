package nasaro.member.model.service;

import nasaro.member.model.dto.Member;

public interface MemberService {

	Member login(Member inputMember);

	void signOut( Member member);
}
