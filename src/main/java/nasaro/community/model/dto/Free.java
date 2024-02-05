package nasaro.community.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Free {
	private long freeNo;
	private long memberNo;
	private String freeTitle;
	private String freeContent;
	private String freeDate;

}
