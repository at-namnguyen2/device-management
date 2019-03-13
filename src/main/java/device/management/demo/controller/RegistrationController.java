package device.management.demo.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import device.management.demo.entity.Role;
import device.management.demo.entity.TokenVerifition;
import device.management.demo.entity.User;
import device.management.demo.entity.UserRole;
import device.management.demo.entity.dto.UserDTO;
//import device.management.demo.entity.dto.UserDTO1;
import device.management.demo.entity.response.UserDTOResponse;
import device.management.demo.repository.RoleRepository;
import device.management.demo.repository.UserRepsository;
import device.management.demo.repository.UserRoleRepository;
import device.management.demo.service.EmployeeService;
import device.management.demo.service.MailService;
import device.management.demo.service.RoleService;
import device.management.demo.service.TokenVerificationService;
import device.management.demo.service.UserRoleService;
import device.management.demo.service.UserService;
import device.management.demo.util.VerificationUtil;

//author: tu viet van
@Controller
public class RegistrationController {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenVerificationService tokenVerificationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificationUtil veritificationUtil;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserRepsository userRepsoitory;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	@GetMapping(path="/register")
	public String showRegisterPage() {	
		
		return "register";
	}
	
	@GetMapping(path = "/registerAccount") 
	public String show() {
		return "add-user";
	}
	@GetMapping(path ="/user")
	public String showUser() {
		return "user-home";
	}
//	@GetMapping(path = "/admin")
//	public String showAdmin() {
//		return "forget-password";
//	}
	
	@GetMapping(path = "/admin1")
	public String showAdminPage() {
		return "user_all";
	}
	@GetMapping(path = "/trang-chung")
	public String showTrangChung() {
		return "trang-chung";
	}
	@GetMapping(path = "/showInfoAdmin")
	public String showInfoAdmin() {
		return "showUser";
	}
	
	@PostMapping(path="/registerAccount")	
	public  ResponseEntity<Object> registNewAccount(@Valid @RequestBody UserDTO userModel,BindingResult result) {
		  UserDTOResponse userDTOResponse = new UserDTOResponse();
		  System.out.println(userModel.getEmail());
		  System.out.println(userModel.getEmail());
		  System.out.println("BirthDay: " + userModel.getBirthDay());
		  
		  
	      if(result.hasErrors()){          
	    	  System.out.println(result.getAllErrors().toString());
	          Map<String, String> errors = result.getFieldErrors().stream()
	                .collect(
	                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
	                  );	        
//	          if(result.getAllErrors().toString().indexOf("PasswordMatches")!= -1) {
//	        	  errors.put("matchingPassword", "Password is not matched");
//	          }c
	          userDTOResponse.setValidated(false);
	          userDTOResponse.setErrorMessages(errors); 
	          return new ResponseEntity<Object>(userDTOResponse, HttpStatus.BAD_REQUEST);
	      }
      
		if(userService.checkDuplicateEmail(userModel.getEmail())) {
			return new ResponseEntity<Object> ("Email is existed", HttpStatus.CONFLICT);
		}else {
			String registCode = veritificationUtil.generateVerificationCode(userModel.getEmail()+userModel.getPassword());
			System.out.println("//////////////"+ registCode);
			Date expireDate = veritificationUtil.calculatorExpireTime();
			String passwordEncode = passwordEncoder.encode(userModel.getPassword());
			System.out.println(passwordEncode);
			userModel.setPassword(passwordEncode);
			
			//Thuoc ve user
			String email = userModel.getEmail();
			User user = new User(email);
			String description = userModel.getDescription();
			String password = userModel.getPassword();
			
			//Thuoc ve bang employee
			
			String address = userModel.getAddress();
			Date birthDate = userModel.getBirthDay();
			String employeeName = userModel.getEmployeeName();
			Boolean gender = userModel.getGender();
			String phone = userModel.getPhone();
			String team = userModel.getTeam();
//			Long user_id = user.getId();
			
			try {
				mailService.sendMail("active account","/activeAccount",userModel.getEmail(),registCode,expireDate);
				//User user = userService.addUser(userModel);
				
				//Thuoc ve bang tokenverifitionemployeeName
				//chua expireDate o tren
				//chua registCode o tren//day la ma token
				//chua user_id o tren...
				userService.addUserFunction(description, email, password);
				Optional<User> user1 = userRepsoitory.findByEmail(email);
				
				Long user_id = user1.get().getId();
				employeeService.addEmployeeFunction(address, birthDate, employeeName, gender, phone, team, user_id);
				////////////
				System.out.println("test token");
				System.out.println(expireDate);
				System.out.println(registCode);
				System.out.println(user_id);
				tokenVerificationService.addTokenFunction(expireDate, registCode, user_id);
				
				//Optional<Role> role1 = roleRepository.findByUserRolesUserEmail(email);
				Role role1 = roleService.findByRoleName("USER");
				
				//System.out.println(role1.get().toString());
				//Role role = new Role("Quyen User", "USER");
				
				
				
				
				//Long role_id = role1.get().getId();
				
				
				
				//employeeService.addEmployeeFunction(address, birthDate, employeeName, gender, phone, team, user_id);
				
				//roleService.addRole();
				//roleRepository.save(role);
			    UserRole userRole = new UserRole(user1.get(), role1);
				userRoleRepository.save(userRole);
				
				
//				TokenVerifition tokenVerifition = new TokenVerifition(user, registCode, expireDate, 0);
//				tokenVerificationService.addToken(tokenVerifition);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<>("Created user successfully", HttpStatus.OK);
		}
	}

	@GetMapping(path="activeAccount")
	public String activeAccount(HttpServletRequest request, @RequestParam("token")String registCode,Model model) throws MessagingException {

		TokenVerifition tokenVerification = tokenVerificationService.findTokenByTokenCode(registCode);
		if(tokenVerification == null) {
			//return new ResponseEntity<String>("Token is not true", HttpStatus.NOT_FOUND);
			return "login";
		}
		User objUsers= tokenVerification.getUser();		
		
		if(objUsers != null) {
			
			Date nowDay = new Date();
			if(tokenVerification.getExpireTime().getTime() < nowDay.getTime()) {
				tokenVerification.setExpireTime(veritificationUtil.calculatorExpireTime());
				tokenVerification.setTokenCode(veritificationUtil.generateVerificationCode(objUsers.getEmail() + objUsers.getPassword()));
				tokenVerificationService.editToken(tokenVerification);
				mailService.sendMail("Active Account","/activeAccount",objUsers.getEmail(),tokenVerification.getTokenCode(),tokenVerification.getExpireTime());
				//return new ResponseEntity<>("We sent you a new token", HttpStatus.OK);
			}else {
				boolean check = userService.activeUser(objUsers.getId());
				if(check == true) {
					tokenVerificationService.deleteTokenById(tokenVerification.getId());
					//return new ResponseEntity<>("Active user successfully", HttpStatus.OK);
					return "login-page";
				}else {
					//return new ResponseEntity<>("Active user fail", HttpStatus.BAD_REQUEST);
					return "checkmail";
				}
			}			
		}	
	    //return new ResponseEntity<>("Active user fail", HttpStatus.BAD_REQUEST);
		return "checkmail";
	}
}	
