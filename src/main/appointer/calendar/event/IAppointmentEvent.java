package appointer.calendar.event;

/**
 * Guaranteed appointment: daterange, organizer, attendee,
 * uid, timestamp, status
 */
public interface IAppointmentEvent extends ITimeRangeEvent {
	
	public String getOrganizer();
	
	public default String getOwner() {return getAttendee();};

	public String getAttendee();
	
	public AppointmentStatus getStatus();

	public void setStatus(AppointmentStatus status);

}
