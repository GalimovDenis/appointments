package appointer.net.appointclient;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;

import appointer.net.dto.IAppointmentDTO;

/**
 * Double-CRUD Step number 3. Organizer uploads the changes he did to the
 * server;
 */
public class RESTClient_Step_3_PostChanges {

	static final String url = "http://localhost:8080/organizer/report/";

	public static HttpStatus organizerReportCreate(IAppointmentDTO appEvent) throws URISyntaxException {

		final String urn = "create" + "?orgname=" + appEvent.getOrganizer();

		return RESTExchanger.postAppointmentDTO(appEvent, url + urn);
		

	}
	
	public static HttpStatus organizerReportRead(IAppointmentDTO appEvent) throws URISyntaxException {

		final String urn = "read" + "?orgname=" + appEvent.getOrganizer();

		return RESTExchanger.postAppointmentDTO(appEvent, url + urn);

	}
	
	public static HttpStatus organizerReportUpdate(IAppointmentDTO appEvent) throws URISyntaxException {

		final String urn = "update" + "?orgname=" + appEvent.getOrganizer();

		return RESTExchanger.postAppointmentDTO(appEvent, url + urn);

	}
	
	public static HttpStatus organizerReportDelete(IAppointmentDTO appEvent) throws URISyntaxException {

		final String urn = "delete" + "?orgname=" + appEvent.getOrganizer();

		return RESTExchanger.postAppointmentDTO(appEvent, url + urn);

	}

}
