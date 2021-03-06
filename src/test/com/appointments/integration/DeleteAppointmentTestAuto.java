package com.appointments.integration;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import org.junit.Test;

import com.appointments.calendar.calendars.ICalendars;
import com.appointments.calendar.event.IAppointmentEvent;
import com.appointments.calendar.event.IEventBuilder;	



public class DeleteAppointmentTestAuto {

	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static ICalendars AttendeeCalendars = ICalendars.create(Attendee);
	final static ICalendars OrganizerCalendars = ICalendars.create(Organizer);
	
	
	/**
	 * Tests create/delete event in the calendar;
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testSingleDeletion() throws URISyntaxException {

		IAppointmentEvent eventToCreateI = IEventBuilder.create().fillTestAppointment(Attendee, Organizer).buildAppointment();
		
		OrganizerCalendars.putEvent(eventToCreateI);
		
		OrganizerCalendars.putEvent(eventToCreateI);

		AttendeeCalendars.putEvent(eventToCreateI);

		IAppointmentEvent createdEventAttendee = AttendeeCalendars.getEvent(eventToCreateI.getUid());

		IAppointmentEvent createdEventOrganizer = OrganizerCalendars.getEvent(eventToCreateI.getUid());
		
		assertTrue(createdEventAttendee.equals(createdEventOrganizer));

		assertTrue(createdEventAttendee.equals(createdEventOrganizer));
		
		assertTrue(AttendeeCalendars.getUids().size() == 1);
		
		assertTrue(OrganizerCalendars.getUids().size() == 1);
		
		AttendeeCalendars.deleteEvent(eventToCreateI);

		OrganizerCalendars.deleteEvent(eventToCreateI);
		
		assertTrue(AttendeeCalendars.getUids().size() == 0);
		
		assertTrue(OrganizerCalendars.getUids().size() == 0);


	}
}
