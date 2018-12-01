package appointer.net.dto;

import java.util.UUID;

import com.appointments.util.daterange.IDateRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentUpdate extends TimedAppointmentDTO implements IAppointmentDTO {
	
	final RequestType requestType = RequestType.UPDATE;

	@JsonCreator
	public AppointmentUpdate(@JsonProperty("requestId") UUID requestId, @JsonProperty("range") IDateRange range) {
		super(requestId, range);
	}
	
	@Override
	public RequestType getRequestType() {
		return requestType;
	} 

	
}
