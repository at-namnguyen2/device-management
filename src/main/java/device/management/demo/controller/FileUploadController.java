package device.management.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

import javax.naming.Context;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	@Autowired
	ServletContext context;
	public static String uploadDirectoty = System.getProperty("user.dir")+"/src/main/resources/uploads";
	
	@RequestMapping("/uploadfile")
	public String UploadPage(Model model) {
		return "uploadview";
	}
	
	@RequestMapping("/upload")
	public String upload(Model model,@RequestParam("files") MultipartFile file) {
		long id =  1;
		System.out.println("vao upload");
//		String pathfile = new File("src/main/resources/static/images").getAbsolutePath() + "/";
//		System.out.println("vao upload"+pathfile);
//		File file_save = new File(pathfile+files[0].getOriginalFilename());
		String pathfile = new File("src/main/resources/static").getAbsolutePath();
		System.out.println("vao upload"+pathfile);
		String filename = file.getOriginalFilename();
		String newFile = filename.substring(filename.length()-4, filename.length());
		String pathToDatabase = "/images/"+id+newFile;
		File file_save = new File(pathfile+pathToDatabase);
		try {
			file.transferTo(file_save);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "uploadstatusview";
	}
}
