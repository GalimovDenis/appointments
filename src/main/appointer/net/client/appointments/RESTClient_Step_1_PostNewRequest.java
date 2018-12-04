package appointer.net.client.appointments;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import appointer.net.dto.IAppointmentDTO;


//Can refactor 2 POST classes by encoding urn parts in the requestType;  
/**
 * Double-CRUD Step number 1. Attendee uploads the request for changes to the
 * server;
 */
public class RESTClient_Step_1_PostNewRequest {

	static final String url = "http://localhost:8080/attendee/request/";

	
	public static HttpStatus attendeeRequestCreate(IAppointmentDTO appDTO) throws URISyntaxException {

		final String urn = "create/";

		return RESTExchanger.postAppointmentDTO(appDTO, url + urn);

	}

	public static HttpStatus attendeeRequestRead(IAppointmentDTO appDTO) throws URISyntaxException {

		final String urn = "read/";

		return RESTExchanger.postAppointmentDTO(appDTO, url + urn);

	}

	public static HttpStatus attendeeRequestUpdate(IAppointmentDTO appDTO) throws URISyntaxException {

		final String urn = "update/";

		return RESTExchanger.postAppointmentDTO(appDTO, url + urn);

	}

	public static HttpStatus attendeeRequestDelete(IAppointmentDTO appDTO) throws URISyntaxException {

		final String urn = "delete/";

		return RESTExchanger.postAppointmentDTO(appDTO, url + urn);

	}

	@SuppressWarnings("unused")
	private static void printRequestResults(IAppointmentDTO appDTO, final ResponseEntity<Boolean> response) {
		System.out.println(
				"Printing appDTO registration result: " + response.getBody() + " with ID " + appDTO.getRequestId());
	}

}