package device.management.demo.util;

import javax.servlet.ServletRequest;

import device.management.demo.entity.Role;

public class UserUtils {



    public static Long getCurrentUserId(ServletRequest request) {



        return request == null ? null :(Long) request.getAttribute("staffId");

    }

    public static Role getRole(ServletRequest request) {

        return request == null ? null : (Role) request.getAttribute("role");

    }



}


