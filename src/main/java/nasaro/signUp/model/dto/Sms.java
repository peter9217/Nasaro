package nasaro.signUp.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sms {
	private long freeNo;
	private long memberNo;
	private String memberTel;
	private String smsCode;
	private String freeTitle;
	private String freeContent;
	private String freeDate;

}
