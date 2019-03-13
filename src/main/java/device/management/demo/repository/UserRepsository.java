package device.management.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import device.management.demo.entity.User;
import java.lang.Boolean;

public interface UserRepsository extends JpaRepository<User, Long> {
	
	/**
   	* @summary find user via email
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email
   	* @return Optional<User>
   	**/
	Optional<User> findByEmail(String email);
	
    /**
	* @summary return list user via rolename
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param roleName
	* @return List<User>
   	**/	
	List<User> findByUserRolesRoleRoleName(String roleName);
	
	
	
	//van
		@Transactional
		@Modifying
		@Query(value = "update user set active = 1 where id=?1", nativeQuery = true)
		int activeUser(Long id);
		
		@Transactional
		@Modifying
		@Query(value = "insert into user values(0, false, ?1, ?2, true, true, ?3)", nativeQuery = true)
		int addUserFunction(String description, String email, String password);
		
		@Transactional
		@Query(value = "select * from  user where non_del = 1 and active = 1", nativeQuery = true)
		List<User> ShowUserStateNondel();
		
	List<User> findByNonDelIsTrueAndActiveIsTrue();
		
		List<User> findByUserRolesRoleRoleNameAndNonDelIsTrueAndActiveIsTrue(String rolename);
		
		@Transactional
		@Modifying
		@Query(value = "insert into user(active, description, email, non_del, non_locked,  password)  values(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
		int addUser1(Boolean active, String description, String email, Boolean nonDel, Boolean nonLocked, String password);
	   

		List<User> findByEmailContainingOrEmployeeEmployeeNameContainingOrEmployeeTeam(String email, String name, String team);
	    
		
		@Transactional
		@Modifying
		@Query(value = "update user set non_del = 0", nativeQuery = true)
		int delAllUser();
		
		@Transactional
	    @Modifying
	    @Query(value = "update user set non_del = 0 where id = ?1", nativeQuery = true)
	    int deleteUserSoft(Long id);
}
