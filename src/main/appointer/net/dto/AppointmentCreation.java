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
public class AppointmentCreation {
	
	UUID uid;	
	String organizer;
	String attendee;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime start; 

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime end; 
	
	UUID EventID;
	boolean created;
	boolean approved;
	
}
