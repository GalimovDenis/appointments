package appointer.net.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import appointer.util.date.range.IDateRange;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
public class AppointmentDTO extends TimedAppointmentDTO implements IAppointmentDTO {
	
	@JsonCreator
	public AppointmentDTO(@JsonProperty("requestId") UUID requestId, 
			@JsonProperty("range") IDateRange range,
			@JsonProperty("requestType") RequestType requestType) {
		super(requestId, range, requestType);
	}
	
}
