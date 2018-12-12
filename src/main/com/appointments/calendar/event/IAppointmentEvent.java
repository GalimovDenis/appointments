package com.appointments.calendar.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.appointments.util.date.range.DateRange;
import com.appointments.util.date.range.IDateRange;

import biweekly.util.Recurrence;

/**
 * Guaranteed appointment: daterange, organizer, attendee,
 * uid, timestamp, status
 */
public interface IAppointmentEvent {
	
	public int getSequence();
	
	public String getOrganizer();

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
	
	public String getAttendee();
	
	public AppointmentStatus getStatus();

	public void setStatus(AppointmentStatus status);

}
