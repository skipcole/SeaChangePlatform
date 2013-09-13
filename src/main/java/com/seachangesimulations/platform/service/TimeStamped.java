
package com.seachangesimulations.platform.service;

import java.util.Date;

public interface TimeStamped {

	public Date getCreatedDate();

	public void setCreatedDate(Date createdDate);

	public Date getLastUpdatedDate();

	public void setLastUpdatedDate(Date lastUpdatedDate);

}
