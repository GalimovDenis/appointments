package appointer.net.dto;

import java.util.UUID;

public class AppointmentDelete extends BaseAppointmentDTO  implements IAppointmentDTO {
	
	final RequestType requestType = RequestType.DELETE;
	
	public AppointmentDelete(UUID requestId) {
		super(requestId);
	}
	
	public AppointmentDelete() {
		super();
	}
	
	@Override
	public RequestType getRequestType() {
		return requestType;
	} 
	
}
