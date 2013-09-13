
package com.seachangesimulations.platform.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PluginRolePlayAssignmentDao;
import com.seachangesimulations.platform.domain.assignment.PluginRolePlayAssignment;

@Repository("pluginRoleplayAssignmentDao")
public class PluginRoleplayAssignmentDaoHibernateImpl extends BaseDaoHibernateImpl<PluginRolePlayAssignment> implements
		PluginRolePlayAssignmentDao {

	public PluginRoleplayAssignmentDaoHibernateImpl() {
		super(PluginRolePlayAssignment.class);
	}

}