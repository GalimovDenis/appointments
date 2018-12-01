package appointer.net.dto;

import java.util.UUID;

import com.appointments.util.daterange.IDateRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
public class AppointmentCreate extends TimedAppointmentDTO implements IAppointmentDTO {

	final RequestType requestType = RequestType.CREATE;
	
	@JsonCreator
	public AppointmentCreate(@JsonProperty("requestId") UUID requestId, @JsonProperty("range") IDateRange range) {
		super(requestId, range);
	}

	@Override
	public RequestType getRequestType() {
		return requestType;
	}


	
	
}
