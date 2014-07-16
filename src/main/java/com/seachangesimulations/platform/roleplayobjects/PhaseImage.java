package com.seachangesimulations.platform.roleplayobjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PhaseImageDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

@Entity
@Component
@Scope("prototype")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PhaseImage  extends BaseSCPlatformObject{

	private Long id = null;
	private Long phaseId = null;
	private String phaseImageName = "";
	

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public String getPhaseImageName() {
		return phaseImageName;
	}

	public void setPhaseImageName(String phaseImageName) {
		this.phaseImageName = phaseImageName;
	}
	
	public List<PhaseImage> getAllForPhase(Long phaseId) {
		ArrayList returnList = new ArrayList();
		
		for (PhaseImage thisPhaseImage : new ArrayList<PhaseImage>()){
			
			if (thisPhaseImage.phaseId.intValue() == phaseId.intValue()){

				returnList.add(thisPhaseImage);
			}
		}
		return returnList;
	}
	
	public void save(){
		PhaseImageDao dao = (PhaseImageDao) getApplicationContext().getBean("phaseImageDao", PhaseImageDao.class);
		dao.save(this);
	}

	@Override
	public PhaseImage getById(Long id) {
		PhaseImageDao dao = (PhaseImageDao) getApplicationContext().getBean("phaseImageDao", PhaseImageDao.class);
		return dao.get(id);
	}
	
}
