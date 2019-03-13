package device.management.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Controller
//@RequestMapping("/home")
public class UserController {
	
//	@GetMapping(path="/myprofile")
//	public String MyProfile() {
//		return "page-user";
//	}
	
	@GetMapping(path = {"/", "/home"})
	public String showUserProfile() {
		return "user-home";
	}
}
