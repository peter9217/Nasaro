package nasaro.community.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notice {
	private long noticeNo;
	private long memberNo;
	private String memberNickname;
	private String memberName;
	private String noticeTitle;
	private String noticeContent;
	private String noticeDate;

}
