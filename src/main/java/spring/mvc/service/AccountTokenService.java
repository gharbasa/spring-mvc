package spring.mvc.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AccountTokenService implements IAccountTokenService {
	private static Log log = LogFactory.getLog(AccountTokenService.class.getName());
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
}

