package appointer.net.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BaseAppointmentDTO implements IAppointmentDTO {

	/**
	 * Data for handling the AppointmentDTO: its CRUD type and its unique ID
	 */
	private final RequestType requestType;
	private final UUID requestId;

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

	@JsonCreator
	public BaseAppointmentDTO(@JsonProperty("requestId") UUID requestId,
			@JsonProperty("requestType") RequestType requestType) {
		super();
		this.requestId = requestId;
		this.requestType = requestType;
	}

	@Override
	public RequestType getRequestType() {
		return requestType;
	}

	public UUID getRequestId() {
		return requestId;
	}

	public String getEventId() {
		return eventId;
	}

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

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public boolean isResponded() {
		return responded;
	}

	public void setResponded(boolean responded) {
		this.responded = responded;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
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
		BaseAppointmentDTO other = (BaseAppointmentDTO) obj;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseAppointmentDTO [requestType=" + getRequestType() + ", requestId=" + requestId + ", eventId="
				+ eventId + ", timestamp="+ timestamp +", organizer=" + organizer + ", attendee=" + attendee + ", registered=" + registered
				+ ", responded=" + responded + ", complete=" + complete + "]";
	}

}
