package appointer.net.client.appoinments;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;

import appointer.calendar.calendars.ICalendars;
import appointer.net.client.appointments.RESTClient_Step_1_PostNewRequest;
import appointer.net.client.appointments.RESTClient_Step_2_GetPending;
import appointer.net.client.appointments.RESTClient_Step_3_PostChanges;
import appointer.net.client.appointments.RESTClient_Step_4_GetResults;
import appointer.net.client.appointments.RESTClient_Step_5_PostComplete;
import appointer.net.dto.RequestType;

/**
 * ` Testing net.client.appointments for fail-fast behavior;
 */
public class RESTClientFailFast {

	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static ICalendars AttendeeCalendars = ICalendars.create(Attendee);
	final static ICalendars OrganizerCalendars = ICalendars.create(Organizer);

	@Test(expected = IllegalArgumentException.class)
	public void testINull() throws URISyntaxException {
		RESTClient_Step_1_PostNewRequest.attendeeRequest(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIIINull() throws URISyntaxException {
		RESTClient_Step_3_PostChanges.organizerReport(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVNull() throws URISyntaxException {
		RESTClient_Step_5_PostComplete.attendeeConfirmComplete(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIIFirstArgNull() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(null, Organizer);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIISecondArgNull() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.READ, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIISecondArgEmpty() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(RequestType.READ, "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIVFirstArgNull() throws URISyntaxException {
		RESTClient_Step_4_GetResults.attendeeReceiveReport(null, Organizer, UUID.randomUUID());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIVSecondArgNull() throws URISyntaxException {
		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.READ, null, UUID.randomUUID());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIVSecondArgEmpty() throws URISyntaxException {
		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.READ, "", UUID.randomUUID());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIVThirfArgNull() throws URISyntaxException {
		RESTClient_Step_4_GetResults.attendeeReceiveReport(RequestType.READ, "", null);
	}

}
