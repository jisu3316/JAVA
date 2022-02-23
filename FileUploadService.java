package board.md.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String saveStore(MultipartFile file);//파일 업로딩한 경로 리턴해주려도 String으로 만듬
}
