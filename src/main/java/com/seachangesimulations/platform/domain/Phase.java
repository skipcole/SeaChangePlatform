
package com.seachangesimulations.platform.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PhaseDao;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;
import com.seachangesimulations.platform.rpimobjects.Message;
import com.seachangesimulations.platform.rpimobjects.Event;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Entity
@Component
@Scope("prototype")
public class Phase extends BaseSCPlatformObject implements MayHaveSubObjects{

	public Phase() {

	}

	/** Id of the role play that this object is associated with. */
	private Long roleplayId;

	/**
	 * If this phase is a member of a larger metaphase, the id of that metaphase.
	 */
	private Long metaPhaseId = null;

	/** Name of this Phase. */
	private String phaseName = ""; //$NON-NLS-1$

	/** Notes regarding this phase. */
	@Lob
	private String phaseNotes = ""; //$NON-NLS-1$

	/**
	 * Order of this Phase in relation to the others. (It is possible that the order will be dependent upon events in
	 * the role play.)
	 */
	private int phaseOrder;

	/** Start date of this phase. */
	private Date phaseStartDate = new Date();

	/**
	 * Indicates if time (in the form of round or calendar) is tracked during this phase.
	 */
	private boolean timePasses = false;

	/** Indicates the method by which time will pass during this simulation. */
	private int timePassageMechanism = 0;

	/** Flag to indicate that this is the first phase. */
	private boolean isFirstPhase = false;

	/** Flag to indicate that this is the last phase. */
	private boolean isLastPhase = false;

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long roleplayId) {
		this.roleplayId = roleplayId;
	}

	public Long getMetaPhaseId() {
		return metaPhaseId;
	}

	public void setMetaPhaseId(Long metaPhaseId) {
		this.metaPhaseId = metaPhaseId;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public String getPhaseNotes() {
		return phaseNotes;
	}

	public void setPhaseNotes(String phaseNotes) {
		this.phaseNotes = phaseNotes;
	}

	public int getPhaseOrder() {
		return phaseOrder;
	}

	public void setPhaseOrder(int phaseOrder) {
		this.phaseOrder = phaseOrder;
	}

	public Date getPhaseStartDate() {
		return phaseStartDate;
	}

	public void setPhaseStartDate(Date phaseStartDate) {
		this.phaseStartDate = phaseStartDate;
	}

	public boolean isTimePasses() {
		return timePasses;
	}

	public void setTimePasses(boolean timePasses) {
		this.timePasses = timePasses;
	}

	public int getTimePassageMechanism() {
		return timePassageMechanism;
	}

	public void setTimePassageMechanism(int timePassageMechanism) {
		this.timePassageMechanism = timePassageMechanism;
	}

	public boolean isFirstPhase() {
		return isFirstPhase;
	}

	public void setFirstPhase(boolean isFirstPhase) {
		this.isFirstPhase = isFirstPhase;
	}

	public boolean isLastPhase() {
		return isLastPhase;
	}

	public void setLastPhase(boolean isLastPhase) {
		this.isLastPhase = isLastPhase;
	}

	public Long create(String name, Long rpId, Long orgId) {
		PhaseDao dao = (PhaseDao) getApplicationContext().getBean("phaseDao", PhaseDao.class);
		return dao.create(name, rpId, orgId);
	}
	
	public void save(){
		PhaseDao dao = (PhaseDao) getApplicationContext().getBean("phaseDao", PhaseDao.class);
		dao.save(this);
	}
	public List<Phase> getAllForRoleplay(Long rpId) {
		PhaseDao dao = (PhaseDao) getApplicationContext().getBean("phaseDao", PhaseDao.class);

		return dao.getAllForRoleplay(rpId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Phase getById(Long id){
		PhaseDao dao = (PhaseDao) getApplicationContext().getBean("phaseDao", PhaseDao.class);
		return dao.get(id);
	}

	public Long getFirstForRoleplay(Long rId) {
		PhaseDao dao = (PhaseDao) getApplicationContext().getBean("phaseDao", PhaseDao.class);
		Phase initialPhase = dao.getFirstPhaseForRoleplay(rId);
		return initialPhase.getId();

	}

	@Override
	public void loadSubObjects() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSubObjects() {
		// TODO Auto-generated method stub
	
	}

	/**
	 * Creates the phase change events and creates alerts.
	 * 
	 * @param sessionInfoBean
	 * @param phaseId
	 */
	public static void createPhaseChangeEvent(SessionInfoBean sessionInfoBean,
			Long phaseId) {
		
		Phase oldPhase = new Phase().getById(sessionInfoBean.getPhaseId());
		Phase newPhase = new Phase().getById(phaseId);
		
		Event event = new Event();
		event.setEventType(Event.ET_PHASE_CHANGE);
		event.setEventName(Event.ET_PHASE_CHANGE_NAME);
		event.setEventDescription("Phase changed from " + oldPhase.getPhaseName() + " to " + newPhase.getPhaseName());
		event.setTimeOfEvent(new Date());
		event.setRoleplayId(sessionInfoBean.getRoleplayId());
		event.setRpimId(sessionInfoBean.getRolePlayInMotionId());
		event.setInitiatingActorId(sessionInfoBean.getActorId());
		event.setInitiatingUserId(sessionInfoBean.getPersonId());
		
		event.save();
		
		createMessagesForPhaseChange(event);
		
	}
	
	/**
	 * Creates the messages to notify players of phase change.
	 * 
	 * @param event
	 */
	public static void createMessagesForPhaseChange(Event event) {

		List<PersonRoleplayAssignment> allPeopleInThisRoleplay = new PersonRoleplayAssignment()
				.getAllPlayers(event.getRpimId());

		for (PersonRoleplayAssignment thisPra : allPeopleInThisRoleplay) {

			if (thisPra.getPersonId() != null) {

				Message message = new Message();
				message.setMessageType(Message.MESSAGE_TYPE_PHASE_CHANGE);
				message.setMessageText(event.getEventDescription());
				message.setRoleplayId(event.getRoleplayId());
				message.setRpimId(event.getRpimId());
				
				//TODO - keeps it real

				// End of keeps it real

				message.save();
			}
		}

	}

}
