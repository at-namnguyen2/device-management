package device.management.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import device.management.demo.entity.AppParamEntity;
import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.repository.extenstion.RequestRepositoryExtenstion;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long> , RequestRepositoryExtenstion{

	/**
   	* @summary return list requests of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param user
   	* @return listrequest
   	**/	
	List<Request> findTop10ByUserOrderByIdDesc(User user);
	
	/**
   	* @summary return list requests via status pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param status
   	* @return listRequest
   	**/	
	Page<Request> findByRequestStatus(AppParamEntity status, Pageable page);
	
	/**
   	* @summary return list requests via status not pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param status
	 * @param page 
   	* @return listRequest
   	**/	
	Page<Request> findByRequestStatusNotLike(AppParamEntity status, Pageable page);
//	List<Request> findByUserAndRequestTypeAndRequestStatusOrderByUpdateDateDesc(User user, AppParamEntity requestType, AppParamEntity requestStatus);
	
	
//	@Query(value="select * from request q where curdate() = DATE_FORMAT(q.update_date, '%Y-%m-%d')",nativeQuery=true)
//	List<Request> findByUpdateDateBetween(Date dateStart,Date dateEnd);

//	List<Request> findByRequestStatusAndUpdateDateBetween(AppParamEntity Status, Date dateStart, Date dateEnd);
	
	Optional<Request> findByUserAndRequestType(User user, AppParamEntity type);
}
