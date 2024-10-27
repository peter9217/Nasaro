package nasaro.common.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import nasaro.member.model.dto.Member;
import nasaro.signUp.model.dto.Sms;
import nasaro.signUp.model.service.SignUpService;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Component
@PropertySource("classpath:/db.properties")
public class SmsUtil {

    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecretKey;
    

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RandomUtil randomUtil;
    
    private DefaultMessageService messageService;

    @PostConstruct
    public void init() {
        messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }
    // 단일 메시지 발송 예제
    public String sendOne(String phoneNum) {
//    	public String sendOne(String phoneNum) {
        Message message = new Message();
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        //인증코드 유효기간 5분 설정
        String verificationCode = randomUtil.randomCode();
        redisUtil.deleteData(phoneNum);
        redisUtil.setDataExpire(phoneNum, verificationCode, 60 * 5L);
        message.setFrom("01072610107");
        message.setTo(phoneNum);
        message.setText("[Lazarus] 아래의 인증번호를 입력해주세요\n" + verificationCode);
        System.out.println(apiKey);
        System.out.println(apiSecretKey);
        System.out.println(message);
        try {
        	  // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
        	System.out.println("성공");
        	  messageService.send(message);
        	} catch (NurigoMessageNotReceivedException exception) {
        	  // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
        	  System.out.println(exception.getFailedMessageList());
        	  System.out.println(exception.getMessage());
        	} catch (Exception exception) {
        	  System.out.println(exception.getMessage());
        	}
//        return response;
        return "response";
    }
    
    public String sendSmsToFindId(Member member) {
    	Message message = new Message();
		 message.setFrom("01072610107");
	     message.setTo(member.getMemberTel());
	     message.setText("[Lazarus] 멤버 ID\n" +member.getMemberId()+ "\n변경된 비밀번호 입니다.\n" + member.getMemberPw());
	     try {
	    	  // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
	    	 System.out.println("성공");
	    	  messageService.send(message);
	    	} catch (NurigoMessageNotReceivedException exception) {
	    	  // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
	    	  System.out.println(exception.getFailedMessageList());
	    	  System.out.println(exception.getMessage());
	    	} catch (Exception exception) {
	    	  System.out.println(exception.getMessage());
	    	}
        return "response";
    }

	public String checkCode(Sms sms) {
		String i = redisUtil.getData(sms.getMemberTel());
		System.out.println(i);
		System.out.println(sms.getSmsCode());
		if (i!=null) {
			if (i.equals(sms.getSmsCode())) {return "코드가 일치합니다.";
			}else {
				redisUtil.deleteData(sms.getMemberTel());
				return "코드가 일치하지 않습니다.";
				
			}
		}
		return "핸드폰의 번호가 정확하지 않습니다.";
	}
    
}
