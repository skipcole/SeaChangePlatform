
package com.seachangesimulations.platform.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.service.ApplicationContextProvider;
import com.seachangesimulations.platform.service.TimeStamped;

/**
 * This class serves as a collection point that all domain objects can implement it, so all can be accepted as Type
 * parameters by the interface BaseDao (and all of its concrete implementations.)
 * 
 * @author Skip
 * 
 */
@Component
@MappedSuperclass
@XmlAccessorType (XmlAccessType.NONE)  // This means that if you want a field mapped, it has to be annotated.
public abstract class BaseSCPlatformObject implements TimeStamped, Serializable, ApplicationContextAware {

	/** Database id of this object used by Hibernate. */
	@Id
	@GeneratedValue
	protected Long id;

	@Version
	protected Integer version;

	/** Id used when objects are exported and imported moving across databases. */
	protected Long transitId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getTransitId() {
		return transitId;
	}

	public void setTransitId(Long transitId) {
		this.transitId = transitId;
	}

	/** Date this object created, which is used by methods required by TimeStamped Interface. */
	protected Date createdDate;

	/** Date this object last updated, which is used by methods required by TimeStamped Interface. */
	protected Date lastUpdatedDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;

	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;

	}
	
	@Transient
	private static ApplicationContext applicationContext;
	
	/** Had problems with applicationContext being null, so went to this route, which seemes to work.
	 * 
	 * @return
	 */
	public ApplicationContext getApplicationContext(){
		
		if (applicationContext == null){
			applicationContext = ApplicationContextProvider.getApplicationContext();
			
			for (int ii = 0 ; ii < 10 ; ++ii){
				System.out.println("applicationContext was null, so we had to re-get it.");
			}
			
		}
		
		return applicationContext;
	}
	
    protected Long initialOrgId;
	
	public Long getInitialOrgId() {
		return initialOrgId;
	}


	public void setInitialOrgId(Long initialOrgId) {
		this.initialOrgId = initialOrgId;
	}
	
    public void setApplicationContext(ApplicationContext _applicationContext) throws BeansException {
        applicationContext = _applicationContext;
    }
    
    /**
     * Returns an object based on its id, unless the id is '0', in which case it
	 * returns a new object. This dance is done to allow an id of 0 to indicate that we
	 * want a new object created, so we can use the same form for create and edit.
	 * (The URL calling for the form will have either /id/{0}  or /id/{n}, where n is the
	 * actual id number of the object desired.)
	 * 
     * @param className
     * @param id
     * @return
     */
	public <T> T getModelObject(Class className,Long id) {
	
		
		try {
			
			T returnObject = (T) className.newInstance();
			
			BaseSCPlatformObject ro = (BaseSCPlatformObject) returnObject;
			
			if (id.intValue() > 0) {
				returnObject = ro.getById(id);
			} else {
				ro.setId(new Long(0));
				returnObject = (T) ro;
			}
			
			return (T) returnObject;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/** All of the children need a 'getById' method. */
	public abstract <T> T getById(Long id);

}
