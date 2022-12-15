package org.pizzahat.com.demo;

import java.util.HashSet;
import java.util.Set;

import org.pizzahat.com.demo.pojo.Role;
import org.pizzahat.com.demo.pojo.User;
import org.pizzahat.com.demo.service.RoleServ;
import org.pizzahat.com.demo.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurity1Application implements CommandLineRunner {

	@Autowired
	private RoleServ roleServ;
	
	@Autowired
	private UserServ userServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");
		
		roleServ.save(userRole);
		roleServ.save(adminRole);
		
		User userUser = new User("user", "{noop}userpws", userRole);
		User adminUser = new User("admin", "{noop}adminpws", adminRole);
		
		Set<Role> userAdminRoles = new HashSet<>();
		userAdminRoles.add(userRole);
		userAdminRoles.add(adminRole);
		User userAdminUser = new User("useradmin", "{noop}useradminpws", userAdminRoles);
		
		userServ.save(userUser);
		userServ.save(adminUser);
		userServ.save(userAdminUser);
	}
}
