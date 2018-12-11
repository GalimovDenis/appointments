
package appointer.calendar.event;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

import appointer.util.date.DateAdapter;
import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.component.VEvent;
import biweekly.property.RecurrenceRule;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;

/**
 * IEvent is a mutable subset of iCal event. 
 * Can be build into immutable ITimeRange and IAppointment events.  
 */
public interface IBuilderEvent {
	
	/**
	 * Should contain the last mutation time.
	 * @return
	 */
	public LocalDateTime getDateTimeStamp();
	
	/**
	 * Unique ID for an event.
	 * @return
	 */
	public UUID getUid();
	
	public LocalDateTime getDateTimeStart();

	public LocalDateTime getDateTimeEnd();
	
	public default IDateRange getDateRange() {
		return new DateRange(getDateTimeStart(), getDateTimeEnd());
	};  

	public Recurrence getEventRepeats();
	
	public String getOrganizer();

	public String getAttendee();

	public void setEventID(UUID uid);

	public void setEventTimestamp(LocalDateTime timestamp);

	public void setTimeStart(LocalDateTime fillTime);

	public void setTimeEnd(LocalDateTime endTime);

	/**
	 * Moves the event's time slot
	 */
	public void adjustEventTime(TemporalAmount offset);

	public void setEventRepeats(Frequency frequency); // coupling to Biweekly again

	public void setOrganizer(String organiserName);

	public void setAttendee(String attendeeName);

	/**
	 * Empty constructor for utility;
	 */
	public static IBuilderEvent create() {
		return new HomebrewEvent();
	}
	

	/**
	 * Produces IBuilderEvent from VEvent;
	 */
	public static IBuilderEvent produce(VEvent event) {
		
		IBuilderEvent builder = create();
		builder.setEventTimestamp(DateAdapter.asLocalDateTime(event.getDateTimeStamp().getValue()));
		builder.setEventID(UUID.fromString(event.getUid().getValue()));
		
		builder.setTimeStart(DateAdapter.asLocalDateTime(event.getDateStart().getValue()));
		builder.setTimeEnd(DateAdapter.asLocalDateTime(event.getDateEnd().getValue()));
		
		builder.setAttendee(event.getAttendees().get(0).getCommonName());
		builder.setOrganizer(event.getOrganizer().getCommonName());
		
		RecurrenceRule recur = event.getRecurrenceRule();
		Frequency frequency = (recur != null ? recur.getValue().getFrequency() : null);
		builder.setEventRepeats(frequency);
		
		return builder;
	}	

	/**
	 * Testing that an event is valid appointment;
	 * 
	 * @return
	 */
	public default boolean isTimeRange() {
		if (getDateTimeStart() == null)
			return false;
		if (getDateTimeEnd() == null)
			return false;
		if (getUid() == null)
			return false;
		return true;
	}

	/**
	 * Testing that an event is valid appointment;
	 * 
	 * @return
	 */
	public default boolean isAppointment() {
		if (!isTimeRange())
			return false;
		if (getAttendee() == null)
			return false;
		if (getOrganizer() == null)
			return false;
		return true;
	}

	/**
	 * Creates an immutable ITimeRangeEvent from the IEvent
	 * 
	 * @return
	 */
	public ITimeRangeEvent buildTimeRange();

	/**
	 * Creates an immutable IAppointmentEvent from the IEvent
	 * 
	 * @return
	 */
	public IAppointmentEvent buildAppointment();

	/**
	 * Test helping method
	 */
	public default IBuilderEvent createTestAppointment() {
		setAttendee("Alyssa");
		setOrganizer("Ben");
		setEventID(UUID.randomUUID());
		return this;
	}

	

}
