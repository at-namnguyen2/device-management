package device.management.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.dto.EmpDeviceDTO;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.countResponse;
import device.management.demo.util.QuerySearch;

public interface Device_Deliver_ReceiveService {

	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param team,name,email
	 * @return listDevDeRe
	 **/
	Page<EmpDeviceResponse> filterDevDeRe(String filter, Pageable page);
	
	/**
	 * @summary return list DetailResponse via email
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  email
	 * @return List<EmpDeviceResponse>
	 **/
	
	List<DetailResponse> getDevByMail(String email);
	
	/**
	 * @summary filter record aloction
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse>
	 **/
	List<EmpDeviceResponse> getDevAllocation(QueryParam<EmpDeviceResponse> page);

	/**
	 * @summary filter record return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  team,name,email
	 * @return List<EmpDeviceResponse>
	 **/
	List<EmpDeviceResponse> getDevHistory(Pageable page);	

	/**
	 * @summary add new record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  EmpDeviceDTO empdev
	 * @return Device_Deliver_Receive obj
	 **/
	Device_Deliver_Receive addDevDeRe(EmpDeviceDTO empdev);
	
	/**
	 * @summary del a record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Long id
	 * @return void
	 **/
	boolean delDevDeRe(ArrayList<Long> listId);

	/**
	 * @summary set record to return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return
	 **/
	EmpDeviceResponse setReturn(EmpDeviceResponse edr);
	
	/**
	 * @summary return device deliver receive
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device
	 * @return Device_Deliver_Receive
	 **/
	Device_Deliver_Receive getDevDeRe(DeviceDetail deviceDetail);
	
	countResponse countQuantity(String email);

//	int getPageAllocation(Pageable page);

	int getPageHistory(Pageable page);

	List<EmpDeviceResponse> getAllocationReturnToday();

}
