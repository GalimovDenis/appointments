package appointer.net.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import appointer.util.date.range.IDateRange;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
public class AppointmentDTO extends TimedAppointmentDTO implements IAppointmentDTO {
	
	@JsonCreator
	public AppointmentDTO(@JsonProperty("requestId") int requestId, 
			@JsonProperty("range") IDateRange range,
			@JsonProperty("requestType") RequestType requestType) {
		super(requestId, range, requestType);
	}

	/**
	 * Constructor for defensive copying
	 * @param appDTO
	 */
	public AppointmentDTO(IAppointmentDTO appDTO) {
		super(appDTO.getSequence(), appDTO.getDateRange(), appDTO.getRequestType());
		this.setAttendee(appDTO.getAttendee());
		this.setOrganizer(appDTO.getOrganizer());
		this.setEventId(appDTO.getEventId());
		this.setRegistered(appDTO.isRegistered());
		this.setResponded(appDTO.isResponded());
		this.setComplete(appDTO.isComplete());
	}
	
}
