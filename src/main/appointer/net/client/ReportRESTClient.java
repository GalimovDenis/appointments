package appointer.net.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.IAppointmentDTO;

public class ReportRESTClient {

	static RestTemplate restTemplate = new RestTemplate();

	static HttpHeaders headers = new HttpHeaders();
	
	/**
	 * Idempotent event report from organizer; event must include organizer name
	 * 
	 * @param event
	 * @throws URISyntaxException
	 */
	public static void organizerReportEvent(IAppointmentDTO appEvent) throws URISyntaxException {

		String organizerName = appEvent.getOrganizer();

		if (organizerName == "")
			throw new IllegalArgumentException();

		final String url = "http://localhost:8080/organizer/report/create";
		

		RequestEntity<IAppointmentDTO> requestEntity = new RequestEntity<IAppointmentDTO>(appEvent, headers,
				HttpMethod.POST, new URI(url + "?orgname=" + organizerName));

		
		
		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event report result on server: " + body + " With ID " + appEvent.getEventId());

	}

	/**
	 * Idempotent event report for attendee;
	 * @param appEvent
	 * @throws URISyntaxException
	 */
	public static void attendeeReportEvent(String organiserName, UUID uid) throws URISyntaxException {
		
		final String url = "http://localhost:8080/attendee/report/create";
		
		RequestEntity requestEntity = new RequestEntity(headers,
				HttpMethod.GET, new URI(url +"?orgname="+organiserName+"&uid=" + uid.toString()));
		
		ResponseEntity<IAppointmentDTO> response = restTemplate.exchange(requestEntity, IAppointmentDTO.class); // printing // without
		
		IAppointmentDTO body = response.getBody();

		System.out.println("Printing event report result for attendee: " + body);
	}
}
