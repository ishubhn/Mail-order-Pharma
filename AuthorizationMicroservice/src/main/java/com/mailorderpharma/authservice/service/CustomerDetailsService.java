package com.mailorderpharma.authservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mailorderpharma.authservice.dao.UserDAO;
import com.mailorderpharma.authservice.entity.UserData;

@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userdao;

	@Override
	public UserDetails loadUserByUsername(String uid) {
		
		System.out.println("inside");
		try
		{
			UserData custuser = userdao.findById(uid).orElse(null);
			custuser.getUname();
			return new User(custuser.getUserid(), custuser.getUpassword(), new ArrayList<>());
		}
		catch (Exception e) {
			System.out.println("inside");
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
			
		
		
	}

}
