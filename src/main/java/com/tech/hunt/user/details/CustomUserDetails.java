package com.tech.hunt.user.details;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.appengine.api.datastore.Entity;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1400862342246756899L;

	private final List<GrantedAuthority> USER_ROLE = Collections.unmodifiableList(AuthorityUtils
			.createAuthorityList("ROLE_USER"));

	private String username;
	private String password;
	private Collection<GrantedAuthority> authorities;

	public CustomUserDetails(Entity user) {
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		this.username = (String) user.getProperty("username");
		this.password = (String) user.getProperty("password");
		this.authorities = "eric".equals(this.username) ? this.USER_ROLE : null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomUserDetails other = (CustomUserDetails) obj;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [username=" + username + ", password=" + password + ", authorities=" + authorities
				+ "]";
	}

}
