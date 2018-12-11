package appointer.calendar.event;

import java.time.LocalDateTime;
import java.util.UUID;

import biweekly.util.Recurrence;

public class TimeRangeEvent implements ITimeRangeEvent {

	private final LocalDateTime timestamp;
	private final UUID uid; 
	private final LocalDateTime start;
	private final LocalDateTime end;
	private final Recurrence recur; 
	
	public TimeRangeEvent(LocalDateTime timestamp, UUID uid, LocalDateTime start, LocalDateTime end, Recurrence recur) {
		this.timestamp = timestamp;
		this.uid = uid;
		this.start = start;
		this.end = end;
		this.recur = recur;
	}

	@Override
	public LocalDateTime getDateTimeStamp() {
		return timestamp;
	}

	@Override
	public UUID getUid() {
		return uid;
	}

	@Override
	public LocalDateTime getDateTimeStart() {
		return start;
	}

	@Override
	public LocalDateTime getDateTimeEnd() {
		return end;
	}

	@Override
	public Recurrence getEventRepeats() {
		return recur;
	}

	@Override
	public String getOwner() {
		throw new UnsupportedOperationException("Not implemented");
	}

}
