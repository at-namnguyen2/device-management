package device.management.demo.service;

import java.security.Principal;

import device.management.demo.entity.User;
import device.management.demo.entity.response.UserResponse2;

public interface PasswordService {
	UserResponse2 viewCurrentUserResponse(Principal principal);
	User viewCurrentUsers(Principal principal);
	Boolean checkDuplicatePasswordCurrent(String existingPassword, String dbPassword);
	Boolean checkDuplicateNewPasswords(String newPassword,String existingPassword);
	Boolean checkDuplicateMatchingPassword(String newPassword, String NewMatchingPassword);
	User saveNewPasswords(User user, String newPassword);
}
