package com.seachangesimulations.platform.domain;

/**
 * This interface is for objects that may have sub objects that need to get saved to XML, and then 
 * restored from there.
 * 
 * The main trick that I'm using when doing this, is to store all of the sub objects (normally linked to
 * via objects and perserved with Hibernate) into a temporary (transient) list of objects.
 *
 */
public interface MayHaveSubObjects {

	/** This method will search for any potential sub objects and load them into transient arrays. */
	public void loadSubObjects();
	
	/** This method will take any objects found in transient arrays, and save them to the database. */
	public void saveSubObjects();
	
}
