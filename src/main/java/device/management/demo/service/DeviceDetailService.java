package device.management.demo.service;

import java.util.List;

import device.management.demo.entity.Device;
import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.dto.DetailDTO;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.entity.response.DeviceQuantityResponse;

public interface DeviceDetailService {
	
	/**
	 * @summary return list devicedetails
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device
	 * @return List<DeviceDetail>
	 **/
	List<DetailResponse> getDeviceDetails(Device device);
	
	/**
	 * @summary edit devicedetails
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  deviceDetail
	 * @return DeviceDetail
	 **/
	DeviceDetail editDeviceDetails(DetailDTO deviceDetail);
	
	/**
	 * @summary del devicedetails via id
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  long id)
	 * @return true, false
	 **/
	Boolean DelDevDetailById(long id);
	
	/**
	 * @summary filter devicedetails not used and normal for allocation
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  working, status, name, catalog
	 * @return List<DetailResponse>
	 **/
	List<DetailResponse> filterDetails(long status, String key);
	
	/**
	 * @summary add new device detail
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  DetailDTO d
	 * @return DetailObj
	 **/
	DeviceDetail addDeviceDetail(DetailDTO detailDTO);
	
	/**
	 * @summary edit field working true via id
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Long id
	 * @return DeviceDetail
	 **/
	DeviceDetail setWorking(Long id);

	List<DeviceQuantityResponse> getQuantityDevice();



}
