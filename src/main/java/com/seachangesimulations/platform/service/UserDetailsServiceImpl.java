
package com.seachangesimulations.platform.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.assignment.PersonOrganizationAssignment;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	/** I am just punting now and setting the platform up for only one organization. */
	private Long orgId = new Long(1);
	
	private String fullName = "Max";

	/**
	 * This is the method we must have to implement UserDetailsService.
	 */
	//@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		System.out.println("");
		System.out.println("UserDetailsServiceImpl called!  loadUserByUsername with username: " + username);
		System.out.println("");
		
		Person person = new Person().getByUsername(username);

		if (person == null)
			throw new UsernameNotFoundException("user not found");

		//return buildUserFromUserEntity(person, getBaseAuthorities());
		return loadUserAuthoritiesForOrg(person);
	}

	//@Transactional(readOnly = true)
	private static Person buildUserFromUserEntity(Person person, Collection<GrantedAuthority> authorities) {

		System.out.println("buildUserFromUserEntity");
		
		person.setAuthorities(authorities);

		return person;
	}

	public Person loadUserAuthoritiesForOrg(Person person) {
		
		System.out.println("loading authoriti");

		if (person == null)
			throw new UsernameNotFoundException("user not found");

		if (orgId == null)
			throw new UsernameNotFoundException("Organization Id must be set first.");

		PersonOrganizationAssignment poa = new PersonOrganizationAssignment().getByPersonAndOrg(person.getId(), orgId);
		
		Collection<GrantedAuthority> authorities = getBaseAuthorities();

		if (poa != null) {
			if (poa.getAuthorizationLevel() >= PersonOrganizationAssignment.ADMIN_LEVEL) {
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_ADMIN));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_DEV));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_AUTHOR));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_FACILITATOR));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_PLAYER));
			} else if (poa.getAuthorizationLevel() >= PersonOrganizationAssignment.DEV_LEVEL) {
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_DEV));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_AUTHOR));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_FACILITATOR));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_PLAYER));
			} else if (poa.getAuthorizationLevel() >= PersonOrganizationAssignment.AUTHOR_LEVEL) {
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_AUTHOR));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_FACILITATOR));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_PLAYER));
			} else if (poa.getAuthorizationLevel() >= PersonOrganizationAssignment.FACILITATOR_LEVEL) {
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_FACILITATOR));
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_PLAYER));
			} else if (poa.getAuthorizationLevel() >= PersonOrganizationAssignment.PLAYER_LEVEL) {
				authorities.add(new SimpleGrantedAuthority(PersonOrganizationAssignment.ROLE_PLAYER));
			}
		}

		return buildUserFromUserEntity(person, authorities);
	}

	private Collection<GrantedAuthority> getBaseAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return authorities;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

}
