package appointer.net.appointclient;

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
	
	
	private static void reportOnServer(IAppointmentDTO appEvent, final String url) throws URISyntaxException {
		String organizerName = appEvent.getOrganizer();

		if (organizerName == "")
			throw new IllegalArgumentException();


		RequestEntity<IAppointmentDTO> requestEntity = new RequestEntity<IAppointmentDTO>(appEvent, headers,
				HttpMethod.POST, new URI(url + "?orgname=" + organizerName));

		
		
		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event report result on server: " + body + " With ID " + appEvent.getRequestId());
	}
	
	private static void reportLocal(String organiserName, UUID uid, final String url) throws URISyntaxException {
		RequestEntity requestEntity = new RequestEntity(headers,
				HttpMethod.GET, new URI(url +"?orgname="+organiserName+"&uid=" + uid.toString()));
		
		ResponseEntity<IAppointmentDTO> response = restTemplate.exchange(requestEntity, IAppointmentDTO.class); // printing // without
		
		IAppointmentDTO body = response.getBody();

		System.out.println("Printing event report result for attendee: " + body);
	}
	
	/**
	 * Idempotent event report from organizer; event must include organizer name
	 * 
	 * @param event
	 * @throws URISyntaxException
	 */
	public static void organizerReportEventCreation(IAppointmentDTO appEvent) throws URISyntaxException {

		final String url = "http://localhost:8080/organizer/report/create";
		
		reportOnServer(appEvent, url);

	}

	/**
	 * Idempotent event report for attendee;
	 * @param appEvent
	 * @throws URISyntaxException
	 */
	public static void attendeeReceiveEventCreationReport(String organiserName, UUID uid) throws URISyntaxException {
		
		final String url = "http://localhost:8080/attendee/report/create";
		
		reportLocal(organiserName, uid, url);
	}

	
	/**
	 * Idempotent event report from organizer; event must include organizer name
	 * 
	 * @param event
	 * @throws URISyntaxException
	 */
	public static void organizerReportEventUpdate(IAppointmentDTO appEvent) throws URISyntaxException {

		final String url = "http://localhost:8080/organizer/report/update";
		
		reportOnServer(appEvent, url);

	}

	/**
	 * Idempotent event report for attendee;
	 * @param appEvent
	 * @throws URISyntaxException
	 */
	public static void attendeeReportEventUpdate(String organiserName, UUID uid) throws URISyntaxException {
		
		final String url = "http://localhost:8080/attendee/report/update";
		
		reportLocal(organiserName, uid, url);
	}

	

}
