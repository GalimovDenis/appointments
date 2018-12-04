package appointer.util.date.range;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Immutable Date Range, can be  empty or populated, null-safe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({ @JsonSubTypes.Type(value = DateRangeEmpty.class, name = "DateRangeEmpty"),
		@JsonSubTypes.Type(value = DateRange.class, name = "DateRange") })
public interface IDateRange {
	public boolean isEmpty();

	public LocalDateTime getStart();

	public LocalDateTime getEnd();

	public String toString();


	@JsonCreator
	public static IDateRange empty() {
		return new DateRangeEmpty();
	}
}
