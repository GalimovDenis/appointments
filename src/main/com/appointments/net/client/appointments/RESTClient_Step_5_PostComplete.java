package com.appointments.net.client.appointments;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;

import com.appointments.net.dto.IAppointmentDTO;
import com.appointments.util.checks.ArgumentsChecker;

/**
 * Double-CRUD Step number 5. Attendee confirms to the server that he received the results of the changes. 
 *
 */
public final class RESTClient_Step_5_PostComplete {
	
	static final  String url = "http://localhost:8080/attendee/complete";
	
	public static HttpStatus attendeeConfirmComplete (IAppointmentDTO appDTO) throws URISyntaxException {
		
		ArgumentsChecker.checkAppDTO(appDTO);

		assertTrue(appDTO.isComplete());
		
		final String uid = appDTO.getEventId();

		final String organizerName = appDTO.getOrganizer();
		
		if (organizerName == "")
			throw new IllegalArgumentException();
		
		final String urn = "?orgname=" + organizerName + "&uid="+uid + "&sequence="+appDTO.getSequence();
		
		return RESTExchanger.postAppointmentDTO(appDTO, url + urn);
		
	}

}
