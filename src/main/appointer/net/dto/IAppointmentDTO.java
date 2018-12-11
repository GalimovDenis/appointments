package appointer.net.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import appointer.util.date.range.IDateRange;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = AppointmentDTO.class, name = "AppointmentDTO")})
public interface IAppointmentDTO {
	
	public RequestType getRequestType();
	
	//every mutation in the calendar yields an auto-increment
	public Integer getSequence();
	
	public String getEventId();

	public void setEventId(String eventId);

	public LocalDateTime getTimestamp();

	public void setTimestamp(LocalDateTime timestamp);

	public String getOrganizer();

	public void setOrganizer(String organizer);

	public String getAttendee();

	public void setAttendee(String attendee);

	public IDateRange getDateRange();
		
	public boolean isRegistered();
	
	public boolean isResponded();
	
	public boolean isComplete();
	
	public void setRegistered(boolean registered);
	
	public void setResponded(boolean responded);
	
	public void setComplete(boolean complete);
}
