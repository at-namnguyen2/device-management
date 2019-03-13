package device.management.demo.util;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class LoginErrorMessage {

	public static String loginErrorMessage(AuthenticationException e) {
		if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
			return "email or password is incorrect!!!";
		} else if (e instanceof AccountExpiredException) {
			return "Your account deleted!!!";
		} else if (e instanceof LockedException) {
			return "Your account is locked!!!";
		} else if (e instanceof DisabledException) {
			return "Your account isn't active";
		} else {
			return e.getMessage();
		}

	}
	
	
}