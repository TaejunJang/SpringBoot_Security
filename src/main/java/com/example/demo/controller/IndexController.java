package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/test/oauth/login")
	public @ResponseBody String testOauthLogin(Authentication authentication) {
		System.out.println(authentication.getPrincipal());
		OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println(oauth2User.getAttributes());
		return "Oauth세션정보 확인";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user(){
		return "user";
	}
	
	
	@GetMapping("/admin")
	public @ResponseBody String admin(){
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager(){
		return "manager";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(){
		return "loginForm";
	}
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@GetMapping("/joinForm")
	public String join(){
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user){
		System.out.println(user);
		user.setUser_role("ROLE_USER");
		String rawPwd = user.getPassword();
		String encPwd = bCryptPasswordEncoder.encode(rawPwd);
		user.setPassword(encPwd);
		
		userRepository.save(user);
		return "redirect:/loginForm";
	}
	
	@GetMapping("/joinProc")
	public @ResponseBody String joinProc(){
		return "회원가입 완료됨";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/info2")
	public @ResponseBody String info2() {
		return "개인정보2";
	}
}

