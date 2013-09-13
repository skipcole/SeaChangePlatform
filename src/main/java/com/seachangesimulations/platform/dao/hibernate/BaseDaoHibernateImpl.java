
package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.BaseDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

/**
 * This very important class handles all of the basic CRUD (Create, Read, Update, Delete) operations for platform
 * objects.
 * 
 * 
 * @param <T>
 */
@Repository
public class BaseDaoHibernateImpl<T extends BaseSCPlatformObject> implements BaseDao<T> {

	private Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;

	/** Zero element contructor required by Hibernate. */
	public BaseDaoHibernateImpl() {

	}

	public BaseDaoHibernateImpl(Class<T> type) {
		super();
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Object o = session.get(type, id);
		session.close();
		return (T) o;
	}

	public List<T> getAll() {
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery("from " + type.getName());

		return query.list();

	}

	public void save(T object) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(object);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(T object) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
		session.close();

	}

	public void indexEntity(T object) {
		FullTextSession fullTextSession = Search.getFullTextSession(getSessionFactory().openSession());
		// TODO figure out why this broke in converting project to maven project
		//fullTextSession.index(T);

	}

	public void indexAllItems() {
		// TODO Auto-generated method stub

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
