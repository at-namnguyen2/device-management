package device.management.demo.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import device.management.demo.service.UploadFileService;
import device.management.demo.util.fileConst;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Override
	public String uploadfile(MultipartFile file, long id) {
		String pathfile = new File("src/main/resources/static").getAbsolutePath();
		System.out.println("vao upload" + pathfile);
//		String filename = file.getOriginalFilename();
//		String newFile = filename.substring(filename.length()-4, filename.length());
		String pathToDatabase;
		if (file.getContentType().equals(fileConst.imagejpeg)) {
			pathToDatabase = "/images/" + id + fileConst.jpg;
		} else {
			pathToDatabase = "/images/" + id + fileConst.png;
		}
		File file_save = new File(pathfile + pathToDatabase);
		try {
			file.transferTo(file_save);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathToDatabase;
	}

}
