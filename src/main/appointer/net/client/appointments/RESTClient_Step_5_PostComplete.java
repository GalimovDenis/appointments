package appointer.net.client.appointments;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;

import appointer.net.dto.IAppointmentDTO;

/**
 * Double-CRUD Step number 5. Attendee confirms to the server that he received the results of the changes. 
 *
 */
public class RESTClient_Step_5_PostComplete {
	
	static final  String url = "http://localhost:8080/attendee/complete";
	
	public static HttpStatus attendeeConfirmComplete (IAppointmentDTO appEvent) throws URISyntaxException {
		
		assertTrue(appEvent.isComplete());
		
		final String uid = appEvent.getRequestId().toString();

		final String organizerName = appEvent.getOrganizer();
		
		if (organizerName == "")
			throw new IllegalArgumentException();
		
		final String urn = "?orgname=" + organizerName + "&uid="+uid;
		
		return RESTExchanger.postAppointmentDTO(appEvent, url + urn);
		
	}

}
