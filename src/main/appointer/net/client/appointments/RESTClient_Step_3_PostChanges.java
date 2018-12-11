package appointer.net.client.appointments;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;

import appointer.net.dto.IAppointmentDTO;
import appointer.util.checks.ArgumentsChecker;

/**
 * Double-CRUD Step number 3. Organizer uploads the changes he did to the
 * server;
 */
public final class RESTClient_Step_3_PostChanges {

	static final String url = "http://localhost:8080/organizer/report/";

	public static HttpStatus organizerReport(IAppointmentDTO appDTO) throws URISyntaxException {

		ArgumentsChecker.checkAppDTO(appDTO);

		// produces urn like: create+?orgname=Organizer;
		final String urn = "?orgname=" + appDTO.getOrganizer();
		
		return RESTExchanger.postAppointmentDTO(appDTO, url + urn);
	
	}
	

}
