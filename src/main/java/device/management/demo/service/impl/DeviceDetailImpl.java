package device.management.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import device.management.demo.entity.Device;
import device.management.demo.entity.DeviceCatalog;
import device.management.demo.entity.DeviceDetail;
import device.management.demo.entity.dto.DetailDTO;
import device.management.demo.entity.response.DetailResponse;
import device.management.demo.entity.response.DeviceQuantityResponse;
import device.management.demo.repository.DeviceCatalogRepository;
import device.management.demo.repository.DeviceDetailRepository;
import device.management.demo.repository.DeviceRepository;
import device.management.demo.service.DeviceDetailService;
import device.management.demo.util.detailConst;

@Service
public class DeviceDetailImpl implements DeviceDetailService {

	@Autowired
	DeviceDetailRepository deviceDetailRepository;

	@Autowired
	DeviceCatalogRepository deviceCatalogRepository;

	@Autowired
	DeviceRepository deviceRepository;

	/**
	 * @summary return list devicedetails
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Device
	 * @return List<DeviceDetail>
	 **/
	@Override
	public List<DetailResponse> getDeviceDetails(Device device) {
		List<DeviceDetail> ldd = deviceDetailRepository.findByDevice(device);
		List<DetailResponse> ldr = new ArrayList<>();
		for (DeviceDetail dd : ldd) {
			DetailResponse dr = ConverttoDetailRes(dd);
			ldr.add(dr);
		}
		return ldr; 
	}

	/**
	 * @summary edit devicedetails
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  deviceDetail
	 * @return DeviceDetail
	 **/
	@Override
	public DeviceDetail editDeviceDetails(DetailDTO d) {
		DeviceDetail Detailobj = deviceDetailRepository.findById(d.getId()).get();
		Detailobj.setDescriptionDeviceDetail(d.getDecription());
		Detailobj.setProductId(d.getProductid());
		Detailobj.setStatus(d.getStatus());
//		Detailobj.setUpdateDate(d.getUpdateDate());
		return deviceDetailRepository.save(Detailobj);
	}
	
	/**
	 * @summary del devicedetails via id
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  long id)
	 * @return true, false
	 **/
	@Override
	public Boolean DelDevDetailById(long id) {
		deviceDetailRepository.deleteById(id);
		return true;
	}
	
	/**
	 * @summary filter devicedetails not used and normal for allocation
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  working, status, name, catalog
	 * @return List<DetailResponse>
	 **/
	@Override
	public List<DetailResponse> filterDetails(long status, String key) {
		List<DeviceDetail> detail = deviceDetailRepository.findByStatusAndDeviceNameContainingOrStatusAndDeviceDeviceCatalogNameContaining(status, key, status, key);
		List<DetailResponse> detailRes = new ArrayList<>();
		for (DeviceDetail d : detail) {
			DetailResponse res = ConverttoDetailRes(d);
			detailRes.add(res);
			System.out.println(res);
		}
		return detailRes;
	}
	

	/**
	 * @summary add new device detail
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  DetailDTO d
	 * @return DetailObj
	 **/
	@Override
	public DeviceDetail addDeviceDetail(DetailDTO d) {
		Optional<DeviceCatalog> catalogObj = deviceCatalogRepository.findByName(d.getCatalogname());
		DeviceCatalog catalog = new DeviceCatalog(d.getCatalogname());
		if (catalogObj.isPresent()) {
			catalog = catalogObj.get();
		} else {
			catalog = deviceCatalogRepository.save(catalog);
		}
		Optional<Device> deviceObj = deviceRepository.findByName(d.getDevicename());
		
		Device device = new Device(catalog, d.getDevicename(), 1L, d.getPrice(), d.getDecription());
		if (deviceObj.isPresent()) {
			device = deviceObj.get();
			device.setQuantity(device.getQuantity()+1);
		} else {
			device = deviceRepository.save(device);
		}

		DeviceDetail deviceDetail = new DeviceDetail(device, d.getProductid(), detailConst.NOTUSED, d.getUpdatedate());
		return deviceDetailRepository.save(deviceDetail);
	}

	/**
	 * @summary edit field working true via id
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  Long id
	 * @return DeviceDetail
	 **/
	@Override
	public DeviceDetail setWorking(Long id) {
		DeviceDetail Detailobj = deviceDetailRepository.findById(id).get();
		Detailobj.setStatus(detailConst.WORKING);
		return deviceDetailRepository.save(Detailobj);
	}
	
	/**
	 * @summary convert form DeviceDetail to DetailResponse
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param  DeviceDetail
	 * @return DetailResponse
	 **/
	public DetailResponse ConverttoDetailRes(DeviceDetail d){
		DetailResponse res = new DetailResponse();
		res.setId(d.getId());
		res.setDecription(d.getDescriptionDeviceDetail());
		res.setCatalogname(d.getDevice().getDeviceCatalog().getName());
		res.setDevicename(d.getDevice().getName());
		res.setPrice(d.getDevice().getPrice());
		res.setProductid(d.getProductId());
		res.setUpdatedate(d.getUpdateDate());
		res.setStatus(d.getStatus());
		res.setIconCatalog(d.getDevice().getDeviceCatalog().getDescription());
		System.out.println(res.getIconCatalog());
		return res;
	}

	@Override
	public List<DeviceQuantityResponse> getQuantityDevice() {
		Long totalAll = 0L;
		Long wokingAll = 0L;
		Long notUseAll = 0L;
		Long errorAll = 0L;
		List<DeviceCatalog> listName = deviceCatalogRepository.findAll();
		List<DeviceQuantityResponse> ldqr = new ArrayList<>();
		for (DeviceCatalog dc : listName) {	

			Long woking = deviceDetailRepository.countByDeviceDeviceCatalogNameAndStatus(dc.getName()
					, detailConst.WORKING);
			
			Long notUse = deviceDetailRepository.countByDeviceDeviceCatalogNameAndStatus(dc.getName(), detailConst.NOTUSED);
			
			Long error = deviceDetailRepository.countByDeviceDeviceCatalogNameAndStatus(dc.getName(), detailConst.ERROR);
			
			Long total = error + woking + notUse;
			
			DeviceQuantityResponse dqr = new DeviceQuantityResponse(dc.getName(), dc.getDescription(), total, woking, error, notUse);
			ldqr.add(dqr);
			totalAll = totalAll + total;
			wokingAll = wokingAll + woking;
			notUseAll = notUseAll + notUse;
			errorAll =  errorAll + error;
		
		}
		String allDevice = "All Devices";
		String catalogIcon = "icon-devices_other";
		DeviceQuantityResponse dqr = new DeviceQuantityResponse(allDevice, catalogIcon, totalAll, wokingAll, errorAll, notUseAll);
		ldqr.add(dqr);
		return ldqr;
	}
	







}
