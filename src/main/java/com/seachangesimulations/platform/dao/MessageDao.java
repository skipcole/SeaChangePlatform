
package com.seachangesimulations.platform.dao;


import java.util.List;

import com.seachangesimulations.platform.rpimobjects.Message;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface MessageDao extends BaseDao<Message> {

	/** Returns messages sent to the user based on their unique user id. */
	List<Message> getPlayersPersonalMessages(Long personId,
			Long rpimId, Long lastMessageReceived);
	
	/** Returns messages sent to the user based on the role they are playing id. */
	List<Message> getPlayersRoleMessages(Long actorId,
			Long rpimId, Long lastMessageReceived);

}
