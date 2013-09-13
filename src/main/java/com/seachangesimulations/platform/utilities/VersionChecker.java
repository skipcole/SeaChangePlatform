
package com.seachangesimulations.platform.utilities;

import java.util.StringTokenizer;

public class VersionChecker {

	public static final int SAME_SOFTWARE_VERSION = 0;

	public static final int EARLIER_SOFTWARE_VERSION_MAJOR = 1;
	public static final int EARLIER_SOFTWARE_VERSION_MINOR = 2;
	public static final int EARLIER_SOFTWARE_VERSION_MICRO = 3;

	public static final int FUTURE_SOFTWARE_VERSION_MAJOR = 4;
	public static final int FUTURE_SOFTWARE_VERSION_MINOR = 5;
	public static final int FUTURE_SOFTWARE_VERSION_MICRO = 6;

	public static int compareRelease(String simulation_release) {

		StringTokenizer str = new StringTokenizer(PlatformProperties.getRelease(), ".");

		Integer this_major = new Integer(str.nextToken().trim());
		Integer this_minor = new Integer(str.nextToken().trim());
		Integer this_micro = new Integer(str.nextToken().trim());

		str = new StringTokenizer(simulation_release, ".");

		Integer sim_major = new Integer(str.nextToken().trim());
		Integer sim_minor = new Integer(str.nextToken().trim());
		Integer sim_micro = new Integer(str.nextToken().trim());

		if (this_major.intValue() > sim_major.intValue()) {
			return EARLIER_SOFTWARE_VERSION_MAJOR;
		} else if (this_major.intValue() < sim_major.intValue()) {
			return FUTURE_SOFTWARE_VERSION_MAJOR;
		}

		if (this_minor.intValue() > sim_minor.intValue()) {
			return EARLIER_SOFTWARE_VERSION_MINOR;
		} else if (this_minor.intValue() < sim_minor.intValue()) {
			return FUTURE_SOFTWARE_VERSION_MINOR;
		}

		if (this_micro.intValue() > sim_micro.intValue()) {
			return EARLIER_SOFTWARE_VERSION_MICRO;
		} else if (this_micro.intValue() < sim_micro.intValue()) {
			return FUTURE_SOFTWARE_VERSION_MICRO;
		}

		return SAME_SOFTWARE_VERSION;

	}
}
