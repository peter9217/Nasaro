package nasaro.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.multipart.MultipartFile;

import nasaro.intro.model.dto.Intro;
import nasaro.member.model.dto.Member;
import nasaro.pray.model.dto.Prayer;
import nasaro.admin.model.dto.Slide;
import nasaro.intro.model.dto.Consultation;
import nasaro.intro.model.dto.Guide;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface AdminMapper {

	Intro intro();

	int modifyIntro(Intro intro);

	int insertImage(Intro img);
	
	
	
	Guide guide();

	int modifyGuide(Guide guide);

	int insertGuideImage(Guide img);

	
	
	Consultation consultation();

	int modifyConsultation(Consultation consultation);

	int insertConsultationImage(Consultation img);
	
	
	
	Prayer prayer();

	int modifyPrayer(Prayer prayer);

	int insertPrayerImage(Prayer img);

	
	Member member();

	int memberListCount();

	List<Member> memberList(RowBounds rowBounds);

	int memberListCountSearch(Map<String, Object> paramMap);

	List<Member> searchMemberList(Map<String, Object> paramMap, RowBounds rowBounds);

	int modifyMember(Member member);

	List<Slide> slideList();

	int modifySlide(Slide img);

	int modifyBoard(@Param("board") String board, 
            @Param("boardNo") int boardNo, 
            @Param("boardChoice") String boardChoice);









	



}

