package com.tech.hunt.user.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.tech.hunt.AbstractUtils;

@Service
public class UserDetailsServiceImpl extends AbstractUtils implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Query userQuery = new Query("User");
		FilterPredicate filter = new FilterPredicate("username", FilterOperator.EQUAL, username);
		userQuery.setFilter(filter);
		Entity user = datastore.prepare(userQuery).asSingleEntity();
		if (user == null) {
			throw new UsernameNotFoundException("username [" + username + "] could not be found.");
		}

		return new CustomUserDetails(user);
	}

}
