package com.bharath.springcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repos.UserRepo;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByEmail(username);
		System.out.println("UserDetailsServiceImp class called 5");
		if(user==null) {
			throw new UsernameNotFoundException("User not found "+username);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),user.getRoles());
	}

}
