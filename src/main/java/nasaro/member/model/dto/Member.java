package nasaro.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {

	private long memberNo;
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberRank;
	private String memberEmail;
	public Object getMemberNo() {
		// TODO Auto-generated method stub
		return null;
	}

}
