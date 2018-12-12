package com.appointments.net.dto;

import java.time.LocalDateTime;

import com.appointments.util.date.range.IDateRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * dto for appointment creation; appointment is event with Organizer and
 * Attendee;
 *
 */
public class AppointmentDTO implements IAppointmentDTO {

	/**
	 * Data for handling the AppointmentDTO: its CRUD type and its unique ID
	 */
	private final RequestType requestType;
	private final int sequence;
	/**
	 * unique Event ID by which to search for it in the calendars
	 */
	private String eventId;

	/**
	 * time of last change for the associated Event Must be the same across devices
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;

	/**
	 * Organizer and attendee of event. Strictly one of each.
	 */
	private String organizer;
	private String attendee;

	/**
	 * Data that describes which stages in the appointment creation process are
	 * passed;
	 */
	private boolean registered;
	private boolean responded;
	private boolean complete;

	@JsonProperty(value = "range", required = true)
	private final IDateRange range;

	@JsonCreator
	public AppointmentDTO(@JsonProperty("requestId") int requestId, @JsonProperty("range") IDateRange range,
			@JsonProperty("requestType") RequestType requestType) {
		super();
		this.sequence = requestId;
		this.requestType = requestType;
		this.range = range;
	}

	/**
	 * Constructor for defensive copying
	 * 
	 * @param appDTO
	 */
	public AppointmentDTO(IAppointmentDTO appDTO) {
		this(appDTO.getSequence(), appDTO.getDateRange(), appDTO.getRequestType());
		this.setAttendee(appDTO.getAttendee());
		this.setOrganizer(appDTO.getOrganizer());
		this.setEventId(appDTO.getEventId());
		this.setRegistered(appDTO.isRegistered());
		this.setResponded(appDTO.isResponded());
		this.setComplete(appDTO.isComplete());
	}

	@Override
	public RequestType getRequestType() {
		return requestType;
	}

	@Override
	public Integer getSequence() {
		return sequence;
	}

	@Override
	public String getEventId() {
		return eventId;
	}

	@Override
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	@Override
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@Override
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String getOrganizer() {
		return organizer;
	}

	@Override
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	@Override
	public String getAttendee() {
		return attendee;
	}

	@Override
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	@Override
	public boolean isRegistered() {
		return registered;
	}

	@Override
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	@Override
	public boolean isResponded() {
		return responded;
	}

	@Override
	public void setResponded(boolean responded) {
		this.responded = responded;
	}

	@Override
	public boolean isComplete() {
		return complete;
	}

	@Override
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public IDateRange getDateRange() {
		return range;
	}

	@Override
	public String toString() {
		return "AppointmentDTO [requestType=" + getRequestType() + ", Time " + getDateRange().toString()
				+ ", requestId=" + getSequence() + ", timestamp= " + getTimestamp() + ", eventId=" + getEventId()
				+ ", organizer=" + getOrganizer() + ", attendee=" + getAttendee() + ", registered=" + isRegistered()
				+ ", responded=" + isResponded() + ", complete=" + isComplete() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendee == null) ? 0 : attendee.hashCode());
		result = prime * result + (complete ? 1231 : 1237);
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((organizer == null) ? 0 : organizer.hashCode());
		result = prime * result + ((range == null) ? 0 : range.hashCode());
		result = prime * result + (registered ? 1231 : 1237);
		result = prime * result + sequence;
		result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
		result = prime * result + (responded ? 1231 : 1237);
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentDTO other = (AppointmentDTO) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (registered != other.registered)
			return false;
		if (sequence != other.sequence)
			return false;
		if (requestType != other.requestType)
			return false;
		if (responded != other.responded)
			return false;
		if (attendee == null) {
			if (other.attendee != null)
				return false;
		} else if (!attendee.equals(other.attendee))
			return false;
		if (complete != other.complete)
			return false;
		if (organizer == null) {
			if (other.organizer != null)
				return false;
		} else if (!organizer.equals(other.organizer))
			return false;
		if (range == null) {
			if (other.range != null)
				return false;
		} else if (!range.equals(other.range))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
