package appointer.net.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class AppointmentDelete  implements IAppointmentDTO {
	
	final RequestType requestType = RequestType.DELETE;
	UUID requestId;
	String eventId; 
	String organizer;
	String attendee;
	boolean registered;
	boolean responded;
	boolean complete;
	
}
