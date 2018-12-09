package appointer.calendar.event;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

import appointer.util.date.range.IDateRange;
import biweekly.util.Frequency;

/**
 * IEvent is a subset of iCal event. 
 */
public interface IEvent {

	/**
	 * Unique ID for an event.
	 * @return
	 */
	public UUID getUid();

	public void setEventID(UUID uid);

	/**
	 * Should contain the last mutation time.
	 * @return
	 */
	public LocalDateTime getDateTimeStamp();

	public void setEventTimestamp(LocalDateTime timestamp);

	
	public LocalDateTime getDateTimeStart();

	public LocalDateTime getDateTimeEnd();

	public void setTimeStart(LocalDateTime fillTime);

	public void setTimeEnd(LocalDateTime endTime);


	/**
	 * Moves the event's time slot
	 */
	public void adjustEventTime(TemporalAmount offset);

	public void setEventRepeats(Frequency frequency); //coupling to Biweekly again

	public String getOrganizer();

	public void setOrganizer(String organiserName);

	public String getAttendee();

	public void setAttendee(String attendeeName);
	
	/**
	 * Creates immutable IDateRange out of this event. Ignores repeated events. 
	 */
	public IDateRange createDateRange();  // coupling; test what's with repeated events

	/**
	 * Empty constructor for utility;
	 */
	public static IEvent create() {
		return new ControlledEvent();
	}
}
