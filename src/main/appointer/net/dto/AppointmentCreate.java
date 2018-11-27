package appointer.net.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
@Data
public class AppointmentCreate implements IAppointmentDTO {

	final RequestType requestType = RequestType.CREATE;
	UUID requestId;
	String eventId; 
	String organizer;
	String attendee;
	boolean registered;
	boolean responded;
	boolean complete;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime start; 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime end; 
	
}
