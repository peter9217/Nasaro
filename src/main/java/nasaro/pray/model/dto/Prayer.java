package nasaro.pray.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Prayer {

	private long memberNo;
	private long prayerNo;
	private String prayerContent;
	private String prayerImg;

}
