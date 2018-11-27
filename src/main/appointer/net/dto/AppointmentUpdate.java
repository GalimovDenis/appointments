package appointer.net.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AppointmentUpdate implements IAppointmentDTO {
	
	final RequestType requestType = RequestType.UPDATE;
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
