package appointer.calendar.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.util.Frequency;
import biweekly.util.Recurrence;
import lombok.EqualsAndHashCode;

/**
 * Homebrew event that doesn't carry VEvent inside; alternative IEvent implementation
 */
public final class HomebrewEvent implements IBuilderEvent {

	
	
	private LocalDateTime timestamp;
	private UUID uid; 
	private LocalDateTime start;
	private LocalDateTime end;
	private String organizer;
	private String attendee;
	private Recurrence recur; 
	private AppointmentStatus status;
	

	public HomebrewEvent() {		
	}
	
//	public HomebrewEvent(VEvent event) {
//		//TODO: stub;		
//	}
	
	@Override
	public void setEventID(UUID uid) {
		this.uid = uid;
	}

	@Override
	public void setEventTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public void setTimeStart(LocalDateTime fillTime) {
		this.start = fillTime; 
	}

	@Override
	public void setTimeEnd(LocalDateTime endTime) {
		this.end = endTime; 
	}

	@Override
	public void adjustEventTime(TemporalAmount offset) {
		start =  start.plus(offset);
		end = end.plus(offset);
	}

	@Override
	public void setEventRepeats(Frequency frequency) {
		recur = new Recurrence.Builder(frequency).build();
	}

	@Override
	public void setOrganizer(String organiserName) {
		this.organizer = organiserName;
	}

	@Override
	public void setAttendee(String attendeeName) {
		
		this.attendee = attendeeName;
		
	}
	
	@Override
	public LocalDateTime getDateTimeStart() {
		return start; //immutable
	}

	@Override
	public LocalDateTime getDateTimeEnd() {
		return end; //immutable
	}

	@Override
	public String getOrganizer() {
		return organizer; //immutable
	}

	@Override
	public String getAttendee() {
		return attendee; //immutable
	}

	@Override
	public UUID getUid() {
		return uid; //immutable
	}

	@Override
	public LocalDateTime getDateTimeStamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "HomebrewEvent [uid=" + uid + ", timestamp=" + timestamp + ", start=" + start + ", end=" + end
				+ ", organizer=" + organizer + ", attendee=" + attendee + ", recur=" + recur + ", status=" + status
				+ "]";
	}

	@Override
	public IAppointmentEvent buildAppointment() {
		
		if (!isAppointment()) throw new IllegalStateException("Event not an appointment");
		
		return new ControlledAppointmentEvent(
				getDateTimeStamp(),
				getUid(),
				getDateTimeStart(),
				getDateTimeEnd(),
				
				getOrganizer(),
				getAttendee(),
				getEventRepeats()
				);			
	}

	@Override
	public Recurrence getEventRepeats() {
		return recur;
	}

	@Override
	public ITimeRangeEvent buildTimeRange() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendee == null) ? 0 : attendee.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((organizer == null) ? 0 : organizer.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HomebrewEvent other = (HomebrewEvent) obj;
		if (attendee == null) {
			if (other.attendee != null)
				return false;
		} else if (!attendee.equals(other.attendee))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (organizer == null) {
			if (other.organizer != null)
				return false;
		} else if (!organizer.equals(other.organizer))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}
