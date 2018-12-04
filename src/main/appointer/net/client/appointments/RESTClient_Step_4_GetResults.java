package appointer.net.client.appointments;

import java.net.URISyntaxException;
import java.util.UUID;

import appointer.net.dto.IAppointmentDTO;
import appointer.util.checks.ArgumentsChecker;


// see step 2 for refactoring
/**
 * Double-CRUD Step number 4. Attendee downloads the changes from to the
 * server;
 *
 */
public class RESTClient_Step_4_GetResults {

	
	static final String url = "http://localhost:8080/attendee/report/";
	
	public static IAppointmentDTO attendeeReceiveCreateReport(String organizerName, UUID uid) throws URISyntaxException {
		
		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		final String urn = "create" + "?orgname="+organizerName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
	
	public static IAppointmentDTO attendeeReceiveReadReport(String organizerName, UUID uid) throws URISyntaxException {
		
		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		final String urn = "read" + "?orgname="+organizerName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
	
	public static IAppointmentDTO attendeeReceiveUpdateReport(String organizerName, UUID uid) throws URISyntaxException {
		
		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		final String urn = "update" + "?orgname="+organizerName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
	
	public static IAppointmentDTO attendeeReceiveDeleteReport(String organizerName, UUID uid) throws URISyntaxException {
		
		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		final String urn = "delete" + "?orgname="+organizerName+"&uid=" + uid.toString();
		
		return RESTExchanger.getAppointmentDTO(url+urn);
	}
}
