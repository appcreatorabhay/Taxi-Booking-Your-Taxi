package com.example.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.AdminDao;
import com.example.model.Admin;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private AdminDao adminDao;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	// ✅ Add Role here
	@PostConstruct
	public void init() {
		if (adminDao.count() == 0) {
			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRole("ROLE_ADMIN"); // ✅ Setting the role
			adminDao.save(admin);
		}
	}
	@PostConstruct
	public void initUsersForTesting() {
	    if (adminDao.findByUsername("user1").isEmpty()) {
	        Admin user = new Admin();
	        user.setUsername("user1");
	        user.setPassword(passwordEncoder.encode("user123"));
	        user.setRole("ROLE_USER");
	        adminDao.save(user);
	    }
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> byUsername = adminDao.findByUsername(username);
		Admin admin = byUsername.orElseThrow(() -> new UsernameNotFoundException("ADMIN DOES NOT EXIST"));

		// ✅ Use the role dynamically (remove "ROLE_" prefix as Spring adds it automatically)
		return User.withUsername(admin.getUsername())
				.password(admin.getPassword())
				.roles(admin.getRole().replace("ROLE_", ""))
				.build();
	}
}
