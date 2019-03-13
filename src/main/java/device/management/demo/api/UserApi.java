package device.management.demo.api;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import device.management.demo.entity.dto.UserDTO;
import device.management.demo.entity.response.PhoneSA;
import device.management.demo.entity.response.UserResponse;
import device.management.demo.service.EmployeeService;
import device.management.demo.service.RequestService;
import device.management.demo.service.UploadFileService;
import device.management.demo.service.UserService;

@RestController
public class UserApi {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	UserService userService;		
	
	@Autowired
	RequestService requestService;
	
	@Autowired 
	UploadFileService uploadFileService;
	/**
   	* @summary return profile of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return user
   	**/
	@GetMapping(path = "/userapi/myprofile")
	public ResponseEntity<Object> ViewCurrentUser(Principal p) {
		String email = p.getName();
	UserResponse user = userService.findUserByEmail(email);
	return new ResponseEntity<>(user, HttpStatus.OK);		
}
	
	/**
	* @summary edit profile of user
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param userdto
	* @return String message
   	**/
	@PostMapping(path = "/userapi/editmyprofile", consumes = "multipart/form-data")
	public ResponseEntity<Object> EditCurrentUser(@RequestPart("userdto") String userdto, @RequestPart("file") MultipartFile file, Principal p) {
		System.out.println("file:"+file);
		String email = p.getName();
		ObjectMapper mapper = new ObjectMapper();
		UserDTO useredit = new UserDTO();
		try {
			useredit = mapper.readValue(userdto, UserDTO.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pathupload = uploadFileService.uploadfile(file, 1);
		if(!pathupload.isEmpty()) {
			useredit.setAvatar(pathupload);
		}
		
		System.out.println("data:"+useredit);
//		if (userdto == null) {
//			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);			
//		}
		userService.editProfileUser(email, userdto);		
		return new ResponseEntity<>("Your update request is pending", HttpStatus.OK);
	}
	
	/**
	* @summary edit profile of user
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param userdto
	* @return String message
   	**/
	@PostMapping(path = "/userapi/editmyprofile2", consumes = "multipart/form-data")
	public ResponseEntity<Object> EditCurrentUserNotAvatar(@RequestPart("userdto") String userdto, Principal p) {
		String email = p.getName();
		ObjectMapper mapper = new ObjectMapper();
		UserDTO useredit = new UserDTO();
		try {
			useredit = mapper.readValue(userdto, UserDTO.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("data:"+useredit);
		userService.editProfileUser(email, userdto);		
		return new ResponseEntity<>("Your update request is pending", HttpStatus.OK);
	}
	
	
	/**
	* @summary list SA contact: phone via rolename Admin
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param 
	* @return listPhone
   	**/
	@GetMapping(path = "userapi/sacontact")
	public ResponseEntity<Object> ShowSaContact() {
		System.out.println("test contact");
		List<PhoneSA> listPhone = userService.getSAContact();		
		return new ResponseEntity<>(listPhone, HttpStatus.OK);
	}
}
	
