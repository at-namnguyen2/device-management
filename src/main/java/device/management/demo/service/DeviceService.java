package device.management.demo.service;


import device.management.demo.entity.Device;

public interface DeviceService {
	Device getDeviceById(long id);
	
	//van
	int editDevice(String name, Long id);
	Boolean updateDevice(Long id, String name, Long quantity, Double price, String description);
	Boolean testExistIdDevice(Long idDevice);
	Device filterDevice(String search);
}
