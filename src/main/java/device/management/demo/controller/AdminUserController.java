package device.management.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	
	@GetMapping
	public String adminHome() {
		return "admin-home";
	}
	
	@GetMapping("notify")
	public String showNotify() {
		return "notify";
	}
	
	@GetMapping("device")
	public String showDevice() {
		return "device";
	}
	
	@GetMapping("report")
	public String showreport() {
		return "admin-home";
	}
	
	@GetMapping("allocationreturn")
	public String showAllocationReturn() {
		return "allocation-return";
	}
	
	@GetMapping("left-container")
	public String showLeftBar() {
		return "/common/left-container";
	}
	
	@GetMapping("topbar")
	public String showTopBar() {
		return "/common/topbar";
	}
	
	@GetMapping("adduser")
	public String showUserProfile() {
		return "add-user";
	}
	@GetMapping("userall")
	public String showUserAll() {
		return "user_all";
	}
}
