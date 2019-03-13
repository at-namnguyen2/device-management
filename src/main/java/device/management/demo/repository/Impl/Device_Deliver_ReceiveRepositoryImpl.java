package device.management.demo.repository.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.Device_Deliver_Receive;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.repository.extenstion.Device_Deliver_ReceiverRepositoryExtenstion;
import device.management.demo.util.DateTimeUtils;
import device.management.demo.util.ParamUtils;
import device.management.demo.util.QueryUtils;
import device.management.demo.util.StringUtils;

public class Device_Deliver_ReceiveRepositoryImpl implements Device_Deliver_ReceiverRepositoryExtenstion {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<EmpDeviceResponse> getDevAllcation(QueryParam<EmpDeviceResponse> page) {
		String sql = "SELECT ddr FROM Device_Deliver_Receive ddr";
		String countSql = "SELECT count(1) FROM Device_Deliver_Receive ddr";
		String condition = " WHERE ";
		String fcondition = filterCondition(page.getQuery());
		if(StringUtils.isValidString(fcondition)) {
			sql = sql.concat(condition).concat(fcondition);
			countSql = countSql.concat(condition).concat(fcondition);
		}
		Query query = entityManager.createQuery(sql);
		Query countQuery = entityManager.createQuery(countSql);
		Long totalRows = (Long) countQuery.getSingleResult();
        page.getPagingItem().setOutRowsNumber(totalRows);
        query.setFirstResult((page.getPagingItem().getPageIndex() - 1 ) * page.getPagingItem().getPageSize());
        query.setMaxResults(page.getPagingItem().getPageSize());
        List<Device_Deliver_Receive> result = query.getResultList();
        List<EmpDeviceResponse> data = new ArrayList<>();
        EmpDeviceResponse emp;
        for (Device_Deliver_Receive row : result) {
        	emp = new EmpDeviceResponse();
        	emp.setAvatar(row.getEmployee().getAvatar());
        	emp.setDateDeliverReceive(DateTimeUtils.convertDateToString(row.getDateDeliverReceive(), ParamUtils.ddMMyyyy));
        	emp.setDateReturn(DateTimeUtils.convertDateToString(row.getDateReturn(), ParamUtils.ddMMyyyy));
        	emp.setDeviceName(row.getDeviceDetail().getDevice().getName());
        	emp.setEmail(row.getEmployee().getUser().getEmail());
        	emp.setEmployeeName(row.getEmployee().getEmployeeName());
        	emp.setIconCatalog(row.getDeviceDetail().getDevice().getDeviceCatalog().getDescription());
        	emp.setId(row.getId());
        	emp.setProductId(row.getDeviceDetail().getProductId());
        	emp.setTeam(row.getEmployee().getTeam());
        	data.add(emp);
        }
		return data;
	}
	
	private String filterCondition(EmpDeviceResponse empDeviceResponse) {
		List<String> fcondition = new ArrayList<>();
			if(empDeviceResponse.getId() != null) {
				fcondition.add("(ddr.id = "+empDeviceResponse.getId()+")");
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getEmployeeName())) {
				fcondition.add(QueryUtils.like("ddr.employee.employeeName",empDeviceResponse.getEmployeeName()));
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getEmail())) {
				fcondition.add(QueryUtils.like("ddr.employee.user.email",empDeviceResponse.getEmail()));
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getTeam())) {
				fcondition.add(QueryUtils.like("ddr.employee.team",empDeviceResponse.getTeam()));
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getAvatar())) {
				fcondition.add(QueryUtils.like("ddr.employee.avatar",empDeviceResponse.getAvatar()));
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getDeviceName())) {
				fcondition.add(QueryUtils.like("ddr.deviceDetail.device.name",empDeviceResponse.getDeviceName()));
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getProductId())) {
				fcondition.add(QueryUtils.like("ddr.deviceDetail.productId",empDeviceResponse.getProductId()));
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getIconCatalog())) {
				fcondition.add(QueryUtils.like("ddr.deviceDetail.device.deviceCatalog.description",empDeviceResponse.getProductId()));
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getDateDeliverReceive())) {
				Date date;
				try {
					date = DateTimeUtils.convertStringToTime(empDeviceResponse.getDateDeliverReceive(), ParamUtils.yyyyMMdd);
					fcondition.add(QueryUtils.like("ddr.dateDeliverReceive", QueryUtils.formatDate(date)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
			if(StringUtils.isValidString(empDeviceResponse.getDateReturn())) {
				Date date;
				try {
					date = DateTimeUtils.convertStringToTime(empDeviceResponse.getDateReturn(), ParamUtils.yyyyMMdd);
					fcondition.add(QueryUtils.like("ddr.dateReturn", QueryUtils.formatDate(date)));
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
			if(empDeviceResponse.isAllocationStatus()) {
				fcondition.add("ddr.dateReturn is NULL");
			} else {
				fcondition.add("ddr.dateReturn is NOT NULL");

			}	
		return String.join(" AND ", fcondition);
	}

}
