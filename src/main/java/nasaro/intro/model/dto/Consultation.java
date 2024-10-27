package nasaro.intro.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Consultation {

	private long memberNo;
	private long consultationNo;
	private String consultationContent;
	private String consultationImg;

}
