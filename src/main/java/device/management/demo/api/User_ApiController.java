package device.management.demo.api;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.entity.Role;
import device.management.demo.entity.User;
import device.management.demo.entity.UserRole;
import device.management.demo.entity.response.UserDTOResponse;
import device.management.demo.entity.response.UserResponse2;
import device.management.demo.entity.response.UserResponse2Return;
import device.management.demo.repository.EmployeeRepository;
import device.management.demo.repository.RoleRepository;
import device.management.demo.repository.UserRepsository;
import device.management.demo.repository.UserRoleRepository;
import device.management.demo.service.RoleService;
import device.management.demo.service.UserService;

//author: tu viet van
@RestController
@RequestMapping("/api/users")
public class User_ApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepsository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleService roleService;
	
	//lay tat ca cac user.
	@GetMapping("/get-all-user")
	public ResponseEntity<Object> getAllUser() {
		
		List<UserResponse2> listUser = userService.showUserStateNonDel();
		 return new ResponseEntity<Object>(listUser, HttpStatus.OK);
		 
	}
	
	//lay user theo id.
	@GetMapping(path = "/getUser/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") long id) {
		 User user = userService.getUserById1(id);
		 if(user == null) {
			 return new ResponseEntity<Object>("Khong tim thay user co id nay", HttpStatus.NOT_FOUND);
		 }
		 UserResponse2 userRespone2 = userService.showInfoUser(user);
		 
		 return new ResponseEntity<Object>(userRespone2, HttpStatus.OK);
	}

	//xoa user theo id
	@DeleteMapping(path = "/deleteUser/{id}")
	public ResponseEntity<Object> removeUserById(@PathVariable("id") long id) {
		User user = userService.getUserById1(id);
		if(user == null) {
			return new ResponseEntity<Object>("Khong co User chua id nay.", HttpStatus.NOT_FOUND);
		}
		user.setNonDel(false);
		userRepository.save(user);
		return new ResponseEntity<Object>("Da xoa thanh cong...", HttpStatus.OK);
	}
		
	//lay tat ca thong tin cua Admin
	@GetMapping("/get-all-admin")
	public ResponseEntity<Object> getAllAdmin() {
		List<UserResponse2> listUser = userService.getUserAdmin();
		return new ResponseEntity<Object>(listUser, HttpStatus.OK);		
	}
	//lay tat ca thong tin user khong phai la admin
	@GetMapping("/get-all-userNotAdmin")
	public ResponseEntity<Object> getAllUserNotAdmin() {
		List<UserResponse2> list = userService.getAllUserNotAdmin();
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	//xoa tat ca cac user(xoa cung).
	@DeleteMapping(path = "/deleteAll") 
		public ResponseEntity<Object> deleteAllUser() {
			List<User> list = userRepository.findAll();
			userRepository.delAllUser();
			return new ResponseEntity<>("Da xoa tat ca user thanh cong", HttpStatus.OK);
		}
	
	//xoa tat ca cac user(xoa mem).
	@DeleteMapping(path = "/deleteAllSoft")
	public ResponseEntity<Object> deleteAllSoft() {
		Long id;
		List<User> list = userRepository.findAll();
		for(User user: list) {
			id = user.getId();
			userService.deleteUserSoftService(id);
			
		}
		return null;
	}
	
	
	//them user
	@PostMapping(path = "/addUser")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UserResponse2 userResponse1, BindingResult result, Principal principal) {
		
		System.out.println(userResponse1.getEmail()+"////////////////////////////////");
		  System.out.println(userResponse1.getEmail());
		
//		UserResponse2Return userResponse2Return = new UserResponse2Return();
//		  System.out.println(userResponse1.getEmail());
//		  System.out.println(userResponse1.getEmail());
//		  //System.out.println("BirthDay: " + userResponse1.getBirthDay());
//		  
//		  
//	      if(result.hasErrors()){          
//	    	  System.out.println(result.getAllErrors().toString());
//	          Map<String, String> errors = result.getFieldErrors().stream()
//	                .collect(
//	                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
//	                  );	        
////	          if(result.getAllErrors().toString().indexOf("PasswordMatches")!= -1) {
////	        	  errors.put("matchingPassword", "Password is not matched");
////	          }c
//	          userResponse2Return.setValidated(false);
//	          userResponse2Return.setErrorMessages(errors); 
//	          return new ResponseEntity<Object>(userResponse2Return, HttpStatus.BAD_REQUEST);
//	      }
		
		
		//Danh cho User
		
		Boolean active = userResponse1.getActive();
		String description = userResponse1.getDescription();
		String email = userResponse1.getEmail();
		Boolean nonDel = userResponse1.getNonDel();
		Boolean nonBlock = userResponse1.getNonLocked();
		String pass = passwordEncoder.encode(userResponse1.getEmail());
		System.out.println(pass);
//		//String a = principal.getName();
//		//System.out.println(a);
//		//Optional<User> user = userRepository.findByEmail(principal.getName());
//		//return null;
		System.out.println(active + " " + description + " " + email + " " + nonDel + " " + nonBlock + " "+ pass + " ");
//		//userRepository.save(user);
		User useradd = new User();
		useradd.setActive(active);
		useradd.setDescription(description);
		useradd.setEmail(email);
		useradd.setNonDel(nonDel);
		useradd.setNonLocked(nonBlock);
		useradd.setPassword(pass);
		userRepository.save(useradd);
//		userRepository.addUser1(active, description, email,  nonDel, nonBlock, pass);
		
		Optional<User> user = userRepository.findByEmail(email);

		String address = userResponse1.getAddress();
		String avatar = userResponse1.getAvatar();
		Date birthDate = userResponse1.getBirthday();
		String employee1 = userResponse1.getEmployeeName();
		Boolean gender = userResponse1.getGender();
		String phone = userResponse1.getPhone();
		String team = userResponse1.getTeam();
		Long user_id = user.get().getId();
		String roleName = userResponse1.getRoleName();
		employeeRepository.addEmployee1(address, avatar, birthDate , employee1 , gender, phone, team, user_id);
		
		
		//cho Role
		Role role1 = roleService.findByRoleName(roleName);
		
	//cho bang UserRole
		UserRole userRole = new UserRole(user.get(), role1);
		userRoleRepository.save(userRole);
		return new ResponseEntity<Object>("add user thanh cong", HttpStatus.OK);
	}

	
	//sua thong tin cua user
	@PutMapping(path = "/editUser")
	public ResponseEntity<Object> editUser(@Valid @RequestBody UserResponse2 userResponse1, BindingResult result, Principal principal) {
		Role role = null;
		
		Optional<User> user = userRepository.findByEmail(userResponse1.getEmail());
		if(!user.isPresent()) {
			return new ResponseEntity<Object>("Khong co user chua email nay.", HttpStatus.NOT_FOUND);
			
		}
		String pass = passwordEncoder.encode(userResponse1.getEmail());
		
		user.get().setActive(userResponse1.getActive());
		user.get().setDescription(userResponse1.getDescription());
		user.get().setEmail(userResponse1.getEmail());
		user.get().setNonDel(userResponse1.getNonDel());
		user.get().setNonLocked(userResponse1.getNonLocked());
		//userRepository.save(user.get());
		user.get().getEmployee().setAddress(userResponse1.getAddress());
//		user.get().getEmployee().setAvatar(userResponse1.getAvatar());
		user.get().getEmployee().setDateOfBirth(userResponse1.getBirthday());
		user.get().getEmployee().setEmployeeName(userResponse1.getEmployeeName());
		user.get().getEmployee().setGender(userResponse1.getGender());
		user.get().getEmployee().setPhone(userResponse1.getPhone());
		user.get().getEmployee().setTeam(userResponse1.getTeam());
		user.get().getUserRoles().get(0).getRole().setDescription(userResponse1.getDescription());
		user.get().getUserRoles().get(0).getRole().setRoleName(userResponse1.getRoleName());
		
		
		userRepository.save(user.get());
		
		
//		List<UserRole> list = user.get().getUserRoles();
//		//for(UserRole userRole: list) {
//		//	role = userRole.getRole();
//		//}
//		role = list.get(0).getRole();
//		//role.setDescription(userResponse1.getDescriptionRole());
//		//role.setRoleName(userResponse1.getRoleName());
//		//role.setUserRoles(list);
//		
//		UserRole userRole = new UserRole(user.get(), role);
//		userRoleRepository.save(userRole);
		
		
		return new ResponseEntity<Object>("add sua thanh cong", HttpStatus.OK);
	}
	
	//tim kiem thong tin cua user theo ten hoac team hoac email.
	@GetMapping("/filterUser")
	
	public ResponseEntity<Object> filterUser(@RequestParam("search") String search) {
		System.out.println(search);
		if(search.isEmpty()) {
			return new ResponseEntity<Object>(userService.showUserStateNonDel(), HttpStatus.OK);
		}
		List<UserResponse2> list = userService.filterUser(search);
		if(list.size() == 0) {
			return new ResponseEntity<Object>("Khong co ban ghi nao de hien thi", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
}
