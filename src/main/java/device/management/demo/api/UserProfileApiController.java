package device.management.demo.api;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.entity.User;
import device.management.demo.entity.dto.PasswordDTO;
import device.management.demo.entity.response.UserResponse2;
import device.management.demo.service.PasswordService;
import device.management.demo.service.UserService;

//author: tu viet van
@RestController
@RequestMapping("/my-profile")
public class UserProfileApiController {
	@Autowired
	UserService userService;

	@Autowired
	PasswordService passwordService;

	@GetMapping
	public ResponseEntity<Object> viewMyProfile(Principal principal) {
		UserResponse2 userRespone = passwordService.viewCurrentUserResponse(principal);
		
		return new ResponseEntity<>(userRespone, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Object> updateMyProfile(@Valid @RequestBody UserResponse2 userResponse, BindingResult bindingResult) {
		System.out.println(userResponse.getAddress());
		if (bindingResult.hasErrors()) {
			String fieldName = bindingResult.getFieldError().getField();
			String message = bindingResult.getFieldError().getDefaultMessage();
			return new ResponseEntity<>(fieldName+" : "+message,HttpStatus.BAD_REQUEST);
		}
		if (userResponse == null) {
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
		}
		
		//userService.saveUser(userResponse);
		return new ResponseEntity<>("Update user successfully", HttpStatus.OK);

	}

	@PutMapping("/change-password")
	public ResponseEntity<Object> changePasswordMyProfile(@Valid @RequestBody PasswordDTO passwordDTO, BindingResult bindingResult,
			Principal principal) {
		
		if (passwordDTO == null) {
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
		}
		if (bindingResult.hasErrors()) {
			String fieldName = bindingResult.getFieldError().getField();
			System.out.println("///////////////////"+ fieldName);
			String message = bindingResult.getFieldError().getDefaultMessage();
			System.out.println(message);
			return new ResponseEntity<>(fieldName+" : "+message,HttpStatus.BAD_REQUEST);
		}
		
		
		User user = passwordService.viewCurrentUsers(principal);
		System.out.println("/////////////////////"+ user.getEmail());

		String existingPassword = passwordDTO.getPasswordCurrent();
		String dbPassword = user.getPassword();
		System.out.println(dbPassword);
		String newPassword = passwordDTO.getNewPassword();
		String newMatchingPassword = passwordDTO.getNewMatchingPassword();

		boolean checkDuplicatePasswordCurrent = passwordService.checkDuplicatePasswordCurrent(existingPassword,
				dbPassword);
		boolean checkDuplicateNewPassowrds = passwordService.checkDuplicateNewPasswords(newPassword, existingPassword);
		boolean checkDuplicateMatchingPassword = passwordService.checkDuplicateMatchingPassword(newPassword,
				newMatchingPassword);

		if (!checkDuplicatePasswordCurrent) {
			return new ResponseEntity<>("The password you enter does not match the current password",
					HttpStatus.CONFLICT);
			//System.out.println("Khong trung password trong database...");
		}
		if (checkDuplicateNewPassowrds) {
			System.out.println(checkDuplicateNewPassowrds);
			//System.out.println("Password cu trung password moi...");
			return new ResponseEntity<>("The password you entered is the same as your current password",
					HttpStatus.CONFLICT);
		}
		if (!checkDuplicateMatchingPassword) {
			return new ResponseEntity<>("The password you entered does not match Password matching",
					HttpStatus.CONFLICT);
			//System.out.println("Password xac nhan va password khong trung nhau...");
		}

		passwordService.saveNewPasswords(user, newPassword);
		return new ResponseEntity<>("Update password", HttpStatus.OK);

	}

	public UserResponse2 viewCurrentUsers(Principal principal) {
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		UserResponse2 userRespone = new UserResponse2();
		return userRespone;
	}

}
