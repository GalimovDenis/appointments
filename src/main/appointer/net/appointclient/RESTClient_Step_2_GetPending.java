package appointer.net.appointclient;

import java.net.URISyntaxException;

import appointer.net.dto.IAppointmentDTO;

/**
 * Double-CRUD Step number 2. Organizer downloads pending requests to do; 
 *
 */
public class RESTClient_Step_2_GetPending {

	static final String url = "http://localhost:8080/organizer/request/";

	public static IAppointmentDTO organizerGetCreateRequest(String organizerName) throws URISyntaxException {

		final String urn = "create/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

	public static IAppointmentDTO organizerGetReadRequest(String organizerName) throws URISyntaxException {

		final String urn = "read/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

	public static IAppointmentDTO organizerGetUpdateRequest(String organizerName) throws URISyntaxException {

		final String urn = "update/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

	public static IAppointmentDTO organizerGetDeleteRequest(String organizerName) throws URISyntaxException {

		final String urn = "delete/" + "?orgname=" + organizerName;

		return RESTExchanger.getAppointmentDTO(url + urn);

	}

}
