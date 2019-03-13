package device.management.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import device.management.demo.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long>{
	
	/**
	 * @summary find device via name
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  String devicename
	 * @return Optional<Device>
	 **/
	Optional<Device> findByName(String devicename);
	
	/**
	 * @summary find device via name
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  String devicename
	 * @return Optional<Device>
	 **/
	
	@Transactional
	@Modifying
	@Query(value = "update device set name=?1 where id=?2", nativeQuery = true)
	int editDeviceRepository(String name, Long id);
	Device save(Device device);
	
	@Transactional
	@Modifying
	@Query(value = "update device set name=?1 , quantity=?2, price=?3 where id=?4", nativeQuery = true)
	int update (String name, Long quantity, Double price, String description, Long id);
	

	
//	List<Device> findByName(String search);
}
