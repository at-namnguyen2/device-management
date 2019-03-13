package device.management.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
	String uploadfile(MultipartFile file, long id);
}
