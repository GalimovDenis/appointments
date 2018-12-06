package appointer.integration;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.junit.Test;

import appointer.calendar.allcalendars.Calendars;
import appointer.util.TestUtil;
import appointer.util.io.console.CalendarPrinter;
import biweekly.component.VEvent;
import biweekly.property.Uid;

/**
 * Integration test for create operation;
 */
public class CreateAppointmentTestAuto {

	private static final int CREATECOUNT = 10;
	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static Calendars AttendeeCalendars = new Calendars(Attendee);
	final static Calendars OrganizerCalendars = new Calendars(Organizer);

	/**
	 * Tests that an appointment created produces the same event on attendee and
	 * organizer calendars
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testSingleCreation() throws URISyntaxException {

		VEvent eventToCreateI = TestUtil.createDemoEvent(Attendee, Organizer);

		Uid eventI_UID = TestUtil.createAppointmentTest(eventToCreateI, OrganizerCalendars, AttendeeCalendars);

		VEvent createdEventAttendee = AttendeeCalendars.readEvent(eventI_UID);

		VEvent createdEventOrganizer = OrganizerCalendars.readEvent(eventI_UID);

		printAttendeAndOrganizerCalendars();

		assertTrue(createdEventAttendee.equals(createdEventOrganizer));

	}

	/**
	 * Test that many appointments created produces the same event list on attendee
	 * and organizer calendars
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testMultiCreation() throws URISyntaxException {

		for (int i = 0; i < CREATECOUNT; i++) {

			TestUtil.createAppointmentTest(TestUtil.createDemoEvent(Attendee, Organizer), OrganizerCalendars,
					AttendeeCalendars);

		}

		assertTrue(AttendeeCalendars.getLocalCalendar().getEvents()
				.equals(OrganizerCalendars.getLocalCalendar().getEvents()));

	}

	public static void printAttendeAndOrganizerCalendars() {
		System.out.println("Attendee " + Attendee);
		CalendarPrinter.printCalendar(AttendeeCalendars.getLocalCalendar());
		System.out.println("Organizer " + Organizer);
		CalendarPrinter.printCalendar(OrganizerCalendars.getLocalCalendar());
	}
}
