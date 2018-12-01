package appointer.net.appointclient;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.IAppointmentDTO;

public class UpdateRESTClient {
	
	static RestTemplate restTemplate = new RestTemplate();

	static HttpHeaders headers = new HttpHeaders();

	public static void attendeeUpdateAppointment(IAppointmentDTO appDTO) throws URISyntaxException {
		
		final String url = "http://localhost:8080/attendee/request/update/";

		RequestEntity<IAppointmentDTO> requestEntity = new RequestEntity<IAppointmentDTO>(appDTO, headers,
				HttpMethod.POST, new URI(url));

		ResponseEntity<Boolean> response = restTemplate.exchange(requestEntity, Boolean.class); // printing // without

		Boolean body = response.getBody();

		System.out.println("Printing event creation result on server: " + body + " with ID " + appDTO.getRequestId());

	}

	public static IAppointmentDTO organizerPendingApproval(String organizerName) throws URISyntaxException {
		
		final String url = "http://localhost:8080/organizer/request/update/";
		
		RequestEntity<AppointmentDTO> requestEntity = new RequestEntity<AppointmentDTO>(headers,
				HttpMethod.GET, new URI(url + "?orgname=" + organizerName));

		
		ResponseEntity<IAppointmentDTO> response = restTemplate.exchange(requestEntity, IAppointmentDTO.class); // printing // without

		IAppointmentDTO appDTO = response.getBody();
		
		organizerPendingAppoinmentPrint(appDTO);
		
		return appDTO;
	}
	
	private static void organizerPendingAppoinmentPrint(IAppointmentDTO appDTO) {
		
		System.out.println("Printing pulled event from server: " + appDTO.toString());

	}


}
