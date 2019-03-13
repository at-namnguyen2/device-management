package device.management.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import device.management.demo.entity.DeviceCatalog;
import java.lang.String;
import java.util.List;
import java.util.Optional;

public interface DeviceCatalogRepository extends JpaRepository<DeviceCatalog, Long>{
	
	/**
	 * @summary find catalog via name
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Optional<DeviceCatalog>
	 * @return String name
	 **/
	Optional<DeviceCatalog> findByName(String name);
	
}
