package com.Lims.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import 	org.springframework.security.core.Authentication;
import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.Lims.dao.AgentRepository;
import com.Lims.dao.AgentServices;
import com.Lims.dao.ClaimRepository;
import com.Lims.dao.HelpRepository;
import com.Lims.dao.PolicyRepository;
import com.Lims.dao.UserRepository;
import com.Lims.entities.Agent;
import com.Lims.entities.Help;
import com.Lims.entities.Policy;
import com.Lims.entities.User;
import com.Lims.entities.claimPolicy;
import com.Lims.helper.Message;

/**
 * @author Raghav Arora
 * @since-15-06-2022
  * purpose-This is the admin controller ,It manages all the 
  * actions performed by admin.
 */
@Controller
@RequestMapping("/adminLogin")
public class AdminController {
	
	@Autowired
	private PolicyRepository policyRepository;
 
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AgentServices agentService;
	
	@Autowired
	private HelpRepository helpRepository;
	
	@Autowired
	private ClaimRepository claimPolicyRepository;
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String AdminDashboard() {
		return "admin/AdminDashboard";
	}
	@RequestMapping("/addPolicy")
	public String addPolicy() {
		
		return "admin/addPolicy";
	}
	
	//handler for feedback
	@RequestMapping("/feedback")
	public String feedback(Model m) {
		
		return "admin/feedback";
	}
	
	
	//handler for processing data of addPolicy
	@RequestMapping(value="/add_Policy",method=RequestMethod.POST )
	 public String add_policy(@ModelAttribute Policy Policy,User user, HttpSession session) {		
       try{
    	   if(Policy!=null) {
    		   this.policyRepository.save(Policy);
            session.setAttribute("message", new Message("Policy Added","alert-success"));    		   
    	   }
       }catch(Exception e) {
    	   e.printStackTrace();
    	   session.setAttribute("message", new Message("Something went wrong!!" + e.getMessage(),"alert-danger"));
       }
		 return "admin/addPolicy";
	 }
	
	@RequestMapping("/showPolicy")
	public String ShowPolicy(Model m,Principal  principal ) {
		//fetch all policies from database
		//send policy list from here 
		try {			
			List<Policy>policy=this.policyRepository.findAll();
			m.addAttribute("policies",policy);
			return "admin/showPolicy";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "admin/showPolicy";
	}
	
	//delete policy handler
	@GetMapping("/delete/{pNumber}")
	public String deletePolicy(@PathVariable("pNumber") long pNum,Model model ) {
		try {
			Optional<Policy> policyOptional=this.policyRepository.findById(pNum);
			Policy policy =policyOptional.get();
			this.policyRepository.delete(policy);
			return "redirect:/adminLogin/dashboard";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/adminLogin/dashboard";
	}
	
	//handler for update form
	@PostMapping("/update/{pNumber}")
	public String updatePolicy(@PathVariable("pNumber") long pNum,Model model ) {
		try {			
			Policy policy=this.policyRepository.findById(pNum).get();
			model.addAttribute("policy",policy);
			return "admin/updateForm";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "admin/updateForm";
	}
	
	//backend handler update form
	@PostMapping("/process-update")
	public String updateHandler(@ModelAttribute Policy policy) {
		try {
			this.policyRepository.saveAndFlush(policy);
			return "redirect:/adminLogin/dashboard";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/adminLogin/dashboard";
	}
	
	
	//handler for showusers
	@RequestMapping("/showUsers")
	public String ShowUsers(Model m) {
		//fetch all policies from database
		try {
			List<User>user=this.userRepository.findAll();
			m.addAttribute("users",user);
			return "admin/showUsers";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "admin/showUsers";
	}
	
	//delete user from admin handler
		@GetMapping("/deleteUser/{id}")
		public String deleteUser(@PathVariable("id") int cid,Model model ) {
			
			try {
				Optional<User> userOptional=this.userRepository.findById(cid);
				User user =userOptional.get();
				this.userRepository.delete(user);
				return "redirect:/adminLogin/dashboard";
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "redirect:/adminLogin/dashboard";
		}
	
		//handler for update User from admin form
		@PostMapping("/updateUser/{id}")
		public String updateUser(@PathVariable("id") int uid,Model model ) {
			try {				
				User user=this.userRepository.findById(uid).get();
				model.addAttribute("user",user);
				return "admin/updateUserForm";
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "admin/updateUserForm";
		}
	
	//backend handler for update user form
		@PostMapping("/process-updateUser")
		public String updateUser(@ModelAttribute User user) {
			try {
				this.userRepository.saveAndFlush(user);
				return "redirect:/adminLogin/dashboard";
			}catch(Exception e) {
				
			}
			return "redirect:/adminLogin/dashboard";
		}
	
		//handler for agentRequest
		@RequestMapping("/agentRequest")
		public String agentRequest(Model m) {
			try {
				List<Agent>agent=this.agentRepository.findAll();
				m.addAttribute("agents",agent);
				return "admin/agentRequest";	
			}catch(Exception e) {
				e.printStackTrace();
			}
//check here			 
			return "admin/agentRequest";
		}

		//delete agentRequest from admin handler
				@GetMapping("/deleteAgent/{id}")
				public String deleteAgent(@PathVariable("id") int cid,Model model ) {
					try {
						Optional<Agent> agent=this.agentRepository.findById(cid);
						Agent agents =agent.get();
						this.agentRepository.delete(agents);
						return "admin/agentRequest";
					}catch(Exception e) {
						e.printStackTrace();
					}
			
					return "admin/agentRequest";
				}
		
				@GetMapping("/agents")
				public String getAgent(@RequestParam("keyword") String Keyword,Model model ) {
					try {
						List<Agent> agentList=agentService.listAll(Keyword);
						model.addAttribute("agents",agentList);
						model.addAttribute("Keyword",Keyword);
						return "admin/agentRequest";
					}catch(Exception e) {
						e.printStackTrace();
					}
					return "admin/agentRequest";
				}
		
				//handler for helpRequest
				@RequestMapping("/HelpRequest")
				public String helpRequest(Model m) {
					try {
						List<Help>help=this.helpRepository.findAll();
						m.addAttribute("help",help);
						return "admin/helpRequest";
					}catch(Exception e) {
						e.printStackTrace();
					}
					 
					return "admin/helpRequest";
				}		
		
				//handler for claimPolicy Request
				
				@RequestMapping("/claimPolicy")
				public String claimPolicyRequest(@ModelAttribute claimPolicy claimPolicy , Model m ) {
					try {
//					if(!claimPolicy.getClaimStatus().equals("Rejected")) {						
						List<claimPolicy>claimpolicy=this.claimPolicyRepository.findAll();
						m.addAttribute("claim",claimpolicy);
//					}
						return "admin/claimRequest";
					}catch(Exception e) {
						e.printStackTrace();
					}
					 
					return "admin/claimRequest";
				}
				
				//handler for delete claim policy Request
				@GetMapping("/deleteClaimRequest/{id}")
				public String deleteClaimRequest(@PathVariable("id") int cid,Model model,HttpSession session ) {
					try {
						Optional<claimPolicy> claimPolicy=this.claimPolicyRepository.findById(cid);
						claimPolicy ClaimPolicy =claimPolicy.get();
						ClaimPolicy.setClaimStatus("Rejected");
						this.claimPolicyRepository.saveAndFlush(ClaimPolicy);
						session.setAttribute("message", new Message("Claim Status updated","alert-primary"));
						model.addAttribute(ClaimPolicy);
						return "admin/AdminDashboard";
					}catch(Exception e) {
						e.printStackTrace();
					}
					return "admin/AdminDashboard";
				}
				//
				@GetMapping("/deleteClaim/{id}")
				public String deleteClaim(@PathVariable("id") int cid,Model model ) {
					try {
						Optional<claimPolicy> cp=this.claimPolicyRepository.findById(cid);
						claimPolicy claimpolicy =cp.get();
						this.claimPolicyRepository.delete(claimpolicy);
						return "admin/AdminDashboard";
					}catch(Exception e) {
						e.printStackTrace();
					}
					return "admin/AdminDashboard";
				}
				
				//handler for approving the claimstatus
				@GetMapping("/approveClaim/{id}")
				public String approveClaim(@PathVariable("id") int cid,Model model,HttpSession session ) {
					try {
						Optional<claimPolicy> claimPolicy=this.claimPolicyRepository.findById(cid);
						claimPolicy ClaimPolicy =claimPolicy.get();
						ClaimPolicy.setClaimStatus("Approved");
						this.claimPolicyRepository.saveAndFlush(ClaimPolicy);
						session.setAttribute("message", new Message("Claim Status updated","alert-primary"));
						model.addAttribute(ClaimPolicy);
						return "admin/AdminDashboard";
					}catch(Exception e) {
						e.printStackTrace();
					}
					return "admin/AdminDashboard";
				}
				
				
//				@ResponseStatus(value=HttpStatus.FORBIDDEN)
//				@ExceptionHandler()
//				public String exceptionHandler(Model m) {
//					m.addAttribute("msg","Exception Occured");
//					return "null_page";
//				}
}
