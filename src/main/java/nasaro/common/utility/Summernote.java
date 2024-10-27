package nasaro.common.utility;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Summernote {

	@Autowired
	private S3Uploader uploader;
	
	@Value("${board.summernote.webpath}")
	private String webPath;
	
	@Value("${board.summernote.location}")
	private String filePath;
	
	@Autowired
	private final WebApplicationContext context; 
    
	// @RequestParam은 자바스크립트에서 설정한 이름과 반드시 같아야합니다.
    @PostMapping(value = "/admin/uploadSummernoteImageFile" , produces = "application/json; charset=utf8")
	public String imageUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		
    	Map<String, String> map = new HashMap<String, String>();
		// 2) 웹 접근 경로(webPath) , 서버 저장 경로 (serverPath)
		try {
			// 서버에 저장할 경로
			String originalFileName=file.getOriginalFilename();
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
			
			// 위에서 설정한 서버 경로에 이미지 저장
			uploader.upload(file, webPath+savedFileName);
		
			System.out.println("************************ 업로드 컨트롤러 실행 ************************");
			System.out.println(filePath+savedFileName);
			String imageUrl = filePath+savedFileName;
			// Ajax에서 업로드 된 파일의 이름을 응답 받을 수 있도록 해줍니다.
			return imageUrl;
		} catch (Exception e) {
			return "false";
		}
	}

}