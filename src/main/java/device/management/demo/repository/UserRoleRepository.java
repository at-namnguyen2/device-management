package device.management.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import  device.management.demo.entity.UserRole;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUserId(Long userId);
		 
	@Transactional
	@Modifying
	@Query(value = "insert into user_role values(0,'USER', 'USER', ?1)", nativeQuery = true)
	int addUserRole(Long user_id);

	@Transactional
	@Modifying
	@Query(value = "insert into user_role values(0, ?2, ?1)", nativeQuery = true)
	int addUserRole1(Long user_id, Long role_id);
	

}
