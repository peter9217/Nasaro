package nasaro.pray.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Family {
	private long familyNo;
	private long memberNo;
	private String memberName;
	private String familyTitle;
	private String familyContent;
	private String familyDate;

}
