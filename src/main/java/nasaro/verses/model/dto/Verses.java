package nasaro.verses.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Verses {
	private long versesNo;
	private long memberNo;
	private String memberName;
	private String versesTitle;
	private String versesContent;
	private String versesDate;
	public void setMemberNo(Object memberNo2) {
		// TODO Auto-generated method stub
		
	}

}
