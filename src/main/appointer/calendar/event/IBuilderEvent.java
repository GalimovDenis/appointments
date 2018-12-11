package appointer.calendar.event;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

import biweekly.util.Frequency;

/**
 * IEvent is a mutable subset of iCal event. 
 * Can be build into immutable ITimeRange and IAppointment events.  
 */
public interface IBuilderEvent extends IAppointmentEvent {

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

	/**
	 * IEvent can have only NEW status, because it's not an Appointment yet;
	 */
	public default AppointmentStatus getStatus() {
		return AppointmentStatus.NEW;
	}

	/**
	 * IEvent is not yet an appointment, therefore you can't set its status;
	 */
	public default void setStatus(AppointmentStatus status) {
		throw new UnsupportedOperationException();
	}

}
