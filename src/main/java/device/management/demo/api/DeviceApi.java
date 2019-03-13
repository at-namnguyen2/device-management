package device.management.demo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.Device;
import device.management.demo.entity.DeviceCatalog;
import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.Employee;
import device.management.demo.entity.User;
import device.management.demo.entity.dto.CatalogDTO;
import device.management.demo.entity.dto.DeviceDTO;
import device.management.demo.entity.dto.EditDeviceDTO;
import device.management.demo.entity.response.CatalogDTOResponse;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.entity.response.DeviceDTOResponse;
import device.management.demo.entity.response.DeviceResponse;
import device.management.demo.entity.response.EditCatalogDTOResponse;
import device.management.demo.entity.response.EditDeviceDTOResponse;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.UserResponse2;
import device.management.demo.repository.DeviceCatalogRepository;
import device.management.demo.repository.DeviceDetailRepository;
import device.management.demo.repository.DeviceRepository;
import device.management.demo.repository.Device_Deliver_ReceiveRepository;
import device.management.demo.repository.EmployeeRepository;
import device.management.demo.repository.UserRepsository;
import device.management.demo.service.DeviceCatalogService;
import device.management.demo.service.DeviceService;
//import device.management.demo.service.DeviceService;
import device.management.demo.service.EmployeeService;

//author: tu viet van

@RestController
@RequestMapping("/")

public class DeviceApi {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepsository userRepository;
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private DeviceCatalogRepository deviceCatalogRepository;
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceCatalogService deviceCatalogService;

	//1 nhan vien nhan tra danh sach thiet bi.
	
	@GetMapping("/api/employee/{id}/devices")
	public ResponseEntity<Object> viewDeviceDetailOfUser(@PathVariable("id") long id) {
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<Object>("khong tim thay user", HttpStatus.NOT_FOUND);	
		}
		Employee employee1 = new Employee();
		employee1.setUser(user.get());
		Optional<Employee> employee = employeeRepository.findById(employee1.getUser().getId());	
		
		List<Device_Deliver_Receive> obj = employee.get().getDeviceDeliverReceive();
		List<DeviceDetail> deviceDetail = new ArrayList<DeviceDetail>();
		for(Device_Deliver_Receive device_Deliver_Receive: obj) {
			deviceDetail.add(device_Deliver_Receive.getDeviceDetail());
		}
		
		return new ResponseEntity<Object>(deviceDetail, HttpStatus.OK);	
	}
	
	//xuat danh sach so luong thiet bi.
	@GetMapping("/listDeviceQuantityHideDetail")
	public ResponseEntity<Object> listDeviceQuantityHideDetail() {
	 List<Device> device = deviceRepository.findAll();
	 for(Device obj: device) {
		 System.out.println(obj.getQuantity());
	 }
	 if(device.size() == 0) {
		 return new ResponseEntity<Object>("khong co thiet bi nao...", HttpStatus.NOT_FOUND);
	 }
	 return new ResponseEntity<Object>(device, HttpStatus.OK);
	}
	
	//xuat danh sach tat ca cac thiet bi.
	@GetMapping("/listAllDevice")
	public ResponseEntity<Object> listAllDevice(@RequestBody QueryParam<DeviceResponse> page) {
//		System.err.println("hihi");
	 List<Device> device = deviceRepository.findAll();
	 if(device.size() == 0) {
		 return new ResponseEntity<Object>("Khong co thiet bi nao", HttpStatus.NOT_FOUND);
	 }
	 List<DeviceResponse> ldr = new ArrayList<>();	
	 for (Device d : device) {
		 DeviceResponse dr = ConverttoDeviceResponse(d);
		 ldr.add(dr);
	}
	 return new ResponseEntity<Object>(ldr, HttpStatus.OK);
	}
	
//	//Tim kiem chi tiet thiet bi boi ten(thu nghiem).
//	@GetMapping("/showDeviceDetailByName")
//	public ResponseEntity<Object>showDeviceDetailByName(@RequestParam("name") String name) {
//	 List<DeviceDetail> device = deviceDetailRepository.findByName(name);
//	 if(device.size() == 0) {
//		 return new ResponseEntity<Object>("Khong co thiet bi nao", HttpStatus.NOT_FOUND);
//	 }
//	 return new ResponseEntity<Object>(device, HttpStatus.OK);
//	}
	
	//xuat thong tin catalog theo id_catalog.
	@GetMapping("/catalog/{id}") 
	public ResponseEntity<Object> listDeviceByCatalog(@PathVariable("id") long id) {
		Optional<DeviceCatalog> deviceCatalog = deviceCatalogRepository.findById(id);
		if(!deviceCatalog.isPresent()) {
			return new ResponseEntity<Object>("Khong co catalog thuoc id nay: ", HttpStatus.NOT_FOUND);
		}
		//Device device = new Device();
		List<Device> list = deviceCatalog.get().getDevice();	
	      return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
	
	//tim kiem device (filter)
    @GetMapping("/filterDevice")	
	public ResponseEntity<Object> filteDevice(@RequestParam("search") String search ) {
		System.out.println(search);
		if(search.isEmpty()) {
			return new ResponseEntity<Object>(deviceRepository.findAll(), HttpStatus.OK);
		}
		Device list = deviceService.filterDevice(search);
		if(list.equals(null)) {
			return new ResponseEntity<Object>("Khong co device can tim", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
	
	
	//sua thiet bi theo device_id(thu nghiem).
	@GetMapping("/editDevice/{id}")
	public ResponseEntity<Object>edit_Device(@PathVariable("id") Long id, @RequestParam("name") String name) {
		Optional<Device> device = deviceRepository.findById(id);
		if(!device.isPresent()) {
			return new ResponseEntity<Object>("Khong ton tai thiet bi chua id nay", HttpStatus.NOT_FOUND);
		}
	  deviceRepository.editDeviceRepository(name, id);
	 //Device deviceSave = deviceRepository.save(device.get());
	 return new ResponseEntity<Object>("Da sua thanh cong", HttpStatus.OK);
	}
	
	
	//them device theo id_catalog.
	@PostMapping(path="/{id}/addDevice")	
	public  ResponseEntity<Object> addDevice(@PathVariable("id") Long id, @Valid @RequestBody DeviceDTO deviceDTO,BindingResult result) {
		
		
		Optional<DeviceCatalog> deviceCatalog = deviceCatalogRepository.findById(id);
	      if(!deviceCatalog.isPresent()) {
	    	  return new ResponseEntity<Object>("khong ton tai deviceCatalog, nen khong the cong 1 device", HttpStatus.NOT_FOUND);
	      }
		
		DeviceDTOResponse deviceDTOResponse = new DeviceDTOResponse();		  
	      if(result.hasErrors()){          
	          Map<String, String> errors = result.getFieldErrors().stream()
	                .collect(
	                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
	                  );
	          
	          deviceDTOResponse.setValidated(false);
	          deviceDTOResponse.setErrorMessages(errors); 
	          return new ResponseEntity<Object>(deviceDTOResponse, HttpStatus.BAD_REQUEST);
	      }
	      
		
		Device device = new Device();
		device.setName(deviceDTO.getName());
		device.setQuantity(deviceDTO.getQuantity());
		device.setPrice(deviceDTO.getPrice());
		device.setDescription(deviceDTO.getDescription());
		DeviceCatalog deviceCatalog1 = new DeviceCatalog(id);
		device.setDeviceCatalog(deviceCatalog1);
		deviceRepository.save(device);
		
	    return new ResponseEntity<Object>("Da them thanh cong", HttpStatus.OK);
	}
	
	//sua device boi device_id.
		@PutMapping(path="/{idDevice}/editDevice")	
		public ResponseEntity<Object> editDevice(@PathVariable("idDevice") Long idDevice, @Valid @RequestBody Device editDeviceDTO,BindingResult result) {
			
			EditDeviceDTOResponse editDeviceDTOResponse = new EditDeviceDTOResponse();		  
		      if(result.hasErrors()){          
		          Map<String, String> errors = result.getFieldErrors().stream()
		                .collect(
		                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
		                  );	        
		          
		          editDeviceDTOResponse.setValidated(false);
		          editDeviceDTOResponse.setErrorMessages(errors); 
		          return new ResponseEntity<Object>(editDeviceDTOResponse, HttpStatus.BAD_REQUEST);
		      }
				
			Optional<Device> device1 = deviceRepository.findById(idDevice);
			//DeviceCatalog deviceCatalog = new DeviceCatalog();
			//deviceCatalog.setId(device.get().getDeviceCatalog().getId());
			if(!device1.isPresent()) {
				return new ResponseEntity<Object>("Khong tim thay id nay", HttpStatus.NOT_FOUND);
			}
			Device device = device1.get();
			device.setName(editDeviceDTO.getName());
			device.setQuantity(editDeviceDTO.getQuantity());
			device.setPrice(editDeviceDTO.getPrice());
			device.setDescription(editDeviceDTO.getDescription());
			deviceRepository.save(device);
			return new ResponseEntity<Object>("Da sua thanh cong", HttpStatus.OK);
		}
		
		//xoa device boi device_id.
		@DeleteMapping(path="/{idDevice}/deleteDevice")
		public ResponseEntity<Object> deleteDevice(@PathVariable("idDevice") Long idDevice) {
			 Boolean test = deviceService.testExistIdDevice(idDevice);
			 if(test == false) {
				 return new ResponseEntity<Object>("Khong tim thay id nay", HttpStatus.NOT_FOUND);
			 }
		    deviceRepository.deleteById(idDevice);
		    return new ResponseEntity<Object>("Da xoa thanh cong", HttpStatus.OK);
		}
		
		//xoa tat ca cac thiet bi.
		@DeleteMapping("/deleteAllDevice")
		public ResponseEntity<Object> deleteAllDevice() {
			deviceRepository.deleteAll();
			return new ResponseEntity<Object>("Da xoa tat ca cac thiet bi", HttpStatus.OK);
		}
		
		
		//xuat danh sach cac catalog 
		@GetMapping(path = "/allCatalog")
		public ResponseEntity<Object> allCatalog() {
			List<DeviceCatalog> list = deviceCatalogRepository.findAll();
			if(list.size() == 0) {
				return new ResponseEntity<Object>("danh sach rong", HttpStatus.OK);
			}
			return new ResponseEntity<Object>(list, HttpStatus.OK);		
		}
		
		//them 1 catalog.
		@PostMapping(path="/addCatalog")	
		public ResponseEntity<Object> addCatalog(@Valid @RequestBody CatalogDTO catalogDTO,BindingResult result) {
			
			CatalogDTOResponse catalogDTOResponse = new CatalogDTOResponse();		  
		      if(result.hasErrors()){          
		          Map<String, String> errors = result.getFieldErrors().stream()
		                .collect(
		                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
		                  );	        
		          catalogDTOResponse.setValidated(false);
		          catalogDTOResponse.setErrorMessages(errors); 
		          return new ResponseEntity<Object>(catalogDTOResponse, HttpStatus.BAD_REQUEST);
		      }
			
			DeviceCatalog deviceCatalog = new DeviceCatalog();
			deviceCatalog.setDescription(catalogDTO.getName());
			deviceCatalog.setName(catalogDTO.getName());
			deviceCatalogRepository.save(deviceCatalog);
			return new ResponseEntity<Object>("Da them catalog thanh cong", HttpStatus.OK);
		}
		
		
		//sua catalog
		@PutMapping(path="/{idCatalog}/editCatalog")	
		public ResponseEntity<Object> editCatalog(@PathVariable("idCatalog") Long idCatalog, @Valid @RequestBody CatalogDTO catalogDTO, BindingResult result) {
			
			EditCatalogDTOResponse editCatalogDTOResponse = new EditCatalogDTOResponse();		  
		      if(result.hasErrors()){          
		          Map<String, String> errors = result.getFieldErrors().stream()
		                .collect(
		                      Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)	                     
		                  );	        
		          editCatalogDTOResponse.setValidated(false);
		          editCatalogDTOResponse.setErrorMessages(errors); 
		          return new ResponseEntity<Object>(editCatalogDTOResponse, HttpStatus.BAD_REQUEST);
		      }
			
			Optional<DeviceCatalog> deviceCatalog = deviceCatalogRepository.findById(idCatalog);
			if(!deviceCatalog.isPresent()) {
				return new ResponseEntity<Object>("Khong tim thay id", HttpStatus.NOT_FOUND);
			}
			
			deviceCatalog.get().setDescription(catalogDTO.getDescription());
			deviceCatalog.get().setName(catalogDTO.getName());
			deviceCatalogRepository.save(deviceCatalog.get());
			return new ResponseEntity<Object>("Da sua catalog thanh cong", HttpStatus.OK);
		}
		
		//xoa catalog theo id
		@DeleteMapping(path="/{idCatalog}/deleteCatalog")
		public ResponseEntity<Object> deleteCatalog(@PathVariable("idCatalog") Long idCatalog) {
			 Boolean test = deviceCatalogService.testExistIdCatalog(idCatalog);
			 if(test == false) {
				 return new ResponseEntity<Object>("Khong tim thay id nay", HttpStatus.NOT_FOUND);
			 }
		    deviceCatalogRepository.deleteById(idCatalog);
		    return new ResponseEntity<Object>("Da xoa thanh cong", HttpStatus.OK);
		}
		@DeleteMapping("/deleteAllCatalog")
		public ResponseEntity<Object> deleteAllCatalog() {
			 deviceCatalogRepository.deleteAll();
			 return new ResponseEntity<Object>("Da xoa thanh cong tat ca cac catalog", HttpStatus.OK);
		}
		
		//tim kiem catalog theo ten(filter)
	    @GetMapping("/filterCatalog")	
		public ResponseEntity<Object> filterCatalog(@RequestParam("search") String search) {
			System.out.println(search);
			if(search.isEmpty()) {
				return new ResponseEntity<Object>(deviceCatalogRepository.findAll(), HttpStatus.OK);
			}
			DeviceCatalog list = deviceCatalogService.filterCatalog(search);
			if(list.equals(null)) {
				return new ResponseEntity<Object>("Khong co catalog nao", HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	    
	    
	    public DeviceResponse ConverttoDeviceResponse(Device d){
	    	DeviceResponse ds = new DeviceResponse();
			ds.setId(d.getId());
			ds.setName(d.getName());
			ds.setPrice(d.getPrice());
			ds.setQuantity(d.getQuantity());
			ds.setDescription(d.getDescription());
			ds.setCatalog(d.getDeviceCatalog().getName());
			return ds;
		}
}
