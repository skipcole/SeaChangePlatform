
package com.seachangesimulations.platform.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PlatformLoginDao;
import com.seachangesimulations.platform.domain.PlatformLogin;

@Repository("platformLoginDao")
public class PlatformLoginDaoHibernateImpl extends BaseDaoHibernateImpl<PlatformLogin> implements PlatformLoginDao {

	public PlatformLoginDaoHibernateImpl() {
		super(PlatformLogin.class);
	}

}