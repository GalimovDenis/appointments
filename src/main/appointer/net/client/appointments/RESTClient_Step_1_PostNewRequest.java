package appointer.net.client.appointments;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import appointer.net.dto.IAppointmentDTO;
import appointer.util.checks.ArgumentsChecker;
  

//can probably refactor 5 classes into one;
/**
 * Double-CRUD Step number 1. Attendee uploads the request for changes to the
 * server;
 */
public final class RESTClient_Step_1_PostNewRequest {

	static final String url = "http://localhost:8080/attendee/request/";
	
	public static HttpStatus attendeeRequest(IAppointmentDTO appDTO) throws URISyntaxException {
		
		ArgumentsChecker.checkAppDTO(appDTO);
		
		return RESTExchanger.postAppointmentDTO(appDTO, url);

	}

	@SuppressWarnings("unused")
	private static void printRequestResults(IAppointmentDTO appDTO, final ResponseEntity<Boolean> response) {
		System.out.println(
				"Printing appDTO registration result: " + response.getBody() + " with ID " + appDTO.getSequence());
	}

}