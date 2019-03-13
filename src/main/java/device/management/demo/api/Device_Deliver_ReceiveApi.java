package device.management.demo.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.dto.EmpDeviceDTO;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.ResponseData;
import device.management.demo.entity.response.countResponse;
import device.management.demo.service.DeviceDetailService;
import device.management.demo.service.Device_Deliver_ReceiveService;
import device.management.demo.service.RequestService;
import device.management.demo.util.QuerySearch;

@RestController
public class Device_Deliver_ReceiveApi {

	private static final Logger logger = LoggerFactory.getLogger(Device_Deliver_ReceiveApi.class);
	@Autowired
	Device_Deliver_ReceiveService device_Deliver_ReceiveService;
	
	@Autowired
	DeviceDetailService deviceDetailService;
	
	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  team,name,email
	 * @return listDevDeRe
	 **/
	@GetMapping(path = "/empdevapi/getuserdevdere")
	public ResponseEntity<Object> getUserDevDeRe(Principal p) {
		  ResponseData res = new ResponseData();
	        try {
	    		String emailprincipal = p.getName();
	    		System.out.println("email"+emailprincipal);
	    		res = new ResponseData();
	    		List<DetailResponse> listDevDeRe = device_Deliver_ReceiveService.getDevByMail(emailprincipal);
	    		res.setData(listDevDeRe);
	        } catch (Exception ex) {
	            res.setError(ex);
	            res.setMessage(ex.getMessage());
	            res.setSuccess(false);
	            logger.error(ex.getMessage());
	        }
		
		return new ResponseEntity<>(res, HttpStatus.OK);
		
	}
	
	/**
	 * @summary filter record via employee
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  team,name,email
	 * @return listDevDeRe
	 **/
	@GetMapping(path = "/filterdevdere")
	public ResponseEntity<Object> filterDevDeRe(@RequestParam String filter, Pageable page) {
		if(filter.isEmpty()) {
			return new ResponseEntity<>("fill to search", HttpStatus.OK);
		}
		Page<EmpDeviceResponse> pageDevDeRe = device_Deliver_ReceiveService.filterDevDeRe(filter, page);
		System.out.println("show"+ page.getPageSize());
		return new ResponseEntity<>(pageDevDeRe, HttpStatus.OK);
		
	}

	/**
	 * @summary filter record aloction
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse>
	 **/
	@PostMapping(path = "/list-device-allocation")
	public ResponseEntity<Object> listDeviceAlocation(@RequestBody QueryParam<EmpDeviceResponse> page){
		ResponseData res = new ResponseData();
		try {
			List<EmpDeviceResponse> data = device_Deliver_ReceiveService.getDevAllocation(page);
			res.setTotalPage(page.getPagingItem().getNumberOfPage());
			res.setTotalRows(page.getPagingItem().getOutRowsNumber());
			Long rowNumber = page.getPagingItem().getOutRowsNumber();
			int pageSize = page.getPagingItem().getPageSize();
			res.setTotalPage((rowNumber % pageSize==0)? rowNumber/pageSize : rowNumber/pageSize+1);
			res.setData(data);
		} catch (Exception ex) {
			res.setError(ex);
			res.setMessage(ex.getMessage());
			res.setSuccess(false);
			logger.error(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	/**
	 * @summary filter record return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  
	 * @return List<EmpDeviceResponse>
	 **/	
	@GetMapping(path = "/getdevhistory")
	public ResponseEntity<Object> getDevHistory(Pageable page){
		
		return new ResponseEntity<>(device_Deliver_ReceiveService.getDevHistory(page), HttpStatus.OK);
	}
	
	/**
	 * @summary add new record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  EmpDeviceDTO ddr
	 * @return String message
	 **/
	@PostMapping(path = "/adddevdere")
	public ResponseEntity<Object> addDevDeRe(@RequestBody EmpDeviceDTO ddr){
		System.out.println("ddr:"+ddr);
		Device_Deliver_Receive Res = device_Deliver_ReceiveService.addDevDeRe(ddr);
		if(Res.equals(null)) {
			new ResponseEntity<>("Allocation fail", HttpStatus.OK);
		}
		deviceDetailService.setWorking(ddr.getDetailId());
		return new ResponseEntity<>("ADD SUCCESS", HttpStatus.OK);
	}
	
	/**
	 * @summary del a record allocation device
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device_Deliver_Receive dl
	 * @return String message
	 **/
	@DeleteMapping(path = "/delallocation")
	public ResponseEntity<String> dellAllocation(@RequestBody ArrayList<Long> listId){
		device_Deliver_ReceiveService.delDevDeRe(listId);
		return new ResponseEntity<>("Delete Success", HttpStatus.OK);
	}
	
	/**
	 * @summary set record to return
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  EmpDeviceResponse edr
	 * @return String message
	 **/
	@PostMapping(path = "/setreturn")
	public ResponseEntity<Object> setReturn(@RequestBody EmpDeviceResponse edr){
		System.out.println("------------------------");
		device_Deliver_ReceiveService.setReturn(edr);
		return new ResponseEntity<>("Delete Success", HttpStatus.OK);
	}
	
	@GetMapping(path = "/empdevapi/countdevice")
	public ResponseEntity<Object> countQuantity(Principal p){
		String principal = p.getName();
	
		countResponse cr = device_Deliver_ReceiveService.countQuantity(principal);
		System.out.println("count1"+cr);
		return new ResponseEntity<>(cr, HttpStatus.OK);
	}
//	@GetMapping(path = "/getPageAllo")
//	public ResponseEntity<Object> getPageAllocation(Pageable page){
//		int totalpage = device_Deliver_ReceiveService.getPageAllocation(page);
//		return new ResponseEntity<>(totalpage, HttpStatus.OK);
//	}
	
	@GetMapping(path = "/getPageHistory")
	public ResponseEntity<Object> getPageHistory(Pageable page){
		System.out.println("hihi"+page);
		int totalpage = device_Deliver_ReceiveService.getPageHistory(page);
		return new ResponseEntity<>(totalpage, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listDevicesAllocationReturnToday")
	public ResponseEntity<Object> getDevicesAllocationReturnToday(){
		List<EmpDeviceResponse> listddr = device_Deliver_ReceiveService.getAllocationReturnToday();
		if(listddr.size() == 0) {
			return new ResponseEntity<>("Not Found Device", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listddr, HttpStatus.OK);
	}
}
