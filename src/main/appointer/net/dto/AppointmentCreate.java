package appointer.net.dto;

import java.util.UUID;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
public class AppointmentCreate extends TimedAppointmentDTO implements IAppointmentDTO {

	final RequestType requestType = RequestType.CREATE;
	
	public AppointmentCreate(UUID requestId) {
		super(requestId);
	}

	public AppointmentCreate() {
		super();
	}
	
	@Override
	public RequestType getRequestType() {
		return requestType;
	} 
	
	
}
