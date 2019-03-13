package device.management.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private UnBlockUserFilter unBlockUserFilter;

	@Autowired
	private SuccessLoginHandler successLoginHandle;

	@Autowired
	private FailureLoginHandler failLoginHandle;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
    
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disable csrf
		http.csrf().disable();

		http.headers().frameOptions().sameOrigin();
		
//		http.authorizeRequests()
//				.antMatchers("/forget-password**", "/h2-console/**", "/login**", "/registerAccount**",
//						"/activeAccount**", "/change-password**")
//				.permitAll();
//		
//           //http.authorizeRequests().antMatchers("/user").authenticated().antMatchers("/admin/**").hasAuthority("ADMIN").and().authorizeRequests().anyRequest().authenticated();
//           
//		    http.authorizeRequests().antMatchers("/admin1/**").hasAuthority("ADMIN");
//           
//		    http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
		    
	        //http.authorizeRequests().antMatchers("/trang-chung/**").authenticated();
		http.authorizeRequests()
		.antMatchers("/forget-password**", "/h2-console/**", "/login**", "/register**",
				"/activeAccount**", "/change-password**")
		.permitAll();

http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN").and().authorizeRequests().anyRequest().authenticated();

http.authorizeRequests().antMatchers("/home/**").hasAuthority("USER").and().authorizeRequests().anyRequest().authenticated();
			
		    http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().and().formLogin()
				//.loginPage("/login").permitAll().usernameParameter("email").passwordParameter("password")
		         .loginPage("/login").usernameParameter("email").passwordParameter("password")
				.loginProcessingUrl("/login").successHandler(successLoginHandle).failureHandler(failLoginHandle)
				// setting remember me
				.and().rememberMe().rememberMeParameter("remember-me")
				// setting logout
				.and().logout().logoutUrl("/logout").permitAll()
				// delete cookies when logout
				.deleteCookies("JSESSIONID", "remember-me").logoutSuccessUrl("/login?logout").permitAll().and()
				.httpBasic();

		//http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	}

	@Override
	public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web)
			throws Exception {
		web.ignoring().antMatchers("/css/**", "/util/**", "/images/**", "/js/**","/fonts/**","/vendor/**","/assets/**");
	}
}
