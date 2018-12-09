package appointer.net.adapters;

import java.time.LocalDateTime;

import appointer.calendar.facades.ControlledEvent;
import appointer.calendar.facades.EventFacade;
import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.BaseAppointmentDTO;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.checks.ArgumentsChecker;
import appointer.util.date.DateAdapter;
import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.component.VEvent;

public class DTOAdapter {

	/**
	 * Setting mutable content onto event
	 * 
	 * @param appDTO
	 * @param event
	 * @return
	 */
	private static <T extends BaseAppointmentDTO> T updateDTOFromEvent(AppointmentDTO appDTO, EventFacade event) {

		appDTO.setOrganizer(event.getOrganizer());

		appDTO.setAttendee(event.getAttendee());

		appDTO.setEventId(event.getUid());

		appDTO.setTimestamp(event.getDateTimeStamp());
		
		return appDTO;
	}

	/**
	 * extracting IDateRange range out of VEvent
	 * 
	 * @param event
	 * @return
	 */
	private static IDateRange createDateRange(VEvent event) { // consider refactoring elsewhere

		final LocalDateTime start = DateAdapter.asLocalDateTime(event.getDateStart().getValue());
		final LocalDateTime end = DateAdapter.asLocalDateTime(event.getDateEnd().getValue());
		return new DateRange(start, end);

	}

	// should accept controlled event
	/**
	 * doing AppointmentDTO
	 * 
	 * @param event
	 * @return
	 */
	public static AppointmentDTO toAppointmentDTO(RequestType type, EventFacade event) {

		ArgumentsChecker.checkNotNull(type, "RequestType");

		ArgumentsChecker.checkNotNull(event, "VEvent");

		final IDateRange range = event.createDateRange();

		final AppointmentDTO appDTO = DTOFactory.createAppointmentDTO(range, type);

		updateDTOFromEvent(appDTO, event);

		return appDTO;

	}

	// should be controlledevent
	/**
	 * Converting appointmentDTO into VEvent
	 * 
	 * @param appCreation
	 * @return
	 */
	public static EventFacade toAppointmentEvent(IAppointmentDTO appCreation) {

		final ControlledEvent controlledEvent = new ControlledEvent();

		controlledEvent.setOrganiser(appCreation.getOrganizer());

		controlledEvent.setAttendee(appCreation.getAttendee());

		controlledEvent.setEventStart(appCreation.getDateRange().getStart());

		controlledEvent.setEventEnd(appCreation.getDateRange().getEnd());

		controlledEvent.setEventID(appCreation.getEventId());

		controlledEvent.setEventTimestamp(appCreation.getTimestamp());
		
		return controlledEvent;
	}

	// should accept controlled event
	/**
	 * updating an event based on the DTO;
	 * returns DTO with the timestamp of Event modifiacation; 
	 * @param eventToChange
	 * @param appDTO.se
	 */
	public static IAppointmentDTO updateEvent(VEvent eventToChange, IAppointmentDTO appDTO) {

		EventFacade.setOrganiser(eventToChange, appDTO.getOrganizer());

		eventToChange.getAttendees().clear(); // refactor to EventFacade please - rep exposure

		EventFacade.addAttendee(eventToChange, appDTO.getAttendee());

		EventFacade.setEventStart(eventToChange, appDTO.getDateRange().getStart());

		EventFacade.setEventEnd(eventToChange, appDTO.getDateRange().getEnd());

		LocalDateTime timestamp = LocalDateTime.now();
		
		EventFacade.setEventTimestamp(eventToChange, timestamp);
		
		appDTO.setTimestamp(timestamp);
		
		return appDTO;
	}

}