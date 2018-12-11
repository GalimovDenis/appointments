package appointer.calendar.event;

import java.time.LocalDateTime;
import java.util.UUID;

import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.util.Recurrence;

public interface ITimeRangeEvent {

	/**
	 * Should contain the last mutation time.
	 * @return
	 */
	public LocalDateTime getDateTimeStamp();
	
	/**
	 * Returns whoever created this event;
	 */
	public String getOwner();
	/**
	 * Unique ID for an event.
	 * @return
	 */
	public UUID getUid();
	
	public LocalDateTime getDateTimeStart();

	public LocalDateTime getDateTimeEnd();
	
	public default IDateRange getDateRange() {
		return new DateRange(getDateTimeStart(), getDateTimeEnd());
	};  

	public Recurrence getEventRepeats();
}
