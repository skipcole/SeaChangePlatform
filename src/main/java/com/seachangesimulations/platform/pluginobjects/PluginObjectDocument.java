package com.seachangesimulations.platform.pluginobjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.pluginobjects.dao.PluginObjectAssociationDao;
import com.seachangesimulations.platform.pluginobjects.dao.PluginObjectDocumentDao;

@Entity
@Component
@Scope("prototype")
//@//XmlRootElement
@XmlAccessorType (XmlAccessType.NONE)   // This means that if you want a field mapped, it has to be annotated.
public class PluginObjectDocument extends BasePluginObject {
	
	@XmlAttribute
	private String documentName;
	
	@XmlAttribute
	private String documentDescription;
	
	@Lob
	@XmlAttribute
	private String documentText;

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PluginObjectDocument getById(Long id){
		PluginObjectDocumentDao dao = (PluginObjectDocumentDao) getApplicationContext().getBean("pluginObjectDocumentDao", PluginObjectDocumentDao.class);
		return dao.get(id);
	}

	public void save() {
		PluginObjectDocumentDao dao = (PluginObjectDocumentDao) getApplicationContext().getBean("pluginObjectDocumentDao", PluginObjectDocumentDao.class);
		dao.save(this);
		
	}
	
	public List getAllForPlugin(Long pluginId){
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		
		List <PluginObjectAssociation> poas  = dao.getAllForPlugin(pluginId, PluginObjectDocument.class.getCanonicalName());
		
		ArrayList <PluginObjectDocument>returnList = new ArrayList<PluginObjectDocument>();
		for (PluginObjectAssociation poa : poas){
			System.out.println(poa.getObjectType());
			
			// Since we only have docs now
			PluginObjectDocument pod = new PluginObjectDocument().getById(poa.getObjectId());
			if (pod == null) {
				System.out.println("XXXXXXX        XXXXXXXX XXXXXXXXXXXXX     pod null");
			} else {
				returnList.add(pod);
			}
		}
		
		return returnList;
	}

	public PluginObjectDocument getByRPimIdPluginIdAndIndex(Long rpimId, Long pluginId, int docIndex) {
		PluginObjectDocumentDao dao = (PluginObjectDocumentDao) getApplicationContext().getBean("pluginObjectDocumentDao", PluginObjectDocumentDao.class);
		return dao.getByRPimIdPluginIdAndIndex(rpimId, pluginId, docIndex);
	}

	/**
	 * Returns all documents associated with the current roleplay.
	 * 
	 * @param roleplayId
	 * @return
	 */
	public Object getAllForRoleplay(Long roleplayId) {
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		
		List <PluginObjectAssociation> poas  = dao.getAllForRoleplay(roleplayId, PluginObjectDocument.class.getCanonicalName());

		ArrayList returnList = new ArrayList();
		// Convert to documents
		for (PluginObjectAssociation poa : poas) {
			returnList.add( new PluginObjectDocument().getById(poa.getObjectId()));
			
		}
		return returnList;
	}
}
