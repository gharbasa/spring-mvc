package spring.mvc.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AccountTokenService implements IAccountTokenService {
	private static Logger log = LoggerFactory.getLogger(AccountTokenService.class);
	//@Autowired
	//BCrypt bCrypt;
	//BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public String generateToken(String account) {
		String salt = BCrypt.gensalt();
		String token = BCrypt.hashpw(account, salt);
		Boolean result = BCrypt.checkpw(account, token);
		Assert.isTrue(result, "Supposed to be true");
		log.debug("salt=" + salt + ", Result of Verification " + result);
		//String token = bCryptPasswordEncoder.encode(account);
		return token;
	}
	/**
	public static void main(String[] str) {
		Random randChars = new Random();
		String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, 6);
		System.out.println("Ramdon image code=" + sImageCode);
	}
	*/
}

