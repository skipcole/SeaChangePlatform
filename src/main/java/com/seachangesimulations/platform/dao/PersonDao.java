
package com.seachangesimulations.platform.dao;

import com.seachangesimulations.platform.domain.Person;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface PersonDao extends BaseDao<Person> {

	public Long validate(String username, String password);

	/** Returns true if user existed, otherwise returns false. */
	public boolean updateCore(String username, String password, Long id);
	
	public Person getByUsername(String userName);

}
