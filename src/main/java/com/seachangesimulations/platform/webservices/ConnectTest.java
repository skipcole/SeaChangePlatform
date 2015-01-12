package com.seachangesimulations.platform.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

@Component
@WebService
public class ConnectTest implements ConnectTestSEI {

	@WebMethod
	public String runTest(String entryString){
		return entryString + ": SAT";
	}
}
