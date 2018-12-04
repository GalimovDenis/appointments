package appointer.net.dto;

import java.util.UUID;

import appointer.util.date.range.IDateRange;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * dto for appointment creation; appointment is event with Organizer and Attendee; 
 *
 */
public class AppointmentDTO extends TimedAppointmentDTO implements IAppointmentDTO {
	
	@JsonCreator
	public AppointmentDTO(@JsonProperty("requestId") UUID requestId, 
			@JsonProperty("range") IDateRange range,
			@JsonProperty("requestType") RequestType requestType) {
		super(requestId, range, requestType);
	}

	/**
	 * Constructor for defensive copying
	 * @param appDTO
	 */
	public AppointmentDTO(IAppointmentDTO appDTO) {
		super(appDTO.getRequestId(), appDTO.getDateRange(), appDTO.getRequestType());
		this.setAttendee(appDTO.getAttendee());
		this.setOrganizer(appDTO.getOrganizer());
		this.setEventId(appDTO.getEventId());
		this.setRegistered(appDTO.isRegistered());
		this.setResponded(appDTO.isResponded());
		this.setComplete(appDTO.isComplete());
	}
	
}
