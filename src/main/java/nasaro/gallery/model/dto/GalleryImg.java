package nasaro.gallery.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GalleryImg {

	private long galleryNo;
	private long galleryImgNo;
	private String galleryPath;
	private int galleryImgOrder;

}
