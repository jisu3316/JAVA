package board.md.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import board.md.fileset.Path;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FileUploadServiceimpl implements FileUploadService {

	@Override
	public String saveStore(MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");	
		String ofheader = ofname.substring(0, idx);		
		String ext = ofname.substring(idx);		
		long ms = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);		
		sb.append(ext);
		String saveFileName = sb.toString();
		
		long fsize = file.getSize();
		log.info("#ofname: " + ofname + ", saveFileName: "+saveFileName + ", fsize: "+fsize);
		
		boolean flag = writeFile(file, saveFileName);
		if(flag) {
			log.info("#업로드 성공");
		}else {
			log.info("#업로드 실패");
		}
		
		return Path.FILE_STORE + saveFileName;
	}
	private boolean writeFile(MultipartFile file,String saveFileName) {
		File dir = new File(Path.FILE_STORE);
		if(!dir.exists()) dir.mkdirs();
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE+saveFileName);
			fos.write(data);
			fos.flush();
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
	}
}
