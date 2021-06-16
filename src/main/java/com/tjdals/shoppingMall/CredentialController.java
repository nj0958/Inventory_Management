package com.tjdals.shoppingMall;



import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping({"/",  "/index"})
	public String home(
			Model model
			) {

		return "index";
	}

	@GetMapping("/user/login")
	public String login() {
		
		return "login";
	}
	
	@PostMapping("/user/login")
	public String login_process() {
		
		return "redirect:/";
	}

	@GetMapping("/user/logout")
	public String logout(
			HttpSession session
			) {
		session.invalidate();
					
		return "redirect:/";
	}
	
	
	@GetMapping("/user/register")
	public String register(
			UserForm userForm
			) {
		
		return "register";
	}
	
	@PostMapping("/user/register")
	public String register_process(
			@Valid UserForm userForm,  BindingResult bindingResult
			) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		
		User user = new User();
		user.setUserName(userForm.getUserName());
		user.setAddress(userForm.getAddress());
		user.setPhone_number(userForm.getPhone_number());
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword =  bCryptPasswordEncoder.encode(userForm.getPassword());
		
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
		
		Role role = new Role();
		role.setUserName(userForm.getUserName());
		role.setRoleName("user");
		
		roleRepository.save(role);
		
		return "redirect:/";
	}
	@GetMapping("/user_list")
	public String user_list(
			Model model) {
		
		List<User> user = userRepository.findAll();
		model.addAttribute("user" , user);
		return "user_list";
	}
	
	
	
}
