package appointer.calendar.facades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TimeZone;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;
import biweekly.property.Uid;
import biweekly.util.Duration;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;
import biweekly.util.com.google.ical.compat.javautil.DateIterator;

public class EventFacade {

	public static void setEventID(VEvent event, Uid uid) {
		event.setUid(uid);
	}

	// is that a future event composite class to hold events and call groups of
	// them?
	/**
	 * VEvent is the Biweekly implementation of calendar event; creating VEvent for
	 * now; https://github.com/mangstadt/biweekly biweekly won by comparison to
	 * older iCal4j library; EventFacade is the facade over VEvent + helper methods;
	 */
	public static VEvent createEventCurrentTime() {
		VEvent vEventOne = new VEvent();
		LocalDateTime timeNow = LocalDateTime.now();
		EventFacade.setEventStart(vEventOne, timeNow);
		EventFacade.setEventEnd(vEventOne, timeNow);
		return vEventOne;
	}

	/**
	 * You can't set Event.end when an event has Duration
	 * 
	 * @return
	 */
	public static void addOneHourToEvent(VEvent event) {
		Duration duration = new Duration.Builder().hours(1).build();
		event.setDuration(duration);
	}

	/**
	 * 
	 * @param event
	 * @param fillTime
	 * @return
	 */
	public static void setEventStart(VEvent event, LocalDateTime fillTime) {
		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);
	}

	public static void moveEventStart(VEvent event, TemporalAmount offset) {
		LocalDateTime localDateStart = DateAdapter.asLocalDateTime(event.getDateStart().getValue());
		localDateStart.plus(offset);
		event.setDateStart(DateAdapter.asDate(localDateStart));
	}

	public static void setEventEnd(VEvent event, LocalDateTime endTime) {
		Date end = DateAdapter.asDate(endTime);
		event.setDateEnd(end);
	}

	public static void rescheduleEvent(VEvent event, Duration duration) {
		// what if other event is there?
	}

	public static void setEventRepeats(VEvent event, Frequency frequency) {
		Recurrence recur = new Recurrence.Builder(frequency).build();
		event.setRecurrenceRule(recur);
	}

	/**
	 * @param event
	 *            any VEvent;
	 * @param organiserName
	 *            name of event client
	 * @return new VEvent
	 */
	public static void setOrganiser(VEvent event, String organiserName) {
		event.setOrganizer(new Organizer(organiserName, ""));
	}

	/**
	 * 
	 * @param event
	 * @param attendeeName
	 * @return
	 */
	public static void addAttendee(VEvent event, String attendeeName) {
		event.addAttendee(new Attendee(attendeeName, ""));
	}

	/**
	 * @param event
	 * @return stream of localdates for recurring event
	 */
	public static Stream<LocalDate> getLocalDateStream(VEvent event) {
		DateIterator dates = event.getDateIterator(TimeZone.getDefault());
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(dates, Spliterator.ORDERED), false)
				.map(DateAdapter::asLocalDate);
	}

}
