package device.management.demo.api;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import device.management.demo.domain.QueryParam;
import device.management.demo.entity.Request;
import device.management.demo.entity.User;
import device.management.demo.entity.response.EmpDeviceResponse;
import device.management.demo.entity.response.RequestResponse;
import device.management.demo.entity.response.ResponseData;
import device.management.demo.service.RequestService;
import device.management.demo.service.UserService;
import device.management.demo.util.requestconst;

@RestController
public class RequestApi {

	private static final Logger logger = LoggerFactory.getLogger(Device_Deliver_ReceiveApi.class);
	@Autowired
	UserService userService;
	@Autowired
	RequestService requestService;

	/**
	 * @summary add new request from user
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param request
	 * @return Request
	 **/
	@PostMapping(path = "/createrequest")
	public ResponseEntity<Object> CreateRequest(@RequestBody RequestResponse request, Principal p) {
		System.out.println("get request");
		String principalemail = p.getName();
		request.setEmail(principalemail);
//		request.setStatus(requestconst.Pending);
		return new ResponseEntity<>(requestService.createRequest(request), HttpStatus.OK);
	}

	/**
	 * @summary show requests of user
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param user
	 * @return listrequest
	 **/
	@GetMapping(path = "/myrequest")
	public ResponseEntity<Object> ViewRequestUser(Principal p) {
		String principalemail = p.getName();
		User user = userService.getUserByEmail(principalemail);
		List<Request> listRequest = requestService.listRequestbyuser(user);
		if (listRequest.size() == 0) {
			return new ResponseEntity<>(listRequest, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listRequest, HttpStatus.OK);
	}

	/**
	 * @summary show requests pending
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param
	 * @return listRequestr
	 **/
	@PostMapping(path = "/list-request")
	public ResponseEntity<Object> listRequest(@RequestBody QueryParam<RequestResponse> page) {
		ResponseData res = new ResponseData();
		try {
			System.out.println("pending"+page.getQuery().getStatus());
			List<RequestResponse> data = requestService.listRequest(page);
			res.setTotalPage(page.getPagingItem().getNumberOfPage());
			res.setTotalRows(page.getPagingItem().getOutRowsNumber());
			Long rowNumber = page.getPagingItem().getOutRowsNumber();
			int pageSize = page.getPagingItem().getPageSize();
			res.setTotalPage((rowNumber % pageSize==0)? rowNumber/pageSize : rowNumber/pageSize+1);
			res.setData(data);
		} catch (Exception ex) {
			res.setError(ex);
			res.setMessage(ex.getMessage());
			res.setSuccess(false);
			logger.error(ex.getMessage());
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * @summary show requests history
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param
	 * @return listRequestr
	 **/
	@GetMapping(path = "/requesthistory")
	public ResponseEntity<Object> viewOldRequest(Pageable page) {
		List<RequestResponse> listRequestr = requestService.listOldRequest(page);
		if (listRequestr.equals(null)) {
			return new ResponseEntity<>(listRequestr, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listRequestr, HttpStatus.OK);
	}

	/**
	 * @summary resolve request
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param
	 * @return listRequestr
	 **/
	@PutMapping(path = "/resolve")
	public ResponseEntity<Object> ResolveRequest(@RequestBody RequestResponse request) {
		System.out.println("show:" + request);
		if (request.getStatus().equals(requestconst.Approved)) {
			if (request.getType().equals(requestconst.Update_Info)) {
				userService.updateUser(request);

			}
		}
		requestService.editRequest(request);
//		if (!request.getStatus().equals(requestconst.Reply_Pending)) {
//			requestService.editRequest(request);
//		}
//		Date date = new Date();
//		RequestResponse rr = new RequestResponse();
//		rr.setContent(request.getContentReply());
//		rr.setUpdatedate(date);
//		rr.setType(request.getType());
//		rr.setStatus(requestconst.Reply);
//		rr.setEmail(request.getEmail());
//		rr = requestService.createRequest(rr);
		
//			else {
//
//			}
//		} else if (request.getStatus().equals(requestconst.Reply)) {
//			return new ResponseEntity<>(requestService.createRequest(request), HttpStatus.OK);
//		} else {
//			userService.updateUser(request);
//			RequestResponse rr = requestService.editRequest(request);
//			return new ResponseEntity<>(rr, HttpStatus.OK);
//		}
		return new ResponseEntity<>("ok2", HttpStatus.OK);
	}
	
	/**
	 * @summary show requests allocation pending sof user 
	 * @date sep 12, 2018
	 * @author Nam.Nguyen2
	 * @param user
	 * @return listrequest
	 **/
	@PostMapping(path = "/filterrequest")
	public ResponseEntity<Object> filterRequest(@RequestBody User user) {
		List<Request> listRequest = requestService.filterRequestByUser(user);
		System.out.println("test"+listRequest);
		if (listRequest.size() == 0) {
			return new ResponseEntity<>(listRequest, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listRequest, HttpStatus.OK);
	}

	@GetMapping(path = "/listrequesttoday")
	public ResponseEntity<Object> listRequestToday(){
		List<RequestResponse> listRequestr = requestService.listRequestToday();
		System.out.println("result"+listRequestr);
		if (listRequestr.equals(null)) {
			
			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listRequestr, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getpagepending")
	public ResponseEntity<Object> getPagePending(Pageable page){
		int totalpage = requestService.getPagePending(page);
		return new ResponseEntity<>(totalpage, HttpStatus.OK);
	}

	@GetMapping(path = "/getpagehistory")
	public ResponseEntity<Object> getPageHistory(Pageable page){
		System.out.println("history"+page);
		int totalpage = requestService.getPageHistory(page);
		return new ResponseEntity<>(totalpage, HttpStatus.OK);
	}
}
