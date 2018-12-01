package appointer.net.dto;

import java.util.UUID;

import com.appointments.util.daterange.IDateRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
public abstract class TimedAppointmentDTO extends BaseAppointmentDTO implements IAppointmentDTO {
	
	@JsonProperty(value="range", required=true)
	private final IDateRange range;
	
	@Override
	public abstract RequestType getRequestType();
	
	@JsonCreator
	public TimedAppointmentDTO(@JsonProperty("requestId") UUID requestId, @JsonProperty("range") IDateRange range) {
		super(requestId);
		this.range = range;
	}
	
	@Override
	public IDateRange getDateRange() {
		return range;
	}
	
	@Override
	public String toString() {
		return "TimedAppointmentDTO [requestType=" + getRequestType() + ", Time " + getDateRange().toString() + ", requestId="
				+ getRequestId() + ", eventId=" + getEventId() + ", organizer=" + getOrganizer() + ", attendee=" + getAttendee()
				+ ", registered=" + isRegistered() + ", responded=" + isResponded() + ", complete=" + isComplete() + "]";
	}


	
	
}
