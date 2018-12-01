package appointer.net.appointclient;

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.IAppointmentDTO;

public class CompleteRESTClient {
	
	static RestTemplate restTemplate = new RestTemplate();

	static HttpHeaders headers = new HttpHeaders();

	public static void reportComplete (IAppointmentDTO appEvent) throws URISyntaxException {
		
		assertTrue(appEvent.isComplete());
		
		final String url = "http://localhost:8080/attendee/complete";
		
		String uid = appEvent.getRequestId().toString();

		String organizerName = appEvent.getOrganizer();
		
		if (organizerName == "")
			throw new IllegalArgumentException();

		
		RequestEntity<Boolean> requestEntity = new RequestEntity<Boolean>(headers,
				HttpMethod.POST, new URI(url + "?orgname=" + organizerName + "&uid="+uid));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event completion result on server: " + body + " With ID " + appEvent.getRequestId());
	}

}
