package nasaro.signUp.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import nasaro.member.model.dto.Member;
import nasaro.signUp.model.dto.Sms;

@Mapper
public interface SignUpMapper {

	int checkNickname(String nick);

	int modifyNickname(Member loginMember);

	int checkId(String id);

	Member findId(String name, String phoneNum);

	int signUp(Member member);

	void modifyTel(Sms sms);

	int findMember(Member member);

	Member checkMember(Member member);

	



}
