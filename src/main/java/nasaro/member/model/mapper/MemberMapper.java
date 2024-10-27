package nasaro.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import nasaro.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(Member inputMember);
	
	void signOut(Member member);
}
