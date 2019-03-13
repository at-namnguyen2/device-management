package device.management.demo.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import device.management.demo.entity.Employee;
import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.repository.RequestRepository;
import device.management.demo.repository.UserRepsository;
import device.management.demo.service.UserService;
import device.management.demo.util.RoleConst;
import device.management.demo.util.requestconst;
import device.management.demo.entity.dto.UserDTO;
import device.management.demo.entity.response.PhoneSA;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.entity.response.UserResponse;
import device.management.demo.entity.response.UserResponse2;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepsository userRepository;

	@Autowired
	private RequestRepository requestRepository;

	/**
	 * @summary return UserResonse via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param email
	 * @return User
	 **/
	@Override
	public User getUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return null;
		}
		return optionalUser.get();
	}

	/**
	 * @summary return UserResonse via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param email
	 * @return UserResponse
	 **/
	@Override
	public UserResponse findUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			return null;
		}
		UserResponse ur = convertToUserResponse(optionalUser.get());
		return ur;
	}

	/**
	 * @summary convert from User entity to UserResponse entity
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param user
	 * @return UserResponse
	 */
	public UserResponse convertToUserResponse(User user) {
		UserResponse ur = new UserResponse();
		ur.setAddress(user.getEmployee().getAddress());
		ur.setAvatar(user.getEmployee().getAvatar());
		ur.setDateOfBirth(user.getEmployee().getDateOfBirth());
		ur.setEmail(user.getEmail());
		ur.setGender(user.getEmployee().getGender());
		ur.setId(user.getId());
		ur.setName(user.getEmployee().getEmployeeName());
		ur.setPhone(user.getEmployee().getPhone());
		ur.setTeam(user.getEmployee().getTeam());
		return ur;
	}

	/**
	 * @summary edit profile of user
	 * @date sep 13, 2018
	 * @author Nam.Nguyen2
	 * @param userdto
	 * @return true
	 **/
	@Override
	public Boolean editProfileUser(String email,String userdto) {
		Date date = new Date();
		User user = getUserByEmail(email);
//		Optional<Request> optionalRequest = requestRepository.findByUserAndType(user, requestconst.Update_Info);
//		if (!optionalRequest.isPresent()) {
//			Request request = new Request("content:" + userdto, requestconst.Update_Info, requestconst.Pending, date, user);
//			requestRepository.save(request);
//		} else {
//			Request request = optionalRequest.get();
//			request.setContent("content:" + userdto);
////			request.setUpdateDate(date);
//			requestRepository.save(request);
//		}
		
		return true;
	}

	/**
	 * @summary list SA contact: phone via rolename Admin
	 * @date sep 13, 2018
	 * @author Nam.Nguyen2
	 * @param
	 * @return listPhone
	 **/
	@Override
	public List<PhoneSA> getSAContact() {
		List<PhoneSA> listPhone = new ArrayList<>();
		List<User> user = userRepository.findByUserRolesRoleRoleName("ADMIN");
		for (User u : user) {
			PhoneSA phone = new PhoneSA();
			phone.setPhone(u.getEmployee().getPhone());
			phone.setName(u.getEmployee().getEmployeeName());
			System.out.println("testfor:" + phone.getPhone());
			listPhone.add(phone);
		}
		return listPhone;
	}

//	@Override
//	public List<User> getUserAdmin(String roleName) {
//		// TODO Auto-generated method stub
//		return userRepository.findByUserRolesRoleRoleName(roleName);
//	}

	/**
	 * @summary return User
	 * @date sep 13, 2018
	 * @author Nam.Nguyen2
	 * @param request
	 * @return User
	 */
	@Override
	public User updateUser(RequestResponse request) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserDTO userdto = mapper.readValue(request.getContent(), UserDTO.class);
			Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
			if (!optionalUser.isPresent()) {
				return null;
			}
			
			User user = optionalUser.get();
			user.getEmployee().setEmployeeName(userdto.getFullname());
			user.getEmployee().setTeam(userdto.getTeam());
			user.getEmployee().setDateOfBirth(userdto.getBirthDay());
			user.getEmployee().setGender(userdto.getGender());
			user.getEmployee().setPhone(userdto.getPhone());
			user.getEmployee().setAddress(userdto.getAddress());
			user.getEmployee().setAvatar(userdto.getAvatar());
			System.out.println("show3:" + userdto.getFullname());
			return userRepository.save(user);
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	//van
		@Override
		public User editUser(User objUser) {
			return userRepository.save(objUser);
		}

		@Override
		public boolean checkDuplicateEmail(String email) {
			Optional<User> optionalUser = userRepository.findByEmail(email);
			if (optionalUser.isPresent())
				return true;
			return false;
		}

		@Modifying
		@Override
		public User addUser(UserDTO userDTO) {
			User user = convertUserDtoToUser(userDTO);
			return userRepository.save(user);
		}

		public User convertUserDtoToUser(UserDTO userDTO) {
			User user = new User();
			Employee employee = new Employee(userDTO.getFullname(), userDTO.getPhone(), userDTO.getAddress(),
					userDTO.getGender(), userDTO.getBirthDay());
			user.setEmail(userDTO.getEmail());
			user.setPassword(userDTO.getPassword());
			employee.setUser(user);
			user.setEmployee(employee);
			return user;
		}

		@Transactional
		@Override
		public boolean activeUser(Long id) {
			Optional<User> optional = userRepository.findById(id);
			if (!optional.isPresent()) {
				return false;
			}
			userRepository.activeUser(id);
			return true;
//		}
		}

		@Override
		public void addUserFunction(String description, String email, String password) {
			userRepository.addUserFunction(description, email, password);

		}

		@Override
		public List<User> getAllUser() {
			List<User> listUser = userRepository.findAll();
			if (listUser.size() != 0) {
				return listUser;
			}
			return null;
		}

		@Override
		public User getUserById1(long id) {
			// TODO Auto-generated method stub
			Optional<User> user = userRepository.findById(id);
			if (!user.isPresent()) {
				return null;
			}
			return user.get();
		}

		@Override
		public boolean deleteUserById(long id) {
			// TODO Auto-generated method stub
			Optional<User> user = userRepository.findById(id);
			if (!user.isPresent()) {
				return false;
			}
			userRepository.delete(user.get());
			return true;
		}

		@Override
		public List<UserResponse2> showUserStateNonDel() {
			List<User> listUserStateNondel = userRepository.findByNonDelIsTrueAndActiveIsTrue();
			List<UserResponse2> list = new ArrayList<>();
			for (User u : listUserStateNondel) {
				
				UserResponse2 ur = convertTOResponse(u);
				list.add(ur);
			}
			return list;
		}

		public UserResponse2 convertTOResponse(User user) {
			UserResponse2 ur2 = new UserResponse2();
			
			ur2.setId(user.getId());
			System.out.println(user.getId());
			System.out.println(user.getEmail());
			ur2.setEmployeeName(user.getEmployee().getEmployeeName());
			ur2.setPhone(user.getEmployee().getPhone());
			ur2.setBirthday(user.getEmployee().getDateOfBirth());
			ur2.setAddress(user.getEmployee().getAddress());
			ur2.setGender(user.getEmployee().getGender());
			ur2.setAvatar(user.getEmployee().getAvatar());
			ur2.setTeam(user.getEmployee().getTeam());
			ur2.setRoleName(user.getUserRoles().get(0).getRole().getRoleName());
			ur2.setActive(user.getActive());
			ur2.setEmail(user.getEmail());
			ur2.setNonLocked(user.getNonLocked());
			ur2.setNonDel(user.getNonDel());
			ur2.setDescription(user.getDescription());
			return ur2;
		}

		@Override
		public List<UserResponse2> getUserAdmin() {
			List<User> list = userRepository.findByUserRolesRoleRoleNameAndNonDelIsTrueAndActiveIsTrue(RoleConst.ADMIN);
			List<UserResponse2> list1 = new ArrayList<>();
			for (User u : list) {
				
				UserResponse2 ur = convertTOResponse(u);
				list1.add(ur);
			}
			return list1;
		}

		@Override
		public List<UserResponse2> filterUser(String search) {
			List<User> listUser = userRepository.findByEmailContainingOrEmployeeEmployeeNameContainingOrEmployeeTeam(search, search, search);
			List<UserResponse2> list1 = new ArrayList<>();
			for (User u : listUser) {
				System.out.println(u.getEmail());
				UserResponse2 ur = convertTOResponse(u);
				list1.add(ur);
			}
			return list1;
		}
		
		@Override
		public User saveUser(User user) {
			return userRepository.save(user);
		}

		@Override
		public UserResponse2 showInfoUser(User user) {
			UserResponse2 userResponse2 = convertTOResponse(user);
			return userResponse2;
		}

		@Override
		public List<UserResponse2> showUserStateNonDel2() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
	    public List<UserResponse2> getAllUserNotAdmin() {
	        
	        List<User> list = userRepository.findByUserRolesRoleRoleNameAndNonDelIsTrueAndActiveIsTrue(RoleConst.USER);
	        List<UserResponse2> list1 = new ArrayList<>();
	        for (User u : list) {
	            
	            UserResponse2 ur = convertTOResponse(u);
	            list1.add(ur);
	        }
	        return list1;
	    }

	    @Override
	    public void deleteUserSoftService(Long id) {
	        // TODO Auto-generated method stub
	        userRepository.deleteUserSoft(id);
	        
	    }

}
