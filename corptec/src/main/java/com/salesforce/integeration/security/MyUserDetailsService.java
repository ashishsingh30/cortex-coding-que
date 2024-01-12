package com.salesforce.integeration.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
       //Fetching user details from Database, For now passing dummy object
    	 List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
    	MyUserDetails user = new MyUserDetails(username, "password", true, authorities);
        return user;
    }
}

