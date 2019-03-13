package device.management.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import device.management.demo.entity.Role;;

public interface RoleRepository extends JpaRepository<Role, Long>{


	@Transactional
	@Modifying
	@Query(value = "insert into role values(0, 'Quyen User', 'USER')", nativeQuery = true)
    int addRole();
	
	@Transactional
	@Query(value = "select * from role where role_name = 'ADMIN' ", nativeQuery = true)
	List<Role> findAdmin();

	Role findByRoleName(String rolename);

}
