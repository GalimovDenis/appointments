package com.appointments.net.client.appointments;

import java.net.URISyntaxException;

import com.appointments.net.dto.IAppointmentDTO;
import com.appointments.net.dto.RequestType;
import com.appointments.util.checks.ArgumentsChecker;


/**
 * Double-CRUD Step number 2. Organizer downloads pending requests to do; 
 *
 */
public final class RESTClient_Step_2_GetPending {

	static final String url = "http://localhost:8080/organizer/request/";

	public static IAppointmentDTO organizerGetRequest(RequestType type, String organizerName) throws URISyntaxException {

		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		ArgumentsChecker.checkNotNull(type, "RequestType");
		
		// produces urn like: create+"/"+"?orgname"+Organizer
		final String urn = type.getURN() +"/"+ "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

}
