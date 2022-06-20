package com.Lims.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.Lims.dao.AgentRepository;
import com.Lims.dao.UserRepository;
import com.Lims.entities.Agent;
import com.Lims.entities.User;
import com.Lims.exception.FileStorageException;
import com.Lims.helper.Message;

/**
 * @author Raghav Arora
 * @since-15-06-2022
 * purpose-This is the Home controller ,It manages all the 
 * actions performed by customer/user.
 */
@Controller
public class HomeController {
	
	@Autowired    
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	 private UserRepository userRepository;
	
	//@GetMapping("/home")
	@RequestMapping("/")
	public String home() {
		//User user=new User();
		//user.setFirstName("DEMO");
		//user.setLastName("DEMO");
		//user.setEmail("Demo@gmail.com");
//		System.out.println(user);
		//userRepository.save(user);
		return "home";
	}	

	@RequestMapping("/contactus")
	public String contactus() {
		//System.out.println("inside contact");
		return "contactus";
	}
	
	@RequestMapping("/signup")
	public String signup() {
		
		return "signup";
	}
	
	@RequestMapping("/beAgent")
	public String beAgent(Model model ) {
		model.addAttribute("agent",new Agent());
		return "beAgent";
	}
	
	//backend handler for beAgent
	@PostMapping("/be_agent")
	public String BeAgent(@ModelAttribute("agent") Agent agent,HttpSession session) {
		
		session.setAttribute("message", new Message("Reqest Sent Successfully ","alert-success"));
		this.agentRepository.save(agent);
		return "beAgent";
	}
	
	
	//handler for registering user
	@RequestMapping(value="/do_register",method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user")User user,@RequestParam(value="role",defaultValue="customer")String role ,Model model,HttpSession session) {
		
		try {				
			//differ between userrole of admin and customer and throw message accordingly.
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			this.userRepository.save(user);
			model.addAttribute("user",new User());
			if(role.equals("admin")) {
				session.setAttribute("message", new Message("Successfully registered. Try login after 24hrs","alert-success"));				
				// send a notification to admin.
			}else {				
				session.setAttribute("message", new Message("Successfully registered","alert-success"));
			}
			return "signup";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));
			
			return "signup";
		}
		
		
	}

	// handler for login 
	
	@GetMapping("/signin")
	 public String Login(Model model) {
		model.addAttribute("title","Login Page");
		//first fetch data from db and then check the role .
		// if else.
		 return "login";
	 }
	
	//Backendhandler for custom login
	@RequestMapping(value="/signin",method=RequestMethod.POST)
	 public String customLogin(Model model,@RequestParam(value="username")String User,HttpSession session) {
		
		User user=userRepository.getUserByUserName(User);
		model.addAttribute("title","Login Page");
		//first fetch data from db and then check the role .
		// if else.
//		if(user!=null) {
//			if(user.getRole().equals("admin")) {
//				session.setAttribute("message", new Message("user not registered","alert-Danger"));
//			}
//		}
		try {
			if(user!=null) {				
				if(user.getRole().equals("admin")) {
					return "admin/adminLogin";
				}else if(user.getRole().equals("user")) {
					return "normal/userdashboard";
				}else {
					session.setAttribute("message", new Message("user not registered","alert-Danger"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			//model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));	
		}
		 return "login";
	 }
	
	
	
	
	//handler for admin login
	@RequestMapping("/adminLogin")
	public String AdminLogin() {
		return "admin/adminLogin";
	}
	
	//handling process of adminlogin
	@RequestMapping(value="/adminLogin",method=RequestMethod.POST)
	public String signupUser(@RequestParam(value="username")String User ,Model model,HttpSession session) {
		User user=userRepository.getUserByUserName(User);
		//System.out.println("user from hc"+ user.getEmail());
		//System.out.println("user from hc"+ user.getRole());
		try {
			if(user!=null) {				
				if(user.getRole().equals("admin")) {
					return "admin/AdminDashboard";
				}else if(user.getRole().equals("user")) {
					throw new FileStorageException("Login from customer portal");
//					return "login";
				}else {
					session.setAttribute("message", new Message("user not registered","alert-Danger"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new FileStorageException("Something went wrong");
			//model.addAttribute("user", user);
			//session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));	
		}
		return "admin/adminLogin";
	}
	

	


	
	
}
