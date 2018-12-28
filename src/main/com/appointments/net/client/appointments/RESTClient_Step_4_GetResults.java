package com.appointments.net.client.appointments;

import java.net.URISyntaxException;
import java.util.UUID;

import com.appointments.net.dto.IAppointmentDTO;
import com.appointments.util.checks.ArgumentsChecker;

/**
 * Double-CRUD Step number 4. Attendee downloads the changes from to the server;
 *
 */
public final class RESTClient_Step_4_GetResults {

	static final String url = "http://localhost:8080/attendee/report/";

	public static IAppointmentDTO attendeeReceiveReport(String organizerName, UUID uid, int sequence)
			throws URISyntaxException {

		ArgumentsChecker.checkStringNotEmpty(organizerName, "organizerName");

		ArgumentsChecker.checkNotNull(uid, "UID");

		// produces urn like: ?orgname"+Organizer+"&uid="+uid...
		final String urn = "?orgname=" + organizerName + "&uid=" + uid.toString() + "&sequence=" + sequence;

		return RESTExchanger.getAppointmentDTO(url + urn);
	}
}
