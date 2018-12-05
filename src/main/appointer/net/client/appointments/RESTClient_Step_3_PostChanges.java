package appointer.net.client.appointments;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;

import appointer.net.dto.IAppointmentDTO;
import appointer.util.checks.ArgumentsChecker;

//see step 1 for refactoring
/**
 * Double-CRUD Step number 3. Organizer uploads the changes he did to the
 * server;
 */
public class RESTClient_Step_3_PostChanges {

	static final String url = "http://localhost:8080/organizer/report/";

	public static HttpStatus organizerReport(IAppointmentDTO appDTO) throws URISyntaxException {

		ArgumentsChecker.checkAppDTO(appDTO);

		// produces urn like: create+?orgname=Organizer;
		final String urn = appDTO.getRequestType().getURN() + "?orgname=" + appDTO.getOrganizer();
		
		return RESTExchanger.postAppointmentDTO(appDTO, url + urn);
	
	}
	

}
