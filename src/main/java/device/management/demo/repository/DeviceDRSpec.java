package device.management.demo.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.response.EmpDeviceResponse;

public class DeviceDRSpec {

	public static Specification<Device_Deliver_Receive> search(EmpDeviceResponse emp) {
	    return new Specification<Device_Deliver_Receive>() {
		@Override
		public Predicate toPredicate(Root<Device_Deliver_Receive> root, CriteriaQuery<?> query,
				CriteriaBuilder criteriaBuilder) {
			// TODO Auto-generated method stub
			return null;
		}
	    };
	  }
}
