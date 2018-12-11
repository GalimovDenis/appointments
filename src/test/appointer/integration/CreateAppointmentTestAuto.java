package appointer.integration;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IAppointmentEvent;
import appointer.calendar.event.IBuilderEvent;
import appointer.net.adapters.DTOAdapter;
import appointer.net.client.appointments.RESTExchanger;
import appointer.util.TestUtil;

/**
 * Integration test for create operation;
 */
public class CreateAppointmentTestAuto {

	private static final int CREATECOUNT = 10;
	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static ICalendars AttendeeCalendars = ICalendars.create(Attendee);
	final static ICalendars OrganizerCalendars = ICalendars.create(Organizer);

	/**
	 * Tests that an appointment created produces the same event on attendee and
	 * organizer calendars
	 * 
	 * Must have the server running
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testSingleCreation() throws URISyntaxException {

		IAppointmentEvent eventToCreateI = IBuilderEvent.create().fillTestAppointment(Attendee, Organizer)
				.buildAppointment();

		UUID eventI_UID = TestUtil.createAppointmentTest(eventToCreateI, OrganizerCalendars, AttendeeCalendars);

		IAppointmentEvent createdEventAttendee = AttendeeCalendars.getEvent(eventI_UID);

		IAppointmentEvent createdEventOrganizer = OrganizerCalendars.getEvent(eventI_UID);

		RESTExchanger.clearServer();
		
		assertTrue(createdEventAttendee.equals(createdEventOrganizer));

	}

	/**
	 * Test that many appointments created produce the same event list on attendee
	 * and organizer calendars
	 * 
	 * Must have the server running
	 * 
	 * @throws URISyntaxException
	 * @throws InterruptedException 
	 */
	@Test
	public void testMultiCreation() throws URISyntaxException, InterruptedException {

		for (int i = 0; i < CREATECOUNT; i++) {
			
			TestUtil.createAppointmentTest(
					IBuilderEvent.create().fillTestAppointment(Attendee, Organizer).buildAppointment(),
					OrganizerCalendars, AttendeeCalendars);
		}

		RESTExchanger.clearServer();
		
		
		assertTrue(OrganizerCalendars.equalByEventSet(AttendeeCalendars));

	}

	@Test
	public void testRegister() throws URISyntaxException {
		IAppointmentEvent eventToCreateI = IBuilderEvent.create().fillTestAppointment(Attendee, Organizer)
				.buildAppointment();
		
		assertTrue(TestUtil.registerEvent(eventToCreateI).equals(HttpStatus.ACCEPTED));
		
		RESTExchanger.clearServer();
		
	}

	@Test
	public void testRegisterTwice() throws URISyntaxException {

		IAppointmentEvent eventToCreateI = IBuilderEvent.create().fillTestAppointment(Attendee, Organizer)
				.buildAppointment();

		assertTrue(TestUtil.registerEvent(eventToCreateI).equals(HttpStatus.ACCEPTED));
		// test case: we try to register event twice;

		assertTrue(TestUtil.registerEvent(eventToCreateI).equals(HttpStatus.ALREADY_REPORTED));
		
		RESTExchanger.clearServer();
		
	}

	@Test
	/**
	 * Succeeds only if server is blank. if there are any appDTO's for this organizer, they'll go first. 
	 * @throws URISyntaxException
	 */
	public void testRespond() throws URISyntaxException {

		IAppointmentEvent eventToCreateI = IBuilderEvent.create().fillTestAppointment(Attendee, Organizer)
				.buildAppointment();

	//	System.out.println("Respond: create" + eventToCreateI);

		TestUtil.registerEvent(eventToCreateI);

		TestUtil.registerEvent(eventToCreateI);

		TestUtil.registerEvent(eventToCreateI);

		IAppointmentEvent eventCreated = DTOAdapter.toAppointmentEvent(TestUtil.respondEvent(OrganizerCalendars));

//		System.out.println("Respond: created" + eventCreated);

		assertTrue(eventCreated.equals(eventToCreateI));

		OrganizerCalendars.deleteEvent(eventCreated); //TODO: clear calendar;
		
		RESTExchanger.clearServer();
		
	}

	public static void printAttendeAndOrganizerCalendars() {
		System.out.println(AttendeeCalendars.toString());
		System.out.println(OrganizerCalendars.toString());
	}
}
