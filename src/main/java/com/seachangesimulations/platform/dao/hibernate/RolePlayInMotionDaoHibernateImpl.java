
package com.seachangesimulations.platform.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.RolePlayInMotionDao;
import com.seachangesimulations.platform.domain.RoleplayInMotion;

@Repository("rolePlayInMotionDao")
public class RolePlayInMotionDaoHibernateImpl extends BaseDaoHibernateImpl<RoleplayInMotion> implements
		RolePlayInMotionDao {

	public RolePlayInMotionDaoHibernateImpl() {
		super(RoleplayInMotion.class);
	}

}