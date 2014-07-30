package com.seachangesimulations.platform.controllers;

/**
 * Controller Mapping Constants.
 *
 */
public class CMC {
	
	/* Generics */
	public static final String INDEX = "index";
	
	/* Install */
	public static final String INSTALL = "/install";
	public static final String I_INSTALLFORM = "installForm";
	public static final String I_INSTALLFORM_POST = "installForm";
	public static final String I_INSTALL_SUCCESS = "installSuccess";

	/* Admin */
	public static final String ADMIN_BASE = "/admin";
	
	/* Developing */
	public static final String DEVELOPING_BASE = "/developing";
	public static final String D_CHOOSE = "choose";
	public static final String D_API = "pluginAPI";
	public static final String D_DEFINE_PLUGIN_GET = "definePlugin/{id}";
	public static final String D_DEFINE_PLUGIN_POST = "definePlugin/{id}";
	public static final String D_UPLOAD_PLUGIN_FILES_GET = "uploadPluginFiles/{id}";
	public static final String D_UPLOAD_PLUGIN_FILES_POST = "uploadPluginFiles/{id}";
	public static final String D_ADD_OBJECTS_TO_PLUGIN_FILES_GET = "addObjectsToPlugin/{id}";
	public static final String D_ADD_OBJECTS_TO_PLUGIN_FILES_POST = "addObjectsToPlugin/{id}";
	public static final String D_REMOVE_PLUGIN_POA_GET = "plugin/{pId}/removePluginObjectAssociation/{id}";
	public static final String D_MODIFY_PLUGIN_DOC_GET = "modifyPluginObject/plugin/{pId}/document/{dId}";
	public static final String D_MODIFY_PLUGIN_DOC_POST = "plugin/{pId}/modifyPluginDocument/{dId}";
	
	/* Authoring */
	public static final String AUTHORING_BASE = "/authoring";
	public static final String A_ROLEPLAY_CREATE = "roleplay/create/{id}";
	public static final String A_ROLEPLAY_SELECT_GET = "selectRoleplay";
	public static final String A_ROLEPLAY_CHANGE = "changeRoleplay/{id}";
	public static final String A_ROLEPLAY_OBJECTIVES_GET = "rolePlay/enterObjectives/{id}";
	public static final String A_ROLEPLAY_OBJECTIVES_POST = "rolePlay/enterObjectives/{id}";
	public static final String A_ROLEPLAY_AUDIENCE_GET = "rolePlay/enterAudience/{id}";
	public static final String A_ROLEPLAY_AUDIENCE_POST = "rolePlay/enterAudience/{id}";
	public static final String A_ROLEPLAY_CUSTOMIZE_PLUGIN_GET = "pluginPlacement/customizePlugin/plugin/{pId}";
	public static final String A_ROLEPLAY_PP_CHANGE_ACTOR = "pluginPlacement/changeActor/{aId}";
	public static final String A_ROLEPLAY_PP_CHANGE_PHASE = "pluginPlacement/changePhase/{pId}";
	
	public static final String A_ROLEPLAY_PUBLISH = "roleplay/publish/{id}";
	
	/* Facilitating */
	public static final String FACILITATING_BASE = "/facilitating";
	public static final String F_ASSIGNPLAYER_RP_A_PRA = "assignPlayers/{rpimId}/actorId/{aId}/praId/{praId}";
	public static final String F_CREATE_RPIM_GET = "createRPIM/{rId}/{rpimId}";
	public static final String F_CREATE_RPIM_POST = "createRPIM/{rId}/{rpimId}" ;
	public static final String F_ASSIGN_PLAYER_GET = "assignPlayers/{rpimId}";
	public static final String F_LAUNCH_RPIM_GET = "launchRPIM/{rpimId}";
	public static final String F_LAUNCH_RPIM_POST = "launchRPIM/{rpimId}";
	
	/* Playing */
	public static final String PLAYING_BASE = "/playing";
	/* All URIs that start with "P_" indicate that they also start with '/playing/' */
	public static final String P_PERSONROLEPLAYASSIGNMENT = "pra/{praId}";
	public static final String P_CHANGEPHASE = "changePhase/{phaseId}";
	public static final String P_GETSESSIONINFO = "getSessionInfo";
	public static final String P_REFRESH = "refresh";
	public static final String P_SHOWPLUGINPOINTER = "showPlugin/{pluginPointerId}";
	public static final String P_GETEVENTS = "getEventJSON/{lastEventIGot}.json";
	public static final String P_POSTEVENTS = "postEvent";
	
	/* Plugin Objects */
	public static final String PLUGINOBJECT_BASE = "/po";
	public static final String PO_DOC = "{pId}/getDocument/objectIndex/{objectIndex}.xml";

	


	

	

	

	
	
}
