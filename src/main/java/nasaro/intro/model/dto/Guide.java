package nasaro.intro.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Guide {

	private long memberNo;
	private long guideNo;
	private String guideContent;
	private String guideImg;

}
