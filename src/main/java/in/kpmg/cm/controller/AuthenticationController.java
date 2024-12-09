package in.kpmg.cm.controller;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.cm.config.AesUtil;
import in.kpmg.cm.config.JwtTokenUtil;
import in.kpmg.cm.config.LoginCred;
import in.kpmg.cm.dto.rqt.JwtRequest;
import in.kpmg.cm.service.JwtUserDetailService;


@RestController
@RequestMapping("/user")
public class AuthenticationController {
	
	String userName=LoginCred.getUserName();
	String passWord=LoginCred.getPassword();

	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private JwtUserDetailService userDetailService;
	
	@GetMapping("/test")
	public ResponseEntity<?> testing() {
		return ResponseEntity.ok().body("Everything looks good!");
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
			HttpServletRequest req) throws Exception {
		try {

			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
			HashMap<String, Object> result = new HashMap<String, Object>();

			result.put("token", token);
//            result.put("userdetails", userDetailsService.getLoginUserData(authenticationRequest.getUsername()));
			result.put("status", true);
			return ResponseEntity.ok(result);
		} catch (Exception ex) {

			ex.printStackTrace();

			// int k=0;
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("status", false);
			result.put("message", "Invalid Username or Password");

			return ResponseEntity.status(201).body(result).ok(result);

		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			if (username==null || password==null) {
				throw new Exception("USERNAME OR PASSWORD CAN NOT BE NULL");
			}
			
			if (!userName.equals(username) || !passWord.equals(password)) {
				throw new Exception("WRONG CREDENTIALS!");
			}
		}catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@GetMapping("/validate-token")
	public ResponseEntity<?> validateTokenUsername() {
		return this.userDetailService.validateTokenUsername();
	}

}
