package nasaro.signUp.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nasaro.common.utility.RandomUtil;
import nasaro.common.utility.SmsUtil;
import nasaro.member.model.dto.Member;
import nasaro.signUp.model.dto.Sms;
import nasaro.signUp.model.mapper.SignUpMapper;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체를 DI
	private BCryptPasswordEncoder bcrypt;
	@Autowired
	private SignUpMapper mapper;

    @Autowired
    private RandomUtil randomUtil;
    @Autowired
    private SmsUtil smsUtil;
	
	@Override
	public int checkNickname(String nick) {
		return mapper.checkNickname(nick);
	}


	@Override
	public int modifyNickname(Member loginMember) {
		System.out.println(loginMember);
		return mapper.modifyNickname(loginMember);
	}


	@Override
	public int checkId(String id) {
		return mapper.checkId(id);
	}


	@Override
	public Member findId(String name, String phoneNum) {
		return mapper.findId(name, phoneNum);
	}


	@Override
	public int signUp(Member member) {
		String encPw = bcrypt.encode(member.getMemberPw());
		member.setMemberPw(encPw);
		
		return mapper.signUp(member);
	}


	@Override
	public void modifyTel(Sms sms) {
		mapper.modifyTel(sms);
	}


	@Override
	public int findMember(Member member) {
		Member i =mapper.checkMember(member);
		String encPw = randomUtil.randomCode();
		System.out.println(encPw);
		encPw = bcrypt.encode(encPw);
		if(i!=null) {
			member.setMemberPw(encPw);
			mapper.findMember(member);
			smsUtil.sendSmsToFindId(i);
		}
		return 1;
	}






	

}
