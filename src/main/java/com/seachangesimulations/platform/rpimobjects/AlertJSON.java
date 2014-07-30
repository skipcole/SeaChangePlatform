package com.seachangesimulations.platform.rpimobjects;

public class AlertJSON {

	private Long alertId = new Long (0);
	
	private String alertType = "";
	
	private String alertText = "";

	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAlertText() {
		return alertText;
	}

	public void setAlertText(String alertText) {
		this.alertText = alertText;
	}
	
	
}
