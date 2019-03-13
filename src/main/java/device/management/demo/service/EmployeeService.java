package device.management.demo.service;

import java.util.Date;
import java.util.List;

import device.management.demo.entity.Employee;
import device.management.demo.entity.response.UserResponse;

public interface EmployeeService {

	/**
   	* @summary filter employee
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email, name, team
   	* @return List<UserResponse> emp
   	**/
	List<UserResponse> listEmployeeByFilter(String key);
	
	//van
		public Boolean existsByEmployee(long userId);

		public void addEmployeeFunction(String address, Date birthDate, String employeeName, Boolean gender,
				String phone, String team, Long user_id);
}
