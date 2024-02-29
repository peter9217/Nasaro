package nasaro.common.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수가 있으면 생성자 항목에 포함시킴
//@Component
@Service
public class S3Uploader {

	@Autowired
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));
        return upload(uploadFile, dirName);
    }

    private String upload(File uploadFile, String dirName) {
    	String path = dirName.substring(1); // path 맨 앞에 있는 '/' 제거
        String uploadImageUrl = putS3(uploadFile, path);

        removeNewFile(uploadFile);  // 로컬에 생성된 File 삭제 (MultipartFile -> File 전환 하며 로컬에 파일 생성됨)

        return uploadImageUrl;      // 업로드된 파일의 S3 URL 주소 반환
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead)	// PublicRead 권한으로 업로드 됨
        );

        return amazonS3.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if(targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        }else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws  IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    /**
     * 해당 경로에 있는 파일 목록 반환
     * @param path
     */
    public List<String> getList(String path) {
    	
    	String newPath = path.substring(1); // path 처음에 있는 '/' 제거
    	ListObjectsRequest req = new ListObjectsRequest();
    	req.setBucketName(bucket);
    	req.setPrefix(newPath);
    	
    	ObjectListing objects = null;
    	List<String> filenameList = new ArrayList<>();
    	do {
    		objects = amazonS3.listObjects(req); //1000개 단위로 읽음
    		for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
    			filenameList.add(objectSummary.getKey());
    		}
    		req.setMarker(objects.getNextMarker());
    	} while (objects.isTruncated());
    	
    	return filenameList;
    }

    /**
     * DB에 없는 파일 서버에서 삭제
     * @param path 
     */
	public void remove(String path) {
		amazonS3.deleteObject(new DeleteObjectRequest(bucket, path));
	}
   
}