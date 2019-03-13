package device.management.demo.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import device.management.demo.entity.TokenVerifition;

public interface TokenVerifitionRepository extends JpaRepository<TokenVerifition, Long> {

	Optional<TokenVerifition> findByTokenCode(String token);
	
	@Transactional
	@Modifying
	@Query(value = "delete from token_verifition where id = ?1", nativeQuery = true)
	int  deleteTokenById(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "insert into token_verifition values(0 ,?1, ?2, ?3)", nativeQuery = true)
	int addTokenFunction(Date expireDate, String registCode, Long user_id);

}
