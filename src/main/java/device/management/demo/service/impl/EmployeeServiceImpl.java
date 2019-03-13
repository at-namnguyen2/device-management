package device.management.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import device.management.demo.entity.Employee;
import device.management.demo.entity.response.UserResponse;
import device.management.demo.repository.EmployeeRepository;
import device.management.demo.service.EmployeeService;
import device.management.demo.util.UserConst;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
   	* @summary filter employee
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param email, name, team
   	* @return List<UserResponse> empObj
   	**/
	@Override
	public List<UserResponse> listEmployeeByFilter(String key) {
		List<Employee> emp = employeeRepository.findByEmployeeNameContainingAndUserNonDelAndUserActiveOrTeamContainingAndUserNonDelAndUserActiveOrUserEmailContainingAndUserNonDelAndUserActive(key, UserConst.NonDel, UserConst.Actice, key, UserConst.NonDel, UserConst.Actice, key, UserConst.NonDel, UserConst.Actice);

		List<UserResponse> empObj = new ArrayList<>();
		for (Employee employee : emp) {
			UserResponse Res = ConvertToEmpResponse(employee);
			empObj.add(Res);
		}
		return empObj;
	}

	/**
   	* @summary convert from UserResponse to Employee
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param Employee emp
   	* @return UserResponse empObj
   	**/
	public UserResponse ConvertToEmpResponse(Employee emp) {
		UserResponse empObj = new UserResponse();
		empObj.setAddress(emp.getAddress());
		empObj.setAvatar(emp.getAvatar());
		empObj.setEmail(emp.getUser().getEmail());
		empObj.setGender(emp.getGender());
		empObj.setId(emp.getId());
		empObj.setName(emp.getEmployeeName());
		empObj.setPhone(emp.getPhone());
		empObj.setTeam(emp.getTeam());
		return empObj;
	}

	@Override
	public Boolean existsByEmployee(long userId) {
		Optional<Employee> exist = employeeRepository.findById(userId);
		if(!exist.isPresent()) {
			return false;
		}
		return true;
		
	}

	@Override
	public void addEmployeeFunction(String address, Date birthDate, String employeeName, Boolean gender,
			String phone, String team, Long user_id) {
		employeeRepository.addEmployeeFunction(address, birthDate, employeeName, gender,
			phone, team, user_id);	
	}
		
	}
