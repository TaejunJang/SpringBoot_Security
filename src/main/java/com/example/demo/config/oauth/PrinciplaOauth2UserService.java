package com.example.demo.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.auth.PrincipalDetails;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class PrinciplaOauth2UserService extends DefaultOAuth2UserService{

	@Autowired
	private UserRepository userRepository;
	
	//구글로 부터 받은 userRequest 데이터에 대한 후처리하는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("userRequest:"+userRequest);
		System.out.println("userRequest:"+userRequest.getClientRegistration());
		System.out.println("userRequest:"+userRequest.getAccessToken());
		System.out.println("userRequest:"+userRequest.getClientRegistration().getClientId());
		System.out.println("userRequest:"+super.loadUser(userRequest).getAttributes());
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		String provider = userRequest.getClientRegistration().getClientId();//goole
		String providerId = oauth2User.getAttribute("sub");
		String id =  provider+"_"+providerId;
		String email = oauth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		User userEntity =	userRepository.findUserById(id);
		
		if (userEntity == null) {
			userEntity = User.builder()
					.user_role(role)
					.email(email)
					.provider(provider)
					.providerId(providerId)
					.id(id)
					.build();
			
			userRepository.save(userEntity);
		}
		else {
			
		}
	
		return new PrincipalDetails(userEntity,oauth2User.getAttributes());
	}
}
