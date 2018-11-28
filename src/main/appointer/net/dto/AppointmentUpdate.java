package appointer.net.dto;

import java.util.UUID;

public class AppointmentUpdate extends TimedAppointmentDTO implements IAppointmentDTO {
	
	final RequestType requestType = RequestType.UPDATE;

	public AppointmentUpdate(UUID requestId) {
		super(requestId);
	}
	
	public AppointmentUpdate() {
		super();
	}
	
	@Override
	public RequestType getRequestType() {
		return requestType;
	} 

	
}
