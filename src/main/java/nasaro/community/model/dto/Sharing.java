package nasaro.community.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sharing {
	private long sharingNo;
	private long memberNo;
	private String memberName;
	private String sharingTitle;
	private String sharingContent;
	private String sharingDate;

}
