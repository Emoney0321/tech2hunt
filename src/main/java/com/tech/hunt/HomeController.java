package com.tech.hunt;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.Entity;
import com.tech.hunt.svo.NewUser;

@Controller
public class HomeController extends AbstractUtils {
	
	@Resource
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	public String signup(NewUser newUser, HttpServletRequest request, HttpServletResponse response) {
		String password = passwordEncoder.encode(newUser.getPassword());
		Entity user = new Entity("User");
		user.setProperty("username", newUser.getUsername());
		user.setProperty("password", password);
		user.setProperty("lastLogin", null);
		datastore.put(user);
		
		Entity profile = new Entity("Profile");
		profile.setProperty("firstName", newUser.getFirstName());
		profile.setProperty("lastName", newUser.getLastName());
		profile.setProperty("email", newUser.getEmail());
		datastore.put(profile);
		
		return "redirect:login";
	}

}
