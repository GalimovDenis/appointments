package appointer.net.client.appointments;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.IAppointmentDTO;

public class RESTExchanger {

	static final RestTemplate restTemplate = new RestTemplate();

	static final HttpHeaders headers = new HttpHeaders();

	public static IAppointmentDTO getAppointmentDTO(final String uri) throws URISyntaxException {
		
		final RequestEntity<IAppointmentDTO> requestEntity = new RequestEntity<IAppointmentDTO>(headers, HttpMethod.GET,
				new URI(uri));

		final ResponseEntity<IAppointmentDTO> response = restTemplate.exchange(requestEntity, IAppointmentDTO.class);
		
		final IAppointmentDTO appDTO = response.getBody();

		return appDTO;
	}

	public static HttpStatus postAppointmentDTO(IAppointmentDTO appEvent, final String uri) throws URISyntaxException {
	
		final String organizerName = appEvent.getOrganizer();

		if (organizerName == "")
			throw new IllegalArgumentException();

		final RequestEntity<IAppointmentDTO> requestEntity = new RequestEntity<IAppointmentDTO>(appEvent, headers,
				HttpMethod.POST, new URI(uri));

		final ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing //
																										// without

		return response.getStatusCode();
	}

	public static HttpStatus postReportComplete(final String uri) throws URISyntaxException {

		final RequestEntity<Boolean> requestEntity = new RequestEntity<Boolean>(headers, HttpMethod.POST, new URI(uri));

		final ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing //																										// without

		return response.getStatusCode();
	}

	public static void clearServer() {
		
		restTemplate.delete("http://localhost:8080/attendee/complete/clear");
		
	}
}
