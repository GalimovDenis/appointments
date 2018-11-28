package appointer.net.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
public abstract class TimedAppointmentDTO extends BaseAppointmentDTO implements IAppointmentDTO {
	
	public TimedAppointmentDTO(UUID requestId) {
		super(requestId);
	}
	
	public TimedAppointmentDTO() {
		super();
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime start; 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime end;

	@Override
	public abstract RequestType getRequestType();
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "TimedAppointmentDTO [requestType=" + getRequestType() + ", start=" + start + ", end=" + end + ", requestId="
				+ requestId + ", eventId=" + eventId + ", organizer=" + organizer + ", attendee=" + attendee
				+ ", registered=" + registered + ", responded=" + responded + ", complete=" + complete + "]";
	}


	
	
}
