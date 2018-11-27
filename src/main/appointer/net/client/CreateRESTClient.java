package appointer.net.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.AppointmentCreate;

/**
 *Calls the server to create an appointment; 
 */
public class CreateRESTClient {

	static RestTemplate restTemplate = new RestTemplate();

	static HttpHeaders headers = new HttpHeaders();
	
	public static void attendeeNewAppointment(AppointmentCreate appEvent) throws URISyntaxException {
				
		final String url = "http://localhost:8080/attendee/request/create/";

		
		RequestEntity<AppointmentCreate> requestEntity = new RequestEntity<AppointmentCreate>(appEvent, headers,
				HttpMethod.POST, new URI(url));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event creation result on server: " + body + " with ID " + appEvent.getRequestId());

	}
	
	

	public static AppointmentCreate organizerPendingApproval(String organizerName) throws URISyntaxException {
		
		final String url = "http://localhost:8080/organizer/request/create/";
	
		RequestEntity<AppointmentCreate> requestEntity = new RequestEntity<AppointmentCreate>(headers,
				HttpMethod.GET, new URI(url + "?orgname=" + organizerName));

		
		ResponseEntity<AppointmentCreate> response = restTemplate.exchange(requestEntity, AppointmentCreate.class); // printing // without

		AppointmentCreate appCreate = response.getBody();
		
		organizerPendingAppoinmentPrint(appCreate);
		
		return appCreate;
	}
	
	private static void organizerPendingAppoinmentPrint(AppointmentCreate appCreate) {
	
		System.out.println("Printing pulled event from server: " + appCreate.toString());

	}

	

	
}