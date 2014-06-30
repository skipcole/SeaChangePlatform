package com.seachangesimulations.platform.controllers;

/**
 * Controller Mapping Constants.
 *
 */
public class CMC {
	
	/* Generics */
	public static final String INDEX = "index";

	/* Admin */
	public static final String ADMIN_BASE = "/admin";
	
	/* Developing */
	public static final String DEVELOPING_BASE = "/developing";
	
	/* Authoring */
	public static final String AUTHORING_BASE = "/authoring";
	public static final String A_ROLEPLAY_CREATE = "roleplay/create/{id}";
	public static final String A_ROLEPLAY_PUBLISH = "roleplay/publish/{id}";
	
	/* Facilitating */
	public static final String FACILITATING_BASE = "/facilitating";
	public static final String F_ASSIGNPLAYER_RP_A_PRA = "assignPlayers/{rpimId}/actorId/{aId}/praId/{praId}";
	
	/* Playing */
	public static final String PLAYING_BASE = "/playing";
	public static final String P_PERSONROLEPLAYASSIGNMENT = "pra/{praId}";
	public static final String P_CHANGEPHASE = "changePhase";
	public static final String P_SHOWPLUGINPOINTER = "showPlugin/{pluginPointerId}";
	
	/* Plugin Objects */
	public static final String PLUGINOBJECT_BASE = "/po";
	public static final String PO_DOC = "{pId}/getDocument/objectIndex/{objectIndex}.xml";
	
}
