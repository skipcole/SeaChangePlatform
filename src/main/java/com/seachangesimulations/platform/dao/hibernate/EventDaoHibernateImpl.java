package com.seachangesimulations.platform.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.EventDao;
import com.seachangesimulations.platform.rpimobjects.Event;

@Repository("eventDao")
public class EventDaoHibernateImpl extends BaseDaoHibernateImpl<Event> implements EventDao{

	public EventDaoHibernateImpl() {
		super(Event.class);
	}
	
}
