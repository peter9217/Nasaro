package nasaro.gallery.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nasaro.common.utility.Pagination;
import nasaro.common.utility.S3Uploader;
import nasaro.community.model.dto.Free;
import nasaro.gallery.model.dto.Gallery;
import nasaro.gallery.model.dto.GalleryImg;
import nasaro.gallery.model.mapper.GalleryMapper;
import nasaro.member.model.dto.Member;
import nasaro.member.model.mapper.MemberMapper;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.pray.model.mapper.FamilyMapper;
import nasaro.pray.model.mapper.NationsMapper;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;
import nasaro.verses.model.mapper.VersesMapper;
import nasaro.verses.model.mapper.ColumnMapper;
import nasaro.verses.model.mapper.ReferenceMapper;

@Service
@PropertySource("classpath:/db.properties")
public class GalleryServiceImpl implements GalleryService {

	@Autowired
	private S3Uploader uploader;
	
	@Value("${board.gallery.webpath}")
	private String webPath;
	
	@Value("${board.gallery.location}")
	private String filePath;
	
	@Autowired
	private GalleryMapper mapper;

	/** 자유 리스트
	 *
	 */
	@Override
	public Map<String, Object> galleryList(Map<String, Object> paramMap,int cp) {
		int listCount = mapper.galleryListCount(paramMap);
		System.out.println(paramMap);
		System.out.println(listCount);
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Gallery> galleryList = mapper.galleryList(rowBounds, paramMap);
		
		for(Gallery gallery : galleryList) {
			if (gallery.getGalleryNo()!=0) {
				List<GalleryImg> imgList = new ArrayList<>();
				imgList=mapper.galleryImg(gallery.getGalleryNo());
				gallery.setGalleryImg(imgList);
			}// 상품 설정
		}
		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("galleryList", galleryList);
		
		return resultMap;
	}
	
	/** 자유 검색어를 포함한 리스트
	 *
	 */
//	@Override
//	public Map<String, Object> freeList(Map<String, Object> paramMap, int cp) {
//		int listCount = mapper.freeListCountSearch(paramMap); // 오버로딩
//		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
//		// -> 내부 필드가 모두 계산되어 초기화됨
//		Pagination pagination = new Pagination(listCount, cp, 10);
//		// 1) offset 계산
//		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
//			
//		// 2) Rowbounds 객체 생성
//		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
//		
//		List<Free> freeList = mapper.searchFreeList(paramMap, rowBounds);
//		
//		// 4. pagination, boardList를 Map에 담아서 반환
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("pagination", pagination);
//		map.put("freeList", freeList);
//		return map;
//	}

	
	@Override
	public int insertGallery(Gallery gallery, List<MultipartFile> images) throws FileUploadException {
		int size = 0;
		String fileName = "";
		int result = mapper.insertGallery(gallery);
		List<GalleryImg> insertList = new ArrayList<GalleryImg>();
		for(int i = 0; i < images.size(); i++) {
			if(images.get(i).getSize()>0) {
				GalleryImg img = new GalleryImg();
				fileName = images.get(i).getOriginalFilename();
				img.setGalleryPath(filePath +gallery.getGalleryNo()+fileName);
				img.setGalleryNo(gallery.getGalleryNo());
				img.setGalleryImgOrder(i);
				System.out.println(img);
				insertList.add(img);
				result=mapper.insertImage(img);
				size+=1;
			}
		}
		if(!insertList.isEmpty()) {
			if(insertList.size()==size) {
				for(int i=0; i<insertList.size(); i++) {
					int index = insertList.get(i).getGalleryImgOrder();
					fileName = gallery.getGalleryNo()+images.get(index).getOriginalFilename();
					try {
						uploader.upload(images.get(index), webPath+fileName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					images.get(index).transferTo(new File(filePath+ fileName));
				}
			} else {
				throw new FileUploadException();
			}
		}
		return result;
		
	}

	

}
