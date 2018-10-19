package appointer.calendar;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;
import biweekly.property.Organizer;
import biweekly.util.Duration;

public class EventFacade {
	// Facade over VEvent;
	// try event composite class to hold events and call this;

	/**
	 * VEvent is the Biweekly implementation of calendar event; creating VEvent for
	 * now;
	 */
	public static VEvent createEventCurrentTime() {
		VEvent vEventOne = new VEvent();
		LocalDateTime timeNow = LocalDateTime.now();
		vEventOne = EventFacade.setEventStart(vEventOne, timeNow);
		return vEventOne;
	}

	/**
	 * You can't set Event.end when an event has Duration
	 * 
	 * @return
	 */
	public static VEvent addOneHourToEvent(VEvent event) {
		Duration duration = new Duration.Builder().hours(1).build();
		event.setDuration(duration);
		return event;
	}

	/**
	 * 
	 * @param event
	 * @param fillTime
	 * @return
	 */
	public static VEvent setEventStart(VEvent event, LocalDateTime fillTime) {
		Date start = DateAdapter.asDate(fillTime);
		event.setDateStart(start);
		return event;
	}

	public static void setEventEnd(VEvent event, LocalDateTime endTime) {
		// what if the endtime is below starttime?
	}

	public static void rescheduleEvent(VEvent event, Duration duration) {
		// what if other event is there?
	}

	public static void setEventRepeats(VEvent event, Period period) {
		// what if it repeats layer on other events
	}

	/**
	 * @param event
	 *            any VEvent;
	 * @param organiserName
	 *            name of event client
	 * @return new VEvent
	 */
	public static VEvent setOrganiser(VEvent event, String organiserName) {
		event.setOrganizer(new Organizer(organiserName, ""));
		return event;
	}

}
