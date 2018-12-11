package appointer.net.dto;

import appointer.util.date.range.IDateRange;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * dto for appointment creation; appointment is event with Organizer and
 * Attendee;
 *
 */
public abstract class TimedAppointmentDTO extends BaseAppointmentDTO implements IAppointmentDTO {

	@JsonProperty(value = "range", required = true)
	private final IDateRange range;

	public TimedAppointmentDTO(int requestId, IDateRange range, RequestType requestType) {
		super(requestId, requestType);
		this.range = range;
	}

	@Override
	public IDateRange getDateRange() {
		return range;
	}

	@Override
	public String toString() {
		return "TimedAppointmentDTO [requestType=" + getRequestType() + ", Time " + getDateRange().toString()
				+ ", requestId=" + getSequence() + ", timestamp= " + getTimestamp() + ", eventId=" + getEventId()
				+ ", organizer=" + getOrganizer() + ", attendee=" + getAttendee() + ", registered=" + isRegistered()
				+ ", responded=" + isResponded() + ", complete=" + isComplete() + "]";
	}

}
