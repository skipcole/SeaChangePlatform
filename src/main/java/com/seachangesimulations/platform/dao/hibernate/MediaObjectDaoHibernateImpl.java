package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.MediaObjectDao;
import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.MediaObject;

@Repository("mediaObjectDao")
public class MediaObjectDaoHibernateImpl extends BaseDaoHibernateImpl<MediaObject> implements MediaObjectDao{

	public MediaObjectDaoHibernateImpl() {
		super(MediaObject.class);
	}

	@Override
	public Long create(String name, Long rpId, Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> getAllForRoleplay(Long rpId) {
		// TODO Auto-generated method stub
		return null;
	}

}
