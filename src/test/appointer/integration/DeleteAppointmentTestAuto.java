package appointer.integration;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.junit.Test;

import appointer.calendar.allcalendars.Calendars;
import appointer.util.TestUtil;
import biweekly.component.VEvent;
import biweekly.property.Uid;



public class DeleteAppointmentTestAuto {
	
	private static final int DELETECOUNT = 10;
	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static Calendars AttendeeCalendars = new Calendars(Attendee);
	final static Calendars OrganizerCalendars = new Calendars(Organizer);
	
	
	/**
	 * Tests that 
	 * organizer calendars
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testSingleCreation() throws URISyntaxException {

		VEvent eventToCreateI = TestUtil.createDemoEvent(Attendee, Organizer);
		
		OrganizerCalendars.addEvent(eventToCreateI);
		
		OrganizerCalendars.addEvent(eventToCreateI);

		AttendeeCalendars.addEvent(eventToCreateI);

		VEvent createdEventAttendee = AttendeeCalendars.readEvent(eventToCreateI.getUid());

		VEvent createdEventOrganizer = OrganizerCalendars.readEvent(eventToCreateI.getUid());

		assertTrue(createdEventAttendee.equals(createdEventOrganizer));

		assertTrue(createdEventAttendee.equals(createdEventOrganizer));
		
		assertTrue(AttendeeCalendars.size() == 1);
		
		assertTrue(OrganizerCalendars.size() == 1);
		
		AttendeeCalendars.deleteEvent(eventToCreateI);

		OrganizerCalendars.deleteEvent(eventToCreateI);
		
		assertTrue(AttendeeCalendars.size() == 0);
		
		assertTrue(OrganizerCalendars.size() == 0);


	}
}
