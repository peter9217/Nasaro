package nasaro.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nasaro.member.model.dto.Member;
import nasaro.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체를 DI
	private BCryptPasswordEncoder bcrypt;

	@Override
	public Member login(Member inputMember) {
		Member loginMember = mapper.login(inputMember);
		if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우
			if(bcrypt.matches(inputMember.getMemberPw(),loginMember.getMemberPw())) {
				loginMember.setMemberPw(null);
			} else {
				loginMember = null;
			}
		}
		return loginMember;
	}
	
	@Override
	public void signOut( Member member) {
		Member loginMember = mapper.login(member);
		if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우
			if(bcrypt.matches(member.getMemberPw(),loginMember.getMemberPw())) {
				mapper.signOut(member);
			} else {
				loginMember = null;
			}
		}
	}


}
