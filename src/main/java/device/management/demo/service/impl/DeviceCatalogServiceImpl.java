package device.management.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.DeviceCatalog;
import device.management.demo.repository.DeviceCatalogRepository;
import device.management.demo.service.DeviceCatalogService;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService{
     
	@Autowired
	private DeviceCatalogRepository deviceCatalogRepository;
	@Override
	public Boolean testExistIdCatalog(Long idCatalog) {
		Optional<DeviceCatalog> test = deviceCatalogRepository.findById(idCatalog);
		if(!test.isPresent()) {
			return false;
		}
		return true;
	}
	@Override
	public DeviceCatalog filterCatalog(String search) {
		 Optional<DeviceCatalog> list = deviceCatalogRepository.findByName(search);
		return list.get();
	}

}
