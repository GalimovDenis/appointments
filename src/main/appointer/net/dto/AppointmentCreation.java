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
	
	UUID uid;	 /// ID of the application's request;
	String organizer;
	String attendee;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime start; 

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime end; 
	
	String  EventID; //ID of the event payload;
	// biWeekly's Uid can't be deserialised easily;
	//https://stackoverflow.com/questions/47570931/jackson-deserialize-class-with-private-fields-and-arg-constructor-without-annot?rq=1
	
	boolean created;
	boolean approved;
	
}
