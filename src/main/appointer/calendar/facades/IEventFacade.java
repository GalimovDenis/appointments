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
import biweekly.component.VEvent;
import biweekly.property.Attendee;
import biweekly.property.Organizer;
import biweekly.util.Duration;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;
import biweekly.util.com.google.ical.compat.javautil.DateIterator;

public interface IEventFacade {
	
	public abstract void setEventID(VEvent event, String string);

	/**
	 * VEvent is the Biweekly implementation of calendar event; creating VEvent for
	 * now; https://github.com/mangstadt/biweekly biweekly won by comparison to
	 * older iCal4j library; EventFacade is the facade over VEvent + helper methods;
	 */
	public VEvent createEventCurrentTime() ;

//	/**
//	 * You can't set Event.end when an event has Duration; therefore it's a good idea to ban Duration;
//	 * 
//	 * @return
//	 */
//	public static void addOneHourToEvent(VEvent event) {
//		Duration duration = new Duration.Builder().hours(1).build();
//		event.setDuration(duration);
//	}

	/**
	 * 
	 * @param event
	 * @param timestamp
	 */
	public void setEventTimestamp(VEvent event, LocalDateTime timestamp) ;

	/**
	 * 
	 * @param event
	 * @param fillTime
	 * @return
	 */
	public void setEventStart(VEvent event, LocalDateTime fillTime);
	
	/**
	 * 
	 * @param event
	 * @param endTime
	 */
	public void setEventEnd(VEvent event, LocalDateTime endTime);


	/**
	 * 
	 * @param event
	 * @param offset
	 */
	public  void adjustEventTime(VEvent event, TemporalAmount offset); 	


	/**
	 * 
	 * @param event
	 * @param duration
	 */
	public void rescheduleEvent(VEvent event, Duration duration) ;
	/**
	 * 
	 * @param event
	 * @param frequency
	 */
	public void setEventRepeats(VEvent event, Frequency frequency); 
	/**
	 * @param event         any VEvent;
	 * @param organiserName name of event client
	 * @return new VEvent
	 */
	public void setOrganiser(VEvent event, String organiserName);
	/**
	 * 
	 * @param event
	 * @param attendeeName
	 * @return
	 */
	public void addAttendee(VEvent event, String attendeeName) ;

	/**
	 * @param event
	 * @return stream of localdates for recurring event
	 */
	public Stream<LocalDate> getLocalDateStream(VEvent event);
}