package device.management.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import device.management.demo.entity.Employee;
import java.lang.String;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	/**
   	* @summary filter employee containing
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email, name, team, nondel, active
   	* @return List<Employee>
   	**/
	List<Employee> findByEmployeeNameContainingAndUserNonDelAndUserActiveOrTeamContainingAndUserNonDelAndUserActiveOrUserEmailContainingAndUserNonDelAndUserActive(String name, Boolean nondel, Boolean active, String team, Boolean nondel2, Boolean active2, String email,Boolean nondel3, Boolean active3);
	
	@Transactional
	@Modifying
	@Query(value = "update user set enable = 1 where id=?1", nativeQuery = true)
	int activeUser(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "insert into employee values(0 ,?1, null, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
	int addEmployeeFunction(String address,  Date birthDate, String employeeName, Boolean gender,
			String phone, String team, Long user_id);
    
	
	@Transactional
	@Modifying
	@Query(value = "insert into employee values(0 ,?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
	int addEmployee1(String address, String avatar, Date birthDate, String employee1, Boolean gender, String phone,
			String team, Long user_id);	
	

}
