package device.management.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import device.management.demo.entity.BlockUser;
import device.management.demo.entity.User;
import device.management.demo.service.UserService;
import device.management.demo.util.LoginErrorMessage;
import device.management.demo.util.VerificationUtil;

@Component
public class FailureLoginHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserService userSevice;

	@Autowired
	private VerificationUtil veritificationUtil;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String messageError = LoginErrorMessage.loginErrorMessage(exception);
		request.getSession().setAttribute("error", messageError);
		String email = request.getParameter("email");
		if (email != null) {
			System.out.println("-------" + email);
			User objUser = userSevice.getUserByEmail(email);
			if (objUser == null) {
				getRedirectStrategy().sendRedirect(request, response, "/login?error");
				return;
			}
			if (objUser.getNonLocked()) {
				BlockUser blockUser = objUser.getBlockUser();
				
				if (blockUser == null) {
					BlockUser newBlockuser = new BlockUser(objUser, null, 1);
					objUser.setBlockUser(newBlockuser);
					System.out.println("blockUser null");
					userSevice.editUser(objUser);
					getRedirectStrategy().sendRedirect(request, response, "/login?error");
					return;
				}
				System.out.println("dfdfd");
				int num_fails = blockUser.getNumberFail();
				num_fails = num_fails + 1;
				if (num_fails >= 5) {
					System.out.println("Numfails >= 5");
					objUser.setNonLocked(false);
					blockUser.setNumberFail(num_fails);
					blockUser.setBlockTime(veritificationUtil.calculatorExpireTime());
					objUser.setBlockUser(blockUser);
					userSevice.editUser(objUser);
					getRedirectStrategy().sendRedirect(request, response, "/login?error");
					return;
				} else {
					System.out.println("Numfials < 5");
					blockUser.setNumberFail(num_fails);
					objUser.setBlockUser(blockUser);
					userSevice.editUser(objUser);
					getRedirectStrategy().sendRedirect(request, response, "/login?error");
					return;
				}
			}
		}
		getRedirectStrategy().sendRedirect(request, response, "/login?error");
	}

}
