package appointer.calendar.facades;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.UUID;
import java.util.stream.Stream;

import appointer.util.date.range.IDateRange;
import biweekly.util.Frequency;

/**
 * VEvent is the Biweekly implementation of calendar event; creating VEvent for
 * now; https://github.com/mangstadt/biweekly biweekly won by comparison to
 * older iCal4j library; EventFacade is the facade over VEvent + helper methods;
 */
public interface IEvent {

	public void setEventID(UUID uid);

	/**
	 * 
	 * @param event
	 * @param timestamp
	 */
	public void setEventTimestamp(LocalDateTime timestamp);

	/**
	 * 
	 * @param event
	 * @param fillTime
	 * @return
	 */
	public void setTimeStart(LocalDateTime fillTime);
	// {
	// Date start = DateAdapter.asDate(fillTime);
	// event.setDateStart(start);
	// }

	/**
	 * 
	 * @param event
	 * @param endTime
	 */
	public void setTimeEnd(LocalDateTime endTime);

	/**
	 * 
	 * @param event
	 * @param offset
	 */
	public void adjustEventTime(TemporalAmount offset);

	/**
	 * 
	 * @param event
	 * @param frequency
	 */
	public void setEventRepeats(Frequency frequency);

	/**
	 * @param event
	 *            any VEvent;
	 * @param organiserName
	 *            name of event client
	 * @return new VEvent
	 */
	public void setOrganizer(String organiserName);

	/**
	 * 
	 * @param event
	 * @param attendeeName
	 * @return
	 */
	public void setAttendee(String attendeeName);

	/**
	 * @param event
	 * @return stream of localdates for recurring event
	 */
	public Stream<LocalDateTime> getLocalDateStream();

	public LocalDateTime getDateStart();

	public LocalDateTime getDateEnd();

	public IDateRange createDateRange(); 

	public String getOrganizer();

	public String getAttendee();

	public UUID getUid();

	public LocalDateTime getDateTimeStamp();

	public static IEvent create() {

		return new ControlledEvent();

	}

}
