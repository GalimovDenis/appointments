package appointer.calendar.facades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TimeZone;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import appointer.util.date.DateAdapter;
import appointer.util.date.range.IDateRange;
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.ICalProperty;
import biweekly.property.Organizer;
import biweekly.property.ValuedProperty;
import biweekly.util.Duration;
import biweekly.util.Frequency;
import biweekly.util.ICalDate;
import biweekly.util.Recurrence;
import biweekly.util.com.google.ical.compat.javautil.DateIterator;

public interface EventFacade {

	public static void setEventID(VEvent event, String string) {
		event.setUid(string);
	}

	/**
	 * VEvent is the Biweekly implementation of calendar event; creating VEvent for
	 * now; https://github.com/mangstadt/biweekly biweekly won by comparison to
	 * older iCal4j library; EventFacade is the facade over VEvent + helper methods;
	 */
	public static VEvent createEventCurrentTime() {
		VEvent vEventOne = new VEvent();
		final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		EventFacade.setEventStart(vEventOne, now);
		EventFacade.setEventEnd(vEventOne, now);
		return vEventOne;
	}

	// /**
	// * You can't set Event.end when an event has Duration; therefore it's a good
	// idea to ban Duration;
	// *
	// * @return
	// */
	// public static void addOneHourToEvent(VEvent event) {
	// Duration duration = new Duration.Builder().hours(1).build();
	// event.setDuration(duration);
	// }

	/**
	 * 
	 * @param event
	 * @param timestamp
	 */
	public static void setEventTimestamp(VEvent event, LocalDateTime timestamp) {
		event.setDateTimeStamp(DateAdapter.asDate(timestamp));
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

	/**
	 * 
	 * @param event
	 * @param endTime
	 */
	public static void setEventEnd(VEvent event, LocalDateTime endTime) {
		Date end = DateAdapter.asDate(endTime);
		event.setDateEnd(end);
	}

	/**
	 * 
	 * @param event
	 * @param offset
	 */
	public static void adjustEventTime(VEvent event, TemporalAmount offset) {
		LocalDateTime localDateStart = DateAdapter.asLocalDateTime(event.getDateStart().getValue());
		LocalDateTime localDateEnd = DateAdapter.asLocalDateTime(event.getDateEnd().getValue());
		event.setDateStart(DateAdapter.asDate(localDateStart.plus(offset)));
		event.setDateEnd(DateAdapter.asDate(localDateEnd.plus(offset)));
	}

	/**
	 * 
	 * @param event
	 * @param duration
	 */
	public static void rescheduleEvent(VEvent event, Duration duration) {
		// what if other event is there?
	}

	/**
	 * 
	 * @param event
	 * @param frequency
	 */
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

	public ValuedProperty<String> getDateStart();

	public ValuedProperty<String> getDateEnd();

	public default IDateRange createDateRange() {
			return IDateRange.empty();
	}

	public ICalProperty getOrganizer();

	public LocalDateTime getAttendees();

	public ValuedProperty<ICalDate> getUid();

	public ValuedProperty<ICalDate> getDateTimeStamp();

}
