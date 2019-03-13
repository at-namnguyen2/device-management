package device.management.demo.repository.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.Request;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.repository.extenstion.RequestRepositoryExtenstion;
import device.management.demo.util.DateTimeUtils;
import device.management.demo.util.ParamUtils;
import device.management.demo.util.QueryUtils;
import device.management.demo.util.StringUtils;
import device.management.demo.util.requestconst;

public class RequestRepositoryImpl implements RequestRepositoryExtenstion {

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<RequestResponse> getRequest(QueryParam<RequestResponse> page) {
		String sql = "SELECT r FROM Request r";
		String countSql = "SELECT count(1) FROM Request r";
		String condition = " WHERE r.requestType.type = 'REQUEST_TYPE' AND r.requestStatus = 'REQUEST_STATUS' ";
		String fcondition = filterCondition(page.getQuery());
		if(StringUtils.isValidString(fcondition)) {
			sql = sql.concat(condition).concat(fcondition);
			countSql = countSql.concat(condition).concat(fcondition);
		}
		Query query = entityManager.createQuery(sql.concat(condition));
		Query countQuery = entityManager.createQuery(countSql.concat(condition));
		Long totalRows = (Long) countQuery.getSingleResult();
        page.getPagingItem().setOutRowsNumber(totalRows);
        query.setFirstResult((page.getPagingItem().getPageIndex() - 1 ) * page.getPagingItem().getPageSize());
        query.setMaxResults(page.getPagingItem().getPageSize());
        List<Request> result = query.getResultList();
        List<RequestResponse> data = new ArrayList<>();
        RequestResponse emp;
        for (Request row : result) {
        	emp = new RequestResponse();
        	emp.setAvatar(row.getUser().getEmployee().getAvatar());
        	emp.setContent(row.getContent());
        	emp.setContentReply(row.getContentReply());
        	emp.setEmail(row.getUser().getEmail());
        	emp.setEmpId(row.getUser().getEmployee().getId());
        	emp.setId(row.getId());
        	emp.setFullname(row.getUser().getEmployee().getEmployeeName());
        	emp.setTeam(row.getUser().getEmployee().getTeam());
//        	emp.setStatus(row.getRequestStatus());
//        	emp.setType(row.getRequestType());
        	emp.setUpdatedate(DateTimeUtils.convertDateToString(row.getUpdatedDate(), ParamUtils.ddMMyyyy));
        	data.add(emp);
        }
		return data;
	}
	
	private String filterCondition(RequestResponse requestResponse) {
		List<String> fcondition = new ArrayList<>();
			if(requestResponse.getId() != null) {
				fcondition.add("(r.id = "+requestResponse.getId()+")");
		}
			if(StringUtils.isValidString(requestResponse.getFullname())) {
				fcondition.add(QueryUtils.like("r.user.employee.employeeName",requestResponse.getFullname()));
		}
			if(StringUtils.isValidString(requestResponse.getEmail())) {
				fcondition.add(QueryUtils.like("r.user.employee.user.email",requestResponse.getEmail()));
		}
			if(StringUtils.isValidString(requestResponse.getTeam())) {
				fcondition.add(QueryUtils.like("r.user.employee.team",requestResponse.getTeam()));
		}
			if(StringUtils.isValidString(requestResponse.getAvatar())) {
				fcondition.add(QueryUtils.like("r.user.employee.avatar",requestResponse.getAvatar()));
		}
			if(StringUtils.isValidString(requestResponse.getUpdatedate())) {
				Date date;
				try {
					date = DateTimeUtils.convertStringToTime(requestResponse.getUpdatedate(), ParamUtils.yyyyMMdd);
					fcondition.add(QueryUtils.like("r.updatedDate", QueryUtils.formatDate(date)));
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
			if(StringUtils.isValidString(requestResponse.getStatus())) {
//				fcondition.add(QueryUtils.like("r.status",requestResponse.getStatus()));
		} else {
			fcondition.add(QueryUtils.notLike("r.status",requestconst.Pending));

		}
		return String.join(" AND ", fcondition);
	}

}
