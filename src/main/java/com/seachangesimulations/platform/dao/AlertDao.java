
package com.seachangesimulations.platform.dao;


import java.util.List;

import com.seachangesimulations.platform.rpimobjects.Alert;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface AlertDao extends BaseDao<Alert> {

	List<Alert> getPlayersAlerts(Long personId, Long actorId,
			Long rpimId, Long lastAlertReceived);

}
