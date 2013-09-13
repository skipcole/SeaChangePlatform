
package com.seachangesimulations.platform.domain.assignment;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PluginRolePlayAssignmentDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

@Entity
@Component
@Scope("prototype")
public class PluginRolePlayAssignment extends BaseSCPlatformObject {

	public PluginRolePlayAssignment() {

	}

	// ////// Member Variables //////
	// Common Variables

	/** Id of the role play that this object is associated with. */
	private Long rolePlayId;

	@SuppressWarnings("unchecked")
	@Override
	public PluginRolePlayAssignment getById(Long id){
		PluginRolePlayAssignmentDao dao = (PluginRolePlayAssignmentDao) getApplicationContext().getBean("pluginRolePlayAssignmentDao", PluginRolePlayAssignmentDao.class);
		return dao.get(id);
	}

}
