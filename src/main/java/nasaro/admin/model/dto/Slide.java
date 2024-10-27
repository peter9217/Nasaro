package nasaro.admin.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Slide {

	private int slideNo;
	private long memberNo;
	private int slideOrder;
	private String slideImgPath;
	private String slideText;
	private int slideTextTop;
	private int slideTextLeft;
	private String slideTextColor;
}
