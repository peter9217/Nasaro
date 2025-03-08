package nasaro.admin.model.service;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import nasaro.intro.model.dto.Intro;
import nasaro.member.model.dto.Member;
import nasaro.pray.model.dto.Prayer;
import nasaro.admin.model.dto.Slide;
import nasaro.intro.model.dto.Consultation;
import nasaro.intro.model.dto.Guide;

public interface AdminService {

	Intro intro();

	int modifyIntro(Intro intro);

	int modifyImgIntro(Intro intro, List<MultipartFile> images) throws FileUploadException;
	
	
	
	Guide guide();

	int modifyGuide(Guide guide);

	int modifyImgGuide(Guide guide, List<MultipartFile> images) throws FileUploadException;
	
	
	
	Consultation consultation();

	int modifyConsultation(Consultation consultation);

	int modifyImgConsultation(Consultation consultation, List<MultipartFile> images) throws FileUploadException;
	
	
	
	Prayer prayer();

	int modifyPrayer(Prayer prayer);

	int modifyImgPrayer(Prayer prayer, List<MultipartFile> images) throws FileUploadException;

	
	
	Member member();

	Map<String, Object> memberList(int cp);

	Map<String, Object> memberList(Map<String, Object> paramMap, int cp);

	int modifyMember(Member member);

	
	
	Map<String, Object> slideList();

	int modifySlide(MultipartFile images, Slide slide) throws FileUploadException;

	int modifyBoard(String board, int boardNo, String boardChoice);



	

	

}
