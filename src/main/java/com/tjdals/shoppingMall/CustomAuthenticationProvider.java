package com.tjdals.shoppingMall;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Optional<User> optionalUser = userRepository.findById(username);
		User user = null;
		if(optionalUser.isPresent()) {
			user = optionalUser.orElse(null);
		}
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder = 
				new BCryptPasswordEncoder();

		Role role = null;
		if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
			Optional<Role> optionalRole = roleRepository.findById(username);
			if(optionalRole.isPresent()) {
				role = optionalRole.orElse(null);
			}
		}
		
		if(role == null) {
			throw new UsernameNotFoundException(role.getRoleName());
		}
		
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
		
		return new UsernamePasswordAuthenticationToken
				(username, password, grantedAuthorityList);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
