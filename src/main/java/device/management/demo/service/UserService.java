package device.management.demo.service;

import java.util.List;
import java.util.Optional;


import device.management.demo.entity.User;
import device.management.demo.entity.dto.UserDTO;
import device.management.demo.entity.response.PhoneSA;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.entity.response.UserResponse;
import device.management.demo.entity.response.UserResponse2;

public interface UserService {
	

    /**
   	* @summary return UserResonse via email
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return User
   	**/

    User getUserByEmail(String email);
    
    /**
   	* @summary return UserResonse via email
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return UserResponse
   	**/
    UserResponse findUserByEmail(String email);
    
    /**
	* @summary edit profile of user
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param userdto
	* @return true
   	**/
    Boolean editProfileUser(String email, String userdto);
    
    /**
	* @summary list SA contact: phone via rolename Admin
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param 
	* @return listPhone
   	**/
	List<PhoneSA> getSAContact();
	    
    /**
   	 * @summary return User
   	 * @date sep 13, 2018
   	 * @author Nam.Nguyen2
   	 * @param email
   	 * @return User
   	 */
    User updateUser(RequestResponse request);
    
    
    //van
    User saveUser(User user);
    User editUser(User objUser);
    boolean checkDuplicateEmail(String email);
    User addUser(UserDTO userDTO);
    boolean activeUser(Long id);
	void addUserFunction(String description, String email, String password);
	List<User> getAllUser();
	User getUserById1(long id);
	boolean deleteUserById(long id);
	List<UserResponse2> showUserStateNonDel();
	List<UserResponse2> showUserStateNonDel2();
	List<UserResponse2> getUserAdmin();
	List<UserResponse2> filterUser(String search);

	UserResponse2 showInfoUser(User user);
	List<UserResponse2> getAllUserNotAdmin();

    void deleteUserSoftService(Long id);
}
