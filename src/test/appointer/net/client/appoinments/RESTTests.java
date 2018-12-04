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
		RESTClient_Step_1_PostNewRequest.attendeeRequestCreate(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIR() throws URISyntaxException {
		RESTClient_Step_1_PostNewRequest.attendeeRequestRead(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIU() throws URISyntaxException {
		RESTClient_Step_1_PostNewRequest.attendeeRequestUpdate(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullID() throws URISyntaxException {
		RESTClient_Step_1_PostNewRequest.attendeeRequestDelete(null);
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIC() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetCreateRequest(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIR() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetReadRequest(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIU() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetUpdateRequest(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIID() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetDeleteRequest(null);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyIIC() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetCreateRequest("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyIIR() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetReadRequest("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyIIU() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetUpdateRequest("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyIID() throws URISyntaxException {
		RESTClient_Step_2_GetPending.organizerGetDeleteRequest("");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIIC() throws URISyntaxException {
		RESTClient_Step_3_PostChanges.organizerReportCreate(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIIR() throws URISyntaxException {
		RESTClient_Step_3_PostChanges.organizerReportRead(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIIU() throws URISyntaxException {
		RESTClient_Step_3_PostChanges.organizerReportUpdate(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullIIID() throws URISyntaxException {
		RESTClient_Step_3_PostChanges.organizerReportDelete(null);
	}
	
	


	
	
}
