package nasaro.verses.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reference {
	private long referenceNo;
	private long memberNo;
	private String memberNickname;
	private String memberName;
	private String referenceTitle;
	private String referenceContent;
	private String referenceDate;

}
