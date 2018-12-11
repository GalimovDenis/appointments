package appointer.integration;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;

import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IAppointmentEvent;
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
	 * Must have the  server running
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testSingleCreation() throws URISyntaxException {


		IAppointmentEvent eventToCreateI = TestUtil.createDemoEvent(Attendee, Organizer).buildAppointment();

		UUID eventI_UID = TestUtil.createAppointmentTest(eventToCreateI, OrganizerCalendars, AttendeeCalendars);

		IAppointmentEvent createdEventAttendee = AttendeeCalendars.getEvent(eventI_UID);

		System.out.println(createdEventAttendee);
		
		IAppointmentEvent createdEventOrganizer = OrganizerCalendars.getEvent(eventI_UID);

		System.out.println(createdEventOrganizer);
		
		assertTrue(createdEventAttendee.equals(createdEventOrganizer));

	}

	/**
	 * Test that many appointments created produce 
	 * the same event list on attendee
	 * and organizer calendars
	 * 
	 * Must have the server running
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testMultiCreation() throws URISyntaxException {

		for (int i = 0; i < CREATECOUNT; i++) {			
			TestUtil.createAppointmentTest(
					TestUtil.createDemoEvent(Attendee, Organizer).buildAppointment(),
					OrganizerCalendars,
					AttendeeCalendars);
		}

		assertTrue(OrganizerCalendars.equalByEventSet(AttendeeCalendars));

	}

	public static void printAttendeAndOrganizerCalendars() {
		System.out.println(AttendeeCalendars.toString());
		System.out.println(OrganizerCalendars.toString());
	}
}
