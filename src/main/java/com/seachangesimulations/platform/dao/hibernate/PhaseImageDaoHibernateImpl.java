package com.seachangesimulations.platform.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PhaseImageDao;
import com.seachangesimulations.platform.roleplayobjects.PhaseImage;

@Repository("phaseImageDao")
public class PhaseImageDaoHibernateImpl extends BaseDaoHibernateImpl<PhaseImage> implements PhaseImageDao{

	public PhaseImageDaoHibernateImpl() {
		super(PhaseImage.class);
	}
	
}
