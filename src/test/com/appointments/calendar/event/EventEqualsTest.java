
package com.appointments.calendar.event;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

import org.junit.Test;

import com.appointments.calendar.event.IAppointmentEvent;
import com.appointments.calendar.event.IEventBuilder;

public class EventEqualsTest {

	final static String Attendee = "Alyssa_P._Hacker";
	final static String Organizer = "Ben_Bitdiddle";
	final static LocalDateTime timeNow = LocalDateTime.now();

	final static LocalDateTime timeStart = LocalDateTime.of(2018, Month.DECEMBER, 31, 19, 0, 0);
	final static LocalDateTime timeFinish = LocalDateTime.of(2018, Month.DECEMBER, 31, 20, 0, 0);
	final static UUID uid = UUID.randomUUID();

	@Test
	public void testIEventEquals() throws InterruptedException {

		IEventBuilder eventA = IEventBuilder.create();
		Thread.sleep(500); // If events have different timestamp, they are not equal.
		IEventBuilder eventB = IEventBuilder.create();

		// therefore we set timestamp as of now;
		eventA.setEventTimestamp(timeNow);
		eventB.setEventTimestamp(timeNow);

		eventA.setEventID(uid);
		eventB.setEventID(uid);

		eventA.setOrganizer(Organizer);
		eventB.setOrganizer(Organizer);

		eventA.setAttendee(Attendee);
		eventB.setAttendee(Attendee);

		eventA.setTimeStart(timeStart);
		eventB.setTimeStart(timeStart);

		eventA.setTimeEnd(timeFinish);
		eventB.setTimeEnd(timeFinish);

		assertTrue(eventA.equals(eventB));
	}

	@Test
	public void testIAppointmentEquals() throws InterruptedException {

		IEventBuilder eventA = IEventBuilder.create();
		Thread.sleep(500); // If events have different timestamp, they are not equal.
		IEventBuilder eventB = IEventBuilder.create();

		eventA.setEventTimestamp(timeNow);
		eventB.setEventTimestamp(timeNow);
		eventA.setEventID(uid);
		eventB.setEventID(uid);
		eventA.setOrganizer(Organizer);
		eventB.setOrganizer(Organizer);
		eventA.setAttendee(Attendee);
		eventB.setAttendee(Attendee);
		eventA.setTimeStart(timeStart);
		eventB.setTimeStart(timeStart);
		eventA.setTimeEnd(timeFinish);
		eventB.setTimeEnd(timeFinish);

		IAppointmentEvent appEventA = eventA.buildAppointment();
		IAppointmentEvent appEventB = eventB.buildAppointment();
		
		assertTrue(appEventA.equals(appEventB));
	}
}
