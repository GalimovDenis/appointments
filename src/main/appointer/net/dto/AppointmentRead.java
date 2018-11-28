package appointer.net.dto;

import java.util.UUID;

public class AppointmentRead extends TimedAppointmentDTO implements IAppointmentDTO {
	
	final RequestType requestType = RequestType.READ;
	
	public AppointmentRead(UUID requestId) {
		super(requestId);
	}
	
	public AppointmentRead() {
		super();
	}
	
	@Override
	public RequestType getRequestType() {
		return requestType;
	} 
	
}
