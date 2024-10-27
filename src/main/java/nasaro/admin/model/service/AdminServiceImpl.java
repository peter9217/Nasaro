package nasaro.admin.model.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nasaro.admin.model.dto.Slide;
import nasaro.admin.model.mapper.AdminMapper;
import nasaro.common.utility.Pagination;
import nasaro.common.utility.S3Uploader;
import nasaro.gallery.model.dto.GalleryImg;
import nasaro.intro.model.dto.Intro;
import nasaro.member.model.dto.Member;
import nasaro.pray.model.dto.Nations;
import nasaro.pray.model.dto.Prayer;
import nasaro.intro.model.dto.Consultation;
import nasaro.intro.model.dto.Guide;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private S3Uploader uploader;
	
	@Value("${board.intro.webpath}")
	private String webPath;
	
	@Value("${board.intro.location}")
	private String filePath;
	
	@Autowired
	private AdminMapper mapper;

	@Override
	public Intro intro() {
		return mapper.intro();
	}

	@Override
	public int modifyIntro(Intro intro) {
		return mapper.modifyIntro(intro);
	}
	
	@Override
	public int modifyImgIntro(Intro intro, List<MultipartFile> images) throws FileUploadException {
		int size = 0;
		intro.setIntroNo(1);
		String fileName = "";
		List<Intro> insertList = new ArrayList<Intro>();
		int result = 1;
		for(int i = 0; i < images.size(); i++) {
			if(images.get(i).getSize()>0) {
				Intro img = new Intro();
				fileName = images.get(i).getOriginalFilename();
				img.setIntroImg(filePath +intro.getIntroNo()+fileName);
				img.setIntroNo(intro.getIntroNo());
//				img.setIntroImgOrder(i);
				System.out.println(img);
				insertList.add(img);
				result=mapper.insertImage(img);
				size+=1;
			}
		}
		System.out.println(images.size());
		System.out.println(insertList.size());
		if(!insertList.isEmpty()) {
			if(insertList.size()==size) {
				for(int i=0; i<insertList.size(); i++) {
//					int index = insertList.get(i).getIntroImgOrder();
					int index = 0;
					fileName = intro.getIntroNo()+images.get(index).getOriginalFilename();
					try {
						uploader.upload(images.get(index), webPath+fileName);
					} catch (IOException e) {
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
	
	
	

	@Override
	public Guide guide() {
		return mapper.guide();
	}

	@Override
	public int modifyGuide(Guide guide) {
		return mapper.modifyGuide(guide);
	}

	@Override
	public int modifyImgGuide(Guide guide, List<MultipartFile> images) throws FileUploadException {
		int size = 0;
		guide.setGuideNo(1);
		String fileName = "";
		List<Guide> insertList = new ArrayList<Guide>();
		int result = 1;
		for(int i = 0; i < images.size(); i++) {
			if(images.get(i).getSize()>0) {
				Guide img = new Guide();
				fileName = images.get(i).getOriginalFilename();
				img.setGuideImg(filePath +guide.getGuideNo()+fileName);
				img.setGuideNo(guide.getGuideNo());
//				img.setIntroImgOrder(i);
				System.out.println(img);
				insertList.add(img);
				result=mapper.insertGuideImage(img);
				size+=1;
			}
		}
		System.out.println(images.size());
		System.out.println(insertList.size());
		if(!insertList.isEmpty()) {
			if(insertList.size()==size) {
				for(int i=0; i<insertList.size(); i++) {
//					int index = insertList.get(i).getIntroImgOrder();
					int index = 0;
					fileName = guide.getGuideNo()+images.get(index).getOriginalFilename();
					try {
						uploader.upload(images.get(index), webPath+fileName);
					} catch (IOException e) {
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

	
	
	
	
	
	@Override
	public Consultation consultation() {
		return mapper.consultation();
	}

	@Override
	public int modifyConsultation(Consultation consultation) {
		return mapper.modifyConsultation(consultation);
	}

	@Override
	public int modifyImgConsultation(Consultation consultation, List<MultipartFile> images) throws FileUploadException {
		int size = 0;
		consultation.setConsultationNo(1);
		String fileName = "";
		List<Consultation> insertList = new ArrayList<Consultation>();
		int result = 1;
		for(int i = 0; i < images.size(); i++) {
			if(images.get(i).getSize()>0) {
				Consultation img = new Consultation();
				fileName = images.get(i).getOriginalFilename();
				img.setConsultationImg(filePath +consultation.getConsultationNo()+fileName);
				img.setConsultationNo(consultation.getConsultationNo());
//				img.setIntroImgOrder(i);
				System.out.println(img);
				insertList.add(img);
				result=mapper.insertConsultationImage(img);
				size+=1;
			}
		}
		System.out.println(images.size());
		System.out.println(insertList.size());
		if(!insertList.isEmpty()) {
			if(insertList.size()==size) {
				for(int i=0; i<insertList.size(); i++) {
//					int index = insertList.get(i).getIntroImgOrder();
					int index = 0;
					fileName = consultation.getConsultationNo()+images.get(index).getOriginalFilename();
					try {
						uploader.upload(images.get(index), webPath+fileName);
					} catch (IOException e) {
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

	
	/**
	 *
	 */
	@Override
	public Prayer prayer() {
		return mapper.prayer();
	}

	@Override
	public int modifyPrayer(Prayer prayer) {
		return mapper.modifyPrayer(prayer);
	}

	@Override
	public int modifyImgPrayer(Prayer prayer, List<MultipartFile> images) throws FileUploadException {
		int size = 0;
		prayer.setPrayerNo(1);
		String fileName = "";
		List<Prayer> insertList = new ArrayList<Prayer>();
		int result = 1;
		for(int i = 0; i < images.size(); i++) {
			if(images.get(i).getSize()>0) {
				Prayer img = new Prayer();
				fileName = images.get(i).getOriginalFilename();
				img.setPrayerImg(filePath +prayer.getPrayerNo()+fileName);
				img.setPrayerNo(prayer.getPrayerNo());
//				img.setIntroImgOrder(i);
				System.out.println(img);
				insertList.add(img);
				result=mapper.insertPrayerImage(img);
				size+=1;
			}
		}
		System.out.println(images.size());
		System.out.println(insertList.size());
		if(!insertList.isEmpty()) {
			if(insertList.size()==size) {
				for(int i=0; i<insertList.size(); i++) {
//					int index = insertList.get(i).getIntroImgOrder();
					int index = 0;
					fileName = prayer.getPrayerNo()+images.get(index).getOriginalFilename();
					try {
						uploader.upload(images.get(index), webPath+fileName);
					} catch (IOException e) {
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

	@Override
	public Member member() {
		return mapper.member();
	}

	@Override
	public Map<String, Object> memberList(int cp) {
		int listCount = mapper.memberListCount();
		
		Pagination pagination = new Pagination(listCount, cp, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Member> memberList = mapper.memberList(rowBounds);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pagination", pagination);
		resultMap.put("memberList", memberList);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> memberList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.memberListCountSearch(paramMap); // 오버로딩
		// 2. 1번 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp, 10);
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() - 1 ) * pagination.getLimit();
			
		// 2) Rowbounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Member> memberList = mapper.searchMemberList(paramMap, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		return map;
	}

	@Override
	public int modifyMember(Member member) {
		return mapper.modifyMember(member);
	}

	@Override
	public Map<String, Object> slideList() {
		List<Slide> slideList = mapper.slideList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("slideList", slideList);
		return map;
	}

	@Override
	public int modifySlide(MultipartFile images, Slide slide) throws FileUploadException {
		int no = 1;
		int result = 0;
		String fileName = "";
		fileName = images.getOriginalFilename();
		if (images.getSize()>0) {
			try {
				slide.setSlideImgPath(filePath+slide.getSlideNo()+fileName);
				uploader.upload(images, webPath+slide.getSlideNo()+fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result=mapper.modifySlide(slide);
		
		return 0;
	}


	@Override
	public int modifyBoard(String board, int boardNo, String boardChoice) {
		return mapper.modifyBoard(board, boardNo, boardChoice);
	}



	

}
