package device.management.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Device;
import device.management.demo.repository.DeviceRepository;
import device.management.demo.service.DeviceService;

@Service
public class DeviceImpl implements DeviceService{
	@Autowired
	DeviceRepository deviceRepository;
	

	@Override
	public Device getDeviceById(long id) {
		// TODO Auto-generated method stub
		return deviceRepository.findById(id).get();
	}
	
	//van
	@Override
	public int editDevice(String name, Long id) {
		// TODO Auto-generated method stub
		int device = deviceRepository.editDeviceRepository(name, id);
		return device;
	}

	@Override
	public Boolean updateDevice(Long id, String name, Long quantity, Double price, String description) {
		Optional<Device> device = deviceRepository.findById(id);
		if(!device.isPresent()) {
			return false;
		}
		int updateDevice = deviceRepository.update(name, quantity, price, description, id);
		if(updateDevice != 1) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean testExistIdDevice(Long idDevice) {
		// TODO Auto-generated method stub
		Optional<Device> test = deviceRepository.findById(idDevice);
		if(!test.isPresent()) {
			return false;
		}		
		return true;
	}

	@Override
	public Device filterDevice(String search) {
		// TODO Auto-generated method stub
		Optional<Device> list = deviceRepository.findByName(search);
		return list.get();
	}
}
