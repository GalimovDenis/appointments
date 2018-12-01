package appointer.net.dto;

import java.util.UUID;

import com.appointments.util.daterange.IDateRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppointmentDelete extends TimedAppointmentDTO  implements IAppointmentDTO {
	
	final RequestType requestType = RequestType.DELETE;
	
	@JsonCreator
	public AppointmentDelete(@JsonProperty("requestId") UUID requestId, @JsonProperty("range") IDateRange range) {
		super(requestId, range);
	}
	
	
	@Override
	public RequestType getRequestType() {
		return requestType;
	} 
	
}
