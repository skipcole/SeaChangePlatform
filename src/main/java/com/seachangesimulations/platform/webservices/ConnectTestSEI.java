
package com.seachangesimulations.platform.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "ConnectTestSEI", targetNamespace = "http://webservices.platform.seachangesimulations.com/")
public interface ConnectTestSEI {

	@WebMethod
	public String runTest(String entryString);

}