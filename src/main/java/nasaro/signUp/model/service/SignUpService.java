package nasaro.signUp.model.service;

import java.util.List;

import nasaro.member.model.dto.Member;
import nasaro.signUp.model.dto.Sms;


public interface SignUpService {

	int checkId(String id);

	int checkNickname(String nick);

	int modifyNickname(Member loginMember);

	Member findId(String name, String phoneNum);

	int signUp(Member member);

	void modifyTel(Sms sms);

	int findMember(Member member);

	


	
}
