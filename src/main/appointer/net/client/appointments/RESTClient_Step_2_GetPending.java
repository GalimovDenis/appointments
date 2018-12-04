package appointer.net.client.appointments;

import java.net.URISyntaxException;

import appointer.net.dto.IAppointmentDTO;
import appointer.util.checks.ArgumentsChecker;


//Can refactor this into one method by sending requestType enum;
/**
 * Double-CRUD Step number 2. Organizer downloads pending requests to do; 
 *
 */
public class RESTClient_Step_2_GetPending {

	static final String url = "http://localhost:8080/organizer/request/";

	public static IAppointmentDTO organizerGetCreateRequest(String organizerName) throws URISyntaxException {

		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		final String urn = "create/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

	public static IAppointmentDTO organizerGetReadRequest(String organizerName) throws URISyntaxException {

		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		final String urn = "read/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

	public static IAppointmentDTO organizerGetUpdateRequest(String organizerName) throws URISyntaxException {
		
		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");

		final String urn = "update/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

	public static IAppointmentDTO organizerGetDeleteRequest(String organizerName) throws URISyntaxException {

		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");
		
		final String urn = "delete/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

}
