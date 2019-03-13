package device.management.demo.service;

import device.management.demo.entity.Role;

public interface RoleService {

	Role findByRoleName(String rolename);
}
