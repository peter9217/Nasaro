package nasaro.gallery.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import nasaro.gallery.model.dto.Gallery;
import nasaro.gallery.model.dto.GalleryImg;

@Mapper
public interface GalleryMapper {

	int galleryListCount(Map<String, Object> paramMap);
	
	List<Gallery> galleryList(RowBounds rowBounds, Map<String, Object> paramMap);
	
	int insertGallery(Gallery GalleryImg);

	int insertImage(GalleryImg img);

	List<GalleryImg> galleryImg(long galleryNo);



}
