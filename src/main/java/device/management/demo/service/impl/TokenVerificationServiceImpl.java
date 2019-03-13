package device.management.demo.service.impl;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  device.management.demo.entity.TokenVerifition;
import device.management.demo.repository.TokenVerifitionRepository;
import device.management.demo.service.TokenVerificationService;

@Service
public class TokenVerificationServiceImpl implements TokenVerificationService {
    
	@Autowired
	TokenVerifitionRepository tokenVerifitionRepository;
	
	
	@Override
	public TokenVerifition addToken(TokenVerifition tokenVerifition) {
		return tokenVerifitionRepository.save(tokenVerifition);
	}
	
	@Override
	public TokenVerifition findTokenByTokenCode(String token) {
		// TODO Auto-generated method stub
		Optional<TokenVerifition> optionalToken = tokenVerifitionRepository.findByTokenCode(token);
		if (!optionalToken.isPresent()) {
			return null;
		}
		return optionalToken.get();
	}
	
	@Override
	public TokenVerifition editToken(TokenVerifition tokenVerifition) {
		// TODO Auto-generated method stub
		return tokenVerifitionRepository.save(tokenVerifition);
	}
	
	@Transactional
	@Override
	public boolean deleteTokenById(Long id) {
		// TODO Auto-generated method stub
		Optional<TokenVerifition> optionalToken = tokenVerifitionRepository.findById(id);
		if (!optionalToken.isPresent()) {
			return false;
		}
		tokenVerifitionRepository.deleteTokenById(id);
		return true;
	}

	@Override
	public void addTokenFunction(Date expireDate, String registCode, Long user_id) {
		tokenVerifitionRepository.addTokenFunction(expireDate, registCode, user_id);
		
	}
}
