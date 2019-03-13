package device.management.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.repository.extenstion.Device_Deliver_ReceiverRepositoryExtenstion;

public interface Device_Deliver_ReceiveRepository extends JpaRepository<Device_Deliver_Receive, Long>, JpaSpecificationExecutor, Device_Deliver_ReceiverRepositoryExtenstion {

	/**
	 * @summary return device deliver receive return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param Device
	 * @return Device_Deliver_Receive
	 **/
	Device_Deliver_Receive findByDeviceDetail(DeviceDetail deviceDetail);

	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param team,name,email, deviceName, catalogName
	 * @return List<Device_Deliver_Receive>
	 **/
	Page<Device_Deliver_Receive> findByEmployeeTeamContainingOrEmployeeEmployeeNameContainingOrEmployeeUserEmailContainingOrDeviceDetailDeviceNameContainingOrDeviceDetailDeviceDeviceCatalogNameContaining(
			String team, String employeeName, String email, String deviceName, String catalogName, Pageable page);

	/**
	 * @summary find detail allocation via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param email
	 * @return List<Device_Deliver_Receive>
	 **/
	List<Device_Deliver_Receive> findByEmployeeUserEmailAndDateReturnNull(String email);

//	findByEmployeeUserEmail
	Long countByEmployeeUserEmail(String email);

	Long countByEmployeeUserEmailAndDateReturnNull(String email);

	Device_Deliver_Receive findTop1ByEmployeeUserEmail(String email);

	/**
	 * @summary filter record aloction
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param
	 * @return List<Device_Deliver_Receive>
	 **/
	List<Device_Deliver_Receive> findTop10ByDateReturnNullOrderByIdDesc();

	/**
	 * @summary filter record return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param
	 * @return List<Device_Deliver_Receive>
	 **/
	Page<Device_Deliver_Receive> findByDateReturnNotNullOrderByIdDesc(Pageable page);
	Page<Device_Deliver_Receive> findByDateReturnNullOrderByIdDesc(List<EmpDeviceResponse> key,Pageable page);
	
	List<Device_Deliver_Receive> findByDateDeliverReceiveBetweenOrDateReturnBetween(Date start1, Date end1, Date start2,
			Date end2);
	
	List<Device_Deliver_Receive> findByDateReturnNull(Specification<Device_Deliver_Receive> spec,Pageable page);
}


