package com.Lims.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.Lims.dao.ClaimRepository;
import com.Lims.dao.ClientRepository;
import com.Lims.dao.FeedbackRepository;
import com.Lims.dao.HelpRepository;
import com.Lims.dao.PaymentRepository;
import com.Lims.dao.UserRepository;
import com.Lims.entities.Agent;
import com.Lims.entities.ClientInfo;
import com.Lims.entities.Feedback;
import com.Lims.entities.User;
import com.Lims.entities.claimPolicy;
import com.Lims.exception.FileStorageException;
import com.Lims.exception.MyFileNotFoundException;
import com.Lims.helper.Message;
import com.Lims.entities.Payment;
import com.Lims.entities.Policy;
import com.Lims.entities.Help;

/**
 * @author Raghav Arora
 * @since-15-06-2022
 * purpose-This is the User controller ,It manages all the 
 * actions performed by user.
 */
@Controller
@RequestMapping("/user")
public class UserController  {
	

	
@Autowired
	private UserRepository userRepository;
   
   @Autowired
   private ClientRepository clientRepository;
	
   @Autowired
   private PaymentRepository paymentRepository;
   
   @Autowired
   private HelpRepository helpRepository;
   
   @Autowired
   private ClaimRepository claimRepository;
   
   @Autowired
   private FeedbackRepository feedbackRepository;
   
   @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
   
   @ModelAttribute
   public void addCommonData(Model model,Principal principal) {
	   try {
		   String username=principal.getName();
//		System.out.println(username);
		   User user=userRepository.getUserByUserName(username);
//		System.out.println("USER"+user.getFirstName());
		   if(user!=null) {			
			   model.addAttribute("user",user);
		   }
		   
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
   }
   
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		return "normal/userdashboard";
	}
	
	//handler for applyforinsurance
	@GetMapping("/applyinsurance")
	public String Applyinsurance(Model model,Principal principal) {
		//List<ClientInfo>client=this.clientRepository.findAll();
		//System.out.println(client);
		model.addAttribute("ClientInfo",new ClientInfo());
		return "normal/insurancedashboard";
	}
	
	//handler for processing data of apply insurance.
	@PostMapping("/process_insurance")
	public String processinsurance(@ModelAttribute ClientInfo clientinfo,Principal principal) {
		try {
			User user=this.userRepository.getUserByUserName(principal.getName());
			clientinfo.setUser(user);
//		System.out.println("inside usercontroller" + user);
			user.getClientinfo().add(clientinfo);
//		System.out.println(user.getClientinfo());
			this.userRepository.save(user);
			return "normal/payment";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "normal/payment";
	}
	
	@RequestMapping("/claimPolicy")
	public String claimPolicy(Model model) {
		model.addAttribute("Claim",new claimPolicy());
		return "normal/claimPolicy";
	}
	
	@PostMapping("/process-claim")
	public String processClaim(@ModelAttribute claimPolicy claimPolicy,Model model,Principal principal,HttpSession session ) {
		
//		System.out.println(claimPolicy);
		
		try {
			if(claimPolicy!=null) {
				this.claimRepository.save(claimPolicy);
				 session.setAttribute("message", new Message("Request Sent","alert-success")); 
				 model.addAttribute("Claim", claimPolicy);
			}else {
					System.out.println("claim Number failed");
					throw new MyFileNotFoundException("invalid claim Number");
				}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			 session.setAttribute("message", new Message("Something went wrong!!","alert-danger")); 
		}
		
		return"normal/claimPolicy";
	}
	
	
	//handler for calculator
	@RequestMapping("/calculator")
		public String calculator() {
			
			return "normal/calculator";
		}
	
	
	//handler for changepassword
	@RequestMapping("/changePassword")
	public String changePassword() {
		
		return "normal/changePassword";
	}
	
	//backendhandler for changePassword
	@PostMapping("/change-password")
	public String changepassword(@RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword,Principal principal,HttpSession session) {
      try {
    	  User currUser=this.userRepository.getUserByUserName(principal.getName());
    	  System.out.println("old" + oldPassword);
    	  System.out.println("new"+ newPassword);
    	  if(this.bCryptPasswordEncoder.matches(oldPassword, currUser.getPassword()))
    	  {
    		  currUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
    		  this.userRepository.save(currUser);
    		  session.setAttribute("message", new Message("Password Changed Successfully ","alert-success"));
    	  }else {
    		  session.setAttribute("message", new Message("Please Enter Correct OldPassword ","alert-danger"));
    	  }
    	  return"normal/changePassword";
      }catch(Exception e) {
    	  e.printStackTrace();
      }
		return"normal/changePassword";
	}
	
	//handler for help
     @RequestMapping("/Help")
	public String Help(Model model) {
    	 model.addAttribute("Help", new Help());
		return "normal/Help";
	}
     
     //handler for feedback
     @RequestMapping("/Feedback")
 	public String Feedback(Model model) {
 		model.addAttribute("feedback",new Feedback());
 		return "normal/feedback";
 	}
     
     //backend handler for feedback
     @PostMapping("/processfeedback")
 	public String feedback(@ModelAttribute Feedback feedback,HttpSession session) {
 		
 		 try{
 	    	   if(feedback!=null) {
 	    		   this.feedbackRepository.save(feedback);
 	            session.setAttribute("message", new Message("Feedback Sent","alert-success"));    		   
 	    	   }
 	       }catch(Exception e) {
 	    	   e.printStackTrace();
 	    	   session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));
 	       }
 		return "normal/feedback";
 	}
     
     
     
   //Backhandler for help
 	@PostMapping("/helpRequest")
 	public String HelpRequest(@ModelAttribute Help help,Principal principal,HttpSession session) {
 		
// 		User user=this.userRepository.getUserByUserName(principal.getName());
// 		System.out.println("inside help request " + user);
 		 try{
 	    	   if(help!=null) {
 	    		   this.helpRepository.save(help);
 	            session.setAttribute("message", new Message("Request Sent","alert-success"));    		   
 	    	   }
 	       }catch(Exception e) {
 	    	   e.printStackTrace();
 	    	   session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));
 	       }
 		return "normal/Help";
 	}
	
	@RequestMapping("/process_insurance")
	public String Payment(Model model) {
		model.addAttribute("payment", new Payment());
		return "normal/payment";
	}
	//backend for payment processing
	@PostMapping("/paymentGateway")
	public String paymentGateway(@ModelAttribute("Payment")Payment payment,@RequestParam(value="cardNumber")long cardNo ,HttpSession session) {
		try {
//			System.out.println(this.paymentRepository.findBycardHolderName("123409871234"));
			Long cardNum=this.paymentRepository.getcardNumberBycardNumber(cardNo);
//			Long cardBal=this.paymentRepository.getavailableBalanceBycardNumber(cardNo);
			//System.out.println("here is the cardNumber"+ cardNum);
			String s=String.valueOf(cardNum);
			if(s!=null) {
				// match here cardno and cvv
				if(s.equals("123409874567")) {
					System.out.println("card matched");
					// check if balance is availabl
					session.setAttribute("message", new Message("Payment done Successfully ","alert-success"));
				}else {
					session.setAttribute("message", new Message("Invalid Card Details","alert-danger"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));
		}
		return"normal/payment";
	}
	
	//handler for notifications
	  @RequestMapping("/notify")
	 	public String Notifications(@ModelAttribute claimPolicy claimPolicy , Model m) {
		  List<claimPolicy>claimpolicy=this.claimRepository.findAll();
			m.addAttribute("claim",claimpolicy);
	 		return "normal/notify";
	 	}
	
	//handler for update User from admin form
			@GetMapping("/resubmitClaim/{id}")
			public String updateUser(@PathVariable("id") int cid,Model model ) {
				
			 claimPolicy claimPolicy=this.claimRepository.findById(cid).get();
				model.addAttribute("Claim",claimPolicy);
				return "normal/resubmitForm";
			}
			//backend handler for resubmit claimpolicy form
			@PostMapping("/process-resubmitClaim")
			public String updateUser(@ModelAttribute claimPolicy claimPolicy) {
				this.claimRepository.saveAndFlush(claimPolicy);
				return "redirect:/user/index";
			}

//			@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//			@ExceptionHandler(NullPointerException.class)
//			public String exceptionHandler(Model m) {
//				m.addAttribute("msg","Exception Occured");
//				return "null_page";
//			}
}