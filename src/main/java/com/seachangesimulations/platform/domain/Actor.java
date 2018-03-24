
package com.seachangesimulations.platform.domain;

import java.util.List;

// MJS First comment and commit. FDDF

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.ActorDao;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;

@Entity
@Component
@Scope("prototype")
@XmlRootElement
public class Actor extends BaseSCPlatformObject implements MayHaveSubObjects{
    // If a compile error is inserted here, it will show up with Highlight the Project 
	// in package explorer, right click "Run As", select Maven Buiid.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Actor() {
		System.out.println("Actor Constructor");
	}

	/** Id of the role play that this object is associated with. */
	private Long roleplayId;

	/** Name of the Actor */
	private String actorName;
	
	/** Name of the image associated with the Actor */
	private String actorImageName;
	
	/** If this actor has been designated as a normal, observer or control role BY DEFAULT (facilitator can override). */
	private int roleType = PersonRoleplayAssignment.NORMAL_ROLE;
	
	
	public void save(){
		ActorDao dao = (ActorDao) getApplicationContext().getBean("actorDao", ActorDao.class);
		dao.save(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Actor getById(Long id){
		ActorDao dao = (ActorDao) getApplicationContext().getBean("actorDao", ActorDao.class);
		return dao.get(id);
	}
	
	
	public List<Actor> getAllForRoleplay(Long rpId) {
		ActorDao dao = (ActorDao) getApplicationContext().getBean("actorDao", ActorDao.class);

		return dao.getAllForRoleplay(rpId);
	}

	@Lob
	private String publicDescription;

	public String getActorName() {
		return actorName;
	}

	@XmlElement
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getPublicDescription() {
		return publicDescription;
	}

	public void setPublicDescription(String publicDescription) {
		this.publicDescription = publicDescription;
	}

	public Long create(String name, Long rpId, Long orgId) {

		ActorDao dao = (ActorDao) getApplicationContext().getBean("actorDao", ActorDao.class);
		return dao.create(name, rpId, orgId);
	}

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long rolePlayId) {
		this.roleplayId = rolePlayId;
	}

	public String getActorImageName() {
		return actorImageName;
	}

	public void setActorImageName(String actorImageName) {
		this.actorImageName = actorImageName;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	@Override
	public void loadSubObjects() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSubObjects() {
		// TODO Auto-generated method stub
	
	}

}
