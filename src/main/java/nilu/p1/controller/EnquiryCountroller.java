package nilu.p1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import nilu.p1.entity.Enquiry;
import nilu.p1.service.EnquiryServiceImpl;

@Controller
public class EnquiryCountroller {
	
	private EnquiryServiceImpl  service;
	
	// setter injection
	public EnquiryCountroller(EnquiryServiceImpl service) {
		this.service = service;
	}


	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		Enquiry enqobj = new Enquiry();
		model.addAttribute("enq", enqobj);
		return "enquiryForm";
	}

	
	@PostMapping("/addEnq")
	public String handelEnquiry( @ModelAttribute("enq") Enquiry enq, Model model , HttpServletRequest req ) throws Exception {
	
		// get existing session obj
		HttpSession session = req.getSession(false);
		 Integer counsellorId =(Integer) session.getAttribute("counsellorId");
		
		boolean isSaved = service.addEnquiry(enq,counsellorId );
		
		if(isSaved) {
			model.addAttribute("smsg"," enquery is add ...");
		}else {
			model.addAttribute("emsg","enquiry not added.. please try again ");
		}
		
		return "enquiryForm";
	}
	
	@GetMapping("/regview")
	public String getAllQuery(Model model,HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		Integer attribute = (Integer)  session.getAttribute("counsellorId");
		
		List<Enquiry> allEnquirys = service.getAllEnquirys(attribute);
		
		model.addAttribute("allQuery", attribute);
		
		return "registerView";
	}



}
