package nasaro.gallery.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Gallery {

	private long galleryNo;
	private long memberNo;
	private String memberId;
	private String galleryContent;
	private String galleryDate;
	private String galleryCategory;
	private List<GalleryImg> galleryImg;

}
