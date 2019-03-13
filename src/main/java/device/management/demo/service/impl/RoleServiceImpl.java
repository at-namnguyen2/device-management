package device.management.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Role;
import device.management.demo.repository.RoleRepository;
import device.management.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role findByRoleName(String rolename) {
		// TODO Auto-generated method stub
		return roleRepository.findByRoleName(rolename);
	}

	
}
