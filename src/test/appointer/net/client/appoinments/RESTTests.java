package appointer.net.client.appoinments;

import java.net.URISyntaxException;

import org.junit.Test;

import appointer.calendar.allcalendars.Calendars;
import appointer.net.client.appointments.RESTClient_Step_1_PostNewRequest;
import appointer.net.client.appointments.RESTClient_Step_2_GetPending;
import appointer.net.client.appointments.RESTClient_Step_3_PostChanges;


// need to refactor the tested classes because in most 4 methods can be rewritten into one and 9 tests are much better than 33;
/**
 * Testing net.client.appointments for fail-fast behavior; 
 */
public class RESTTests {
	
	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static Calendars AttendeeCalendars = new Calendars(Attendee);
	final static Calendars OrganizerCalendars = new Calendars(Organizer);
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIC() throws URISyntaxException {
		RESTClient_Step_1_PostNewRequest.attendeeRequest(null);
	}
	

	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIC() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyIIC() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetRequest(null, "");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIIC() throws URISyntaxException {
		RESTClient_Step_3_PostChanges.organizerReport(null);
	}
	
	
	
}
