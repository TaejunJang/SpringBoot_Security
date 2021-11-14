package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

//시큐리티 설정에서 loginProccessingUrl("/login")
//login요청이 오면 자동으로 UserDetailsService타입으로 IOC되어있는 loadUserByUsername  함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findUserById(username);
		System.out.println(username);
		if(user !=null) {
			return new PrincipalDetails(user);
		}
		
		return null;
	}

}
