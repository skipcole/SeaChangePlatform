package com.seachangesimulations.platform.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.AlertDao;
import com.seachangesimulations.platform.rpimobjects.Alert;

@Repository("alertDao")
public class AlertDaoHibernateImpl extends BaseDaoHibernateImpl<Alert> implements AlertDao{

	public AlertDaoHibernateImpl() {
		super(Alert.class);
	}
	
}
