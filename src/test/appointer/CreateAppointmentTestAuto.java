package appointer;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;

import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IEvent;
import appointer.util.AppointerUtil;

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
	 * @throws URISyntaxException
	 */
	@Test
	public void testSingleCreation() throws URISyntaxException {

		IEvent eventToCreateI = AppointerUtil.createDemoEvent(Attendee, Organizer);

		UUID eventI_UID = AppointerUtil.createAppointmentTest(eventToCreateI, OrganizerCalendars, AttendeeCalendars);

		IEvent createdEventAttendee = AttendeeCalendars.getEvent(eventI_UID);

		IEvent createdEventOrganizer = OrganizerCalendars.getEvent(eventI_UID);

		assertTrue(createdEventAttendee.equals(createdEventOrganizer));

	}

	/**
	 * Test that many appointments created produce 
	 * the same event list on attendee
	 * and organizer calendars
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testMultiCreation() throws URISyntaxException {

		for (int i = 0; i < CREATECOUNT; i++) {
			
			AppointerUtil.createAppointmentTest(
					AppointerUtil.createDemoEvent(Attendee, Organizer),
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
