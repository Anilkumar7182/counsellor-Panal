 package nilu.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import nilu.p1.dto.DashboardResponse;
import nilu.p1.entity.Counsellor;
import nilu.p1.service.CounsellorServiceImp;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorServiceImp service;
	
	
	// register - page - display
		@GetMapping("/register")
		public String  register(Model model) {
			model.addAttribute("counsellor", new Counsellor());
			return "registerView";
		}

		// register - button - handle
		@PostMapping("/register")
		public String handelRegister(Counsellor counsellor ,Model model) {
			
			Counsellor byEmail = service.findByEmail(counsellor.getEmail());
			
			if(byEmail != null) {
				model.addAttribute("fmsg", "Registration Failed this email id allready Create accout");
				return "registerView";
			}
			
			boolean status = service.register(counsellor);
			if(status) {
				model.addAttribute("msg", "Registration  seccfully ..... ");
			}else {
				model.addAttribute("fmsg", "Registration Failed");
			}
			return "registerView";
		}
		
	
	
	// login - page - display	
	@GetMapping("/")
	public String login(Model model) {
		Counsellor coun = new Counsellor();
		model.addAttribute("counsellor", coun);
		return "login";
	}
	
	
	// login - button - handle	
	@PostMapping("/login")
	public String handleLogin(Counsellor counsellor, HttpServletRequest request  , Model model) {
	
		// check email id and passwordn are not null value
		if (counsellor.getEmail() == null || counsellor.getPassword() == null) {
		    model.addAttribute("emsg", "Email and Password must not be empty");
		    return "login";
		}
		
		 try {
		        Counsellor c = service.login(counsellor.getEmail(), counsellor.getPassword());
		        
		        if (c == null) {
		            model.addAttribute("emsg", "Invalid Credentials please currect email and password enter");
		            return "login";
		        }

		        // Valid login, store counsellorId in session for future use
		        HttpSession session = request.getSession(true);
		        session.setAttribute("counsellorId", c.getCounsellorId());

		        // Fetch dashboard information
		        DashboardResponse dbobj = service.getDashboardInfo(c.getCounsellorId());
		        model.addAttribute("dashbordInfo", dbobj);

		        return "dashboard";

		    } catch (Exception e) {
		        //logger.error("Error during login process: ", e);
		    	System.out.println(e);
		        model.addAttribute("emsg", "An unexpected error occurred. Please try again later.");
		        return "login";
		    }
		}
		

	
	// logout - method
	
		@GetMapping("/logout")
		public String logout(HttpServletRequest req,Model model) {
			
			//get existing session and invalidate it
			HttpSession session = req.getSession(false);
			session.invalidate();
		//	model.addAttribute("counsellor", new Counsellor());
			
			// redirect to login page
			return "redirect:/";
		}


}
