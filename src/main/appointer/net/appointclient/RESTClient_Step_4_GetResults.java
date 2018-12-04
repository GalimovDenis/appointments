package appointer.net.appointclient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.IAppointmentDTO;

/**
 * Double-CRUD Step number 4. Attendee downloads the changes from to the
 * server;
 *
 */
public class RESTClient_Step_4_GetResults {

	
	static final String url = "http://localhost:8080/attendee/report/";
	
	public static IAppointmentDTO attendeeReceiveCreateReport(String organiserName, UUID uid) throws URISyntaxException {
		
		final String urn = "create" + "?orgname="+organiserName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
	
	public static IAppointmentDTO attendeeReceiveReadReport(String organiserName, UUID uid) throws URISyntaxException {
		
		final String urn = "read" + "?orgname="+organiserName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
	
	public static IAppointmentDTO attendeeReceiveUpdateReport(String organiserName, UUID uid) throws URISyntaxException {
		
		final String urn = "update" + "?orgname="+organiserName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
	
	public static IAppointmentDTO attendeeReceiveDeleteReport(String organiserName, UUID uid) throws URISyntaxException {
		
		final String urn = "delete" + "?orgname="+organiserName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
}
