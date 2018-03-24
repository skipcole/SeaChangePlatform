
package com.seachangesimulations.platform.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.BaseDao;
import com.seachangesimulations.platform.database.DbHqlAccessory;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.html.SearchFormBuilder;

/**
 * This very important class handles all of the basic CRUD (Create, Read, Update, Delete) operations for platform
 * objects.
 * 
 * 
 * @param <T>
 */
@Repository
public class BaseDaoHibernateImpl<T extends BaseSCPlatformObject> implements BaseDao<T> {

	private static String MYSQL = "MySql"; 
	public static String DB_VENDOR = MYSQL;  // features such as BINARY only for MySql	
	
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
	private boolean dbVendorIsMySql() {
		return DB_VENDOR.equalsIgnoreCase(MYSQL);
	}
	
	public T get(Long id) {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Object o = session.get(type, id);
		session.close();
		return (T) o;
	}

	public List<T> getAll() {
		// not sure why no commit or close
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery("from " + type.getName());
		List<T> list = (List<T>) (query.list());
		session.close();  // added 3.17.18 MJS
		return list;
	}
	
	
	// Get list of rows for a HQL database search. 
	// Return an object[] instead of an object since private object values are not easily accessible, etc.
	public List<Object[]> searchFor(Map<String, String> params) {
		
		SessionFactory sessionFactory = getSessionFactory();
		Session session = sessionFactory.openSession();
		ClassMetadata cmd = sessionFactory.getClassMetadata(type);
		
		List<String> dbColNames = DbHqlAccessory.getDBColumnNames(type.getName(), sessionFactory);
		StringBuilder hql = new StringBuilder("Select ");
		Map<String, String> hqlParams = new HashMap<>();
		boolean first = true;
		for (String colName: dbColNames) {
			if (!first) {hql.append(", ");}
			hql.append(colName);
			first = false;
		}
		hql.append(" from "  + type.getName());
		first = true;
		for (String colName: dbColNames) {
			// see if the text field (represented by colName) has anything in it.
			if (!params.containsKey(colName)) { continue; }
			String value = params.get(colName);
			if (value == null) { continue; }
			value = value.trim();
			if (value.length() == 0) { continue; }

			// value has been entered - construct string such as 
			// WHERE lower(firstName) = lower( :firstName) 
			// if ignoreCase checkbox parameter exists, the box has been checked.
			boolean ignoreCase = params.containsKey(colName + SearchFormBuilder.IGNORE_CASE.trim().replace(" ", ""));
			System.out.println("SearchFor ignoreCase check box: " + ignoreCase);
			System.out.println("Ignore case for " + colName + " is " + ignoreCase);			

			String op = " LIKE ";
			String select = null;
			if (params.containsKey(colName + "Select")) {
				select = params.get(colName + "Select");
				System.out.println(colName + "Select is " + select);
				if (select.equalsIgnoreCase(SearchFormBuilder.combineFieldAndLabel(colName, SearchFormBuilder.EQUALS))) {
					// leave values as it
				} else if (select.equalsIgnoreCase(SearchFormBuilder.combineFieldAndLabel(colName, SearchFormBuilder.NO_WILDCARDS))) {
					op = " = ";
				} else if (select.equalsIgnoreCase(SearchFormBuilder.combineFieldAndLabel(colName, SearchFormBuilder.STARTS_WITH))) {
					value = value + "%";
				} else if (select.equalsIgnoreCase(SearchFormBuilder.combineFieldAndLabel(colName, SearchFormBuilder.ENDS_WITH))) {
					value =  "%" + value;
				} else if (select.equalsIgnoreCase(SearchFormBuilder.combineFieldAndLabel(colName, SearchFormBuilder.CONTAINS))) {
					value = "%" + value + "%";
				} else {
					System.out.println("Bad selection for column " + colName + " Value: " + select);
				} // end if else
			} else { 
				System.out.println("BaseDAOHibernateImpl search for: Missing select parameter for col !" + colName);
			}			
			String joiner = " AND ";

			
			if (first) {
				hql.append(" WHERE ");
				first = false;
			} else {
				hql.append(joiner);  // AND or OR
			}
			// use params instead of single quotes to avoid HQL injection
			if (ignoreCase) {hql.append("lower(");}
			hql.append(colName);
			if (ignoreCase) {hql.append(")");}
			hql.append(op);
			// for MySQL database only, since default is case insensitive
			if (!ignoreCase && dbVendorIsMySql()) {hql.append("BINARY(");}
			if (ignoreCase) {hql.append("lower(");}
			hql.append(":" + colName);
			if (ignoreCase) {hql.append(")");}
			if (!ignoreCase && dbVendorIsMySql()) {hql.append(")");}
			hqlParams.put(colName, value);
		}
		if (!first ) {System.out.println("The HQL is " + hql); }
		
		Query query = session.createQuery(hql.toString());
		for (Map.Entry<String, String> entry : hqlParams.entrySet()) {
			System.out.println("The hqlParam is: " + entry.getKey() + "  -> " + entry.getValue());
			query.setParameter(entry.getKey(), entry.getValue());
		}
		List<Object[]> list = query.list();
		
		// Dont select all fields, as this returns an object (aka Person), not an array of objects (aka fields)
		// Query query2 = session.createQuery("from " + type.getName());
	    // List<T> result = query.list();  // returns objects instead of array of values
	    session.close();
		return list; 
	}  // end search for
	
	// Each table has its own sessionFactory, which is needed to get metaData, 
	// so we need a getDBColumnNames wrapper here.  Needed since routine called when displaying generic table.  
	// Return list of all column names. 
	public List<String> getDBColumnNames( ) {
	 	return DbHqlAccessory.getDBColumnNames(type.getName(), sessionFactory);
	}  // end getDBColumnNames
	

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
