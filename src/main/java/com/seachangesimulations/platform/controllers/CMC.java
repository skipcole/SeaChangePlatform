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
	public static final String I_CMC_INSTALLFORM_POST = "/install/installForm";
	public static final String I_INSTALL_SUCCESS = "installSuccess";

	/* Admin */
	public static final String ADMIN_BASE = "/admin";
	public static final String ADMIN_INDEX = "adminIndex";
	
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
	public static final String A_ROLEPLAY_CREATE = "roleplay/create/{roleplayId}";
	public static final String A_ROLEPLAY_SELECT_GET = "selectRoleplay";
	public static final String A_ROLEPLAY_CHANGE = "changeRoleplay/{roleplayId}";
	public static final String A_ROLEPLAY_OBJECTIVES_GET = "roleplay/enterObjectives/{roleplayId}";
	public static final String A_ROLEPLAY_OBJECTIVES_POST = "roleplay/enterObjectives/{roleplayId}";
	public static final String A_ROLEPLAY_AUDIENCE_GET = "roleplay/enterAudience/{roleplayId}";
	public static final String A_ROLEPLAY_AUDIENCE_POST = "roleplay/enterAudience/{roleplayId}";
	
	public static final String A_ROLEPLAY_ACTOR_CREATE = "roleplay/{roleplayId}/actor/create/{actorId}";
	public static final String A_ROLEPLAY_ACTOR_EDIT = "actor/edit/{actorId}";
	
	public static final String A_ROLEPLAY_PHASE_CREATE = "roleplay/{roleplayId}/phase/create/{phaseId}";
	public static final String A_ROLEPLAY_PHASE_CREATE_SUCCESS = "phase/createPhaseSuccess/{phaseId}";
	public static final String A_ROLEPLAY_PHASE_EDIT = "phase/edit/{phaseId}";
	
	public static final String A_ROLEPLAY_PLUGIN_CREATE = "plugin/create";
	public static final String A_ROLEPLAY_PLUGIN_CUST_DOC = "plugin/{pluginId}/customizePluginDocument/{podId}";
	
	// PP stands for plugin placement
	public static final String A_ROLEPLAY_PP_UNIVERSAL = "roleplay/{roleplayId}/pluginPlacement";
	public static final String A_ROLEPLAY_PP_SPECIFIC = "roleplay/{roleplayId}/actor/{actorId}/phase/{phaseId}/pluginPlacement";
	
	public static final String A_ROLEPLAY_PP_CHANGE_ACTOR = "pluginPlacement/changeActor/{actorId}";
	public static final String A_ROLEPLAY_PP_CHANGE_PHASE = "pluginPlacement/changePhase/{phaseId}";
	
	public static final String A_ROLEPLAY_CUSTOMIZE_PLUGIN_GET = "pluginPlacement/customizePlugin/plugin/{pluginId}";
	
	public static final String A_ROLEPLAY_PUBLISH = "roleplay/publish/{id}";
	
	/* Facilitating */
	public static final String FACILITATING_BASE = "/facilitating";
	
	public static final String F_ASSIGNPLAYER_RP_A_PRA = "assignPlayers/{rpimId}/actorId/{actorId}/praId/{praId}";
	public static final String F_CREATE_RPIM_GET = "createRPIM/{roleplayId}/{rpimId}";
	public static final String F_CREATE_RPIM_POST = "createRPIM/{roleplayId}/{rpimId}" ;
	public static final String F_ASSIGN_PLAYER_GET = "assignPlayers/{rpimId}";
	public static final String F_LAUNCH_RPIM_GET = "launchRPIM/{rpimId}";
	public static final String F_LAUNCH_RPIM_POST = "launchRPIM/{rpimId}";
	
	/* Playing */
	public static final String PLAYING_BASE = "/playing";
	/* All URIs that start with "P_" indicate that they also start with '/playing/' */
	// bnp is short for 'bare naked page' - a page without headings.
	public static final String P_PERSONROLEPLAYASSIGNMENT = "pra/{praId}";
	public static final String P_CHANGEPHASE = "changePhase/{phaseId}";
	public static final String P_GETSESSIONINFO = "getSessionInfo";
	public static final String P_REFRESH = "refresh";
	public static final String P_SHOWPLUGINPOINTER = "showPlugin/{pluginPointerId}";
	public static final String P_GETALERTS = "getEventJSON/{lastEventIGot}.json";
	public static final String P_SEND_MESSAGE_GET = "bnpSendMessageGet";
	public static final String P_POSTEVENTS = "postEvent";
	
	
	
	/* Plugin Objects */
	public static final String PLUGINOBJECT_BASE = "/po";
	public static final String PO_DOC = "{pId}/getDocument/objectIndex/{objectIndex}.xml";

	
	public static String getURI(String code){
		
		if (code.equalsIgnoreCase("I_CMC_INSTALLFORM_POST")){ 
			return I_CMC_INSTALLFORM_POST;
		} else {
			System.out.println("This is bad");
			return null;
		}
	}

	

	

	

	
	
}
