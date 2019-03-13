package device.management.demo.repository.extenstion;

import java.util.List;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.response.RequestResponse;

public interface RequestRepositoryExtenstion {
	List<RequestResponse> getRequest(QueryParam<RequestResponse> page);
}
