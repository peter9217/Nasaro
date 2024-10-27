package nasaro.intro.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Intro {

	private long memberNo;
	private long introNo;
	private String introContent;
	private String introImg;

}
