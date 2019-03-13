package device.management.demo.repository.extenstion;

import java.util.List;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.response.EmpDeviceResponse;

public interface Device_Deliver_ReceiverRepositoryExtenstion {
	List<EmpDeviceResponse> getDevAllcation(QueryParam<EmpDeviceResponse> page);
}
