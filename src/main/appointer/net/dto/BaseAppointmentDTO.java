package appointer.net.dto;

import java.util.UUID;

public abstract class BaseAppointmentDTO implements IAppointmentDTO {

	UUID requestId;
	String eventId; 
	String organizer;
	String attendee;
	

	boolean registered;
	boolean responded;
	boolean complete;
	
	
	
	
	@Override
	public abstract RequestType getRequestType();
	
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
		return "BaseAppointmentDTO [requestType=" + getRequestType() + ", requestId=" + requestId + ", eventId=" + eventId + ", organizer=" + organizer
				+ ", attendee=" + attendee + ", registered=" + registered + ", responded=" + responded + ", complete="
				+ complete + "]";
	}

	public BaseAppointmentDTO() {
		super();
	}
	
	public BaseAppointmentDTO(UUID requestId) {
		super();
		this.requestId = requestId;
	}

	public UUID getRequestId() {
		return requestId;
	}
	
	public void setRequestId(UUID requestId) {
		this.requestId = requestId;
	}
	
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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



	
}
