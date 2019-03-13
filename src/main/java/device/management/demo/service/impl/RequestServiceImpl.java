package device.management.demo.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.repository.RequestRepository;
import device.management.demo.repository.UserRepsository;
import device.management.demo.service.RequestService;
import device.management.demo.util.requestconst;
import freemarker.core.ParseException;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	UserRepsository userRepsository;
	
	/**
//	* @summary add new Request
	* @date sep 13, 2018
	* @author Nam.Nguyen2
	* @param request
	* @return Request
   	**/
	@Override
	public RequestResponse createRequest(RequestResponse request) {
		Optional<User> optionalUser = userRepsository.findByEmail(request.getEmail());
		if (!optionalUser.isPresent()) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
//		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
//		Request addrequest = new Request(request.getContent(), request.getType(), request.getStatus(), date, optionalUser.get());
//		Request r = requestRepository.save(addrequest);
//		RequestResponse rr = ConverToRequestRes(r);
		return null;
	}
	
	/**
   	* @summary return list requests of user
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param user
   	* @return listrequest
   	**/	
	@Override
	public List<Request> listRequestbyuser(User user) {
		// TODO Auto-generated method stub
		return requestRepository.findTop10ByUserOrderByIdDesc(user);
	}
	
	/**
   	* @summary return list requests pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return listRequestr
   	**/	
	@Override
	public List<RequestResponse> listRequest(QueryParam<RequestResponse> page) {
//		List<RequestResponse> r = requestRepository.getRequest(page);
		return null;
	}
	
	/**
   	* @summary return requests not pending
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return listRequestr
   	**/	
	@Override
	public List<RequestResponse> listOldRequest(Pageable page) {
		Page<Request> p = requestRepository.findByRequestStatusNotLike(null, page);
		List<Request> lr = p.getContent();
		if (lr.size() == 0) {
			return null;
		}
		List<RequestResponse> listRequestr = new ArrayList<>();
		for (Request r : lr) {
//			RequestResponse rr = ConverToRequestRes(r);
//			listRequestr.add(rr);
		}
		return listRequestr;
	}
	
	/**
   	* @summary edit status request
   	* @date sep 12, 2018
   	* @author Nam.Nguyen2
   	* @param 
   	* @return RequestResponse
   	**/
	@Override
	public RequestResponse editRequest(RequestResponse request) {
		Date date = new Date();
//		Request editrequest = requestRepository.findById(request.getId()).get();
//		editrequest.setStatus(request.getStatus());
//		editrequest.setUpdateDate(date);
//		editrequest.setContentReply(request.getContentReply());
//		editrequest = requestRepository.save(editrequest);
//		RequestResponse rr = ConverToRequestRes(editrequest);
		return null;
	}
	
//	public RequestResponse ConverToRequestRes(Request r) {
//		RequestResponse rr = new RequestResponse();
//		rr.setContent(r.getContent());
//		rr.setEmail(r.getUser().getEmail());
//		rr.setFullname(r.getUser().getEmployee().getEmployeeName());
//		rr.setEmpId(r.getUser().getEmployee().getId());
//		rr.setId(r.getId());
//		rr.setStatus(r.getStatus());
//		rr.setTeam(r.getUser().getEmployee().getTeam());
//		rr.setType(r.getType());
////		rr.setUpdatedate(r.getUpdateDate());
//		rr.setAvatar(r.getUser().getEmployee().getAvatar());
//		return rr;
//	}
	
	
	@Override
	public Request editRequest(Request request) {
		// TODO Auto-generated method stub
		return requestRepository.save(request);
	}
	@Override
	public Request getRequestbyid(long id) {
		// TODO Auto-generated method stub
		return requestRepository.findById(id).get();
	}

	@Override
	public List<Request> filterRequestByUser(User user) {
		List<Request> list = new ArrayList<>();
		System.out.println(list);
		return list;
	}

	@Override
	public List<RequestResponse> listRequestToday() {	
		   LocalDate now = LocalDate.now();
		   Date dateStart = null;
		   Date dateEnd = null;
		
		try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(now+" 00:00:00");
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(now+" 23:59:59");
			
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		   System.out.println("start"+dateStart);
		   System.out.println("end"+dateEnd);
//		   List<Request> lr = requestRepository.findByStatusAndUpdateDateBetween(requestconst.Pending, dateStart, dateEnd);
//		   System.out.println("end"+lr);
//		   if (lr.size() == 0) {
//				return null;
//			}
//		   List<RequestResponse> listRequestr = new ArrayList<>();
//			for (Request r : lr) {
////				RequestResponse rr = ConverToRequestRes(r);
////				listRequestr.add(rr);
//			}
		return null;
	}

	@Override
	public int getPagePending(Pageable page) {
		Page<Request> p = requestRepository.findByRequestStatus(null, page);
		return p.getTotalPages();
	}

	@Override
	public int getPageHistory(Pageable page) {
		Page<Request> p = requestRepository.findByRequestStatusNotLike(null, page);
		return p.getTotalPages();
	}


}
