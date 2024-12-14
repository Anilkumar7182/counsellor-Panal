package nilu.p1.controller;

import org.hibernate.engine.query.spi.EntityGraphQueryHint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import nilu.p1.entity.Enquiry;
import nilu.p1.service.EnquiryService;
import nilu.p1.service.EnquiryServiceImpl;

@Controller
public class EnquiryCountroller {
	
	@Autowired
	private EnquiryServiceImpl  service;
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		Enquiry enqobj = new Enquiry();
		model.addAttribute("enquiry", enqobj);
		return "enquiryForm";
	}

	
	@PostMapping("/addEnq")
	public String handelEnquiry(Model model , HttpServletRequest req ,Enquiry enq) throws Exception {
	
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



}
