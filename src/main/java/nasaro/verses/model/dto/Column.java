package nasaro.verses.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Column {
	private long columnNo;
	private long memberNo;
	private String columnTitle;
	private String columnContent;
	private String columnDate;

}