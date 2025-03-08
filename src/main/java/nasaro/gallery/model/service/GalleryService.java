package nasaro.gallery.model.service;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import nasaro.gallery.model.dto.Gallery;

public interface GalleryService {


	int insertGallery(Gallery gallery, List<MultipartFile> images) throws FileUploadException;

	Map<String, Object> galleryList(Map<String, Object> paramMap, int cp);

	
}
