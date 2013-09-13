
package com.seachangesimulations.platform.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PersonDao;
import com.seachangesimulations.platform.domain.Person;

@Repository("personDao")
public class PersonDaoHibernateImpl extends BaseDaoHibernateImpl<Person> implements PersonDao {

	public PersonDaoHibernateImpl() {
		super(Person.class);
	}

	/**
	 * Returns the id of the validated person, otherwise, returns null.
	 * 
	 */
	public Long validate(String username, String password) {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person where lower(USERNAME) = :username AND PASSWORD = :password ")
				.setString("username", username).setString("password", password);

		Person p = (Person) query.uniqueResult();

		session.close();

		if (p != null) {
			return p.getId();
		} else {
			return null;
		}
	}

	/**
	 * Attempts to update the person with new information. Returns false if this is person not found.
	 * 
	 */
	public boolean updateCore(String username, String password, Long id) {

		Person p = this.get(id);
		if (p == null) {
			return false;
		}

		if (username != null){
			p.setUsername(username);
		}
		
		if (password != null){
			p.setPassword(password);
		}

		this.save(p);

		return true;
	}

	@Override
	public Person getByUsername(String userName) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Person where lower(USERNAME) = :username")
				.setString("username", userName);

		Person p = (Person) query.uniqueResult();
		
		// Set password to empty string. No reason to have it in memory
		// TODO revisit this 
		//p.setPassword("");

		session.close();
		
		return p;
	}
	
	/**
	 * Remove password information on retrieval.
	 * 
	 */
	// TODO 
	//@Override
	public Person getOverIt(Long id){
		Person p = super.get(id);
		p.setPassword("");
		return p;
	}

}
