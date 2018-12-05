package appointer.net.client.appointments;

import java.net.URISyntaxException;
import java.util.UUID;

import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.checks.ArgumentsChecker;

/**
 * Double-CRUD Step number 4. Attendee downloads the changes from to the server;
 *
 */
public final class RESTClient_Step_4_GetResults {

	static final String url = "http://localhost:8080/attendee/report/";

	public static IAppointmentDTO attendeeReceiveReport(RequestType type, String organizerName, UUID uid)
			throws URISyntaxException {

		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");

		ArgumentsChecker.checkNotNull(type, "RequestType");
		
		ArgumentsChecker.checkNotNull(uid, "UID");

		// produces urn like: create+"/"+"?orgname"+Organizer+"&uid="+uid...
		final String urn = type.getURN() + "/" + "?orgname=" + organizerName + "&uid=" + uid.toString();

		return RESTExchanger.getAppointmentDTO(url + urn);
	}
}
