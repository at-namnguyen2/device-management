package device.management.demo.service;

import java.util.List;

import device.management.demo.entity.DeviceCatalog;

public interface DeviceCatalogService {

	//van
	Boolean testExistIdCatalog(Long idCatalog);

	DeviceCatalog filterCatalog(String search);
}
