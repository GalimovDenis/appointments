package appointer.util.date.range;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DateRangeEmpty implements IDateRange {

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public LocalDateTime getStart() {
		throw new UnsupportedOperationException();
	}

	@Override
	public LocalDateTime getEnd() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String toString() {
		return "DateRangeEmpty";
	}

}
