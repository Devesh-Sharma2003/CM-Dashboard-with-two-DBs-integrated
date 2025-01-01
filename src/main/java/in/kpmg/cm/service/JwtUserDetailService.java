package in.kpmg.cm.service;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import in.kpmg.cm.config.LoginCred;
import in.kpmg.cm.dto.rsp.ApiResponse;

@Service
public class JwtUserDetailService implements UserDetailsService{

	public ResponseEntity<?> validateTokenUsername() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			// System.out.println(authentication);

			if (authentication != null && authentication.isAuthenticated()) {
				// System.out.println(authentication.getName());

				return new ResponseEntity<>(
						new ApiResponse<>(true, "Validation Successful", null, HttpStatus.OK.value()), HttpStatus.OK);

			} else
				throw new SecurityException("Unauthorized Access!");
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse<>(false, e.getMessage(), null, HttpStatus.UNAUTHORIZED.value()),
					HttpStatus.UNAUTHORIZED);
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		return new org.springframework.security.core.userdetails.User(LoginCred.getUserName() , LoginCred.getPassword(),
                new ArrayList<>());
	}
}
