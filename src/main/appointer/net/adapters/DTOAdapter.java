package appointer.net.adapters;

import java.time.LocalDateTime;
import java.util.UUID;

import appointer.calendar.facades.ControlledEvent;
import appointer.calendar.facades.IEvent;
import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.BaseAppointmentDTO;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.checks.ArgumentsChecker;
import appointer.util.date.range.IDateRange;

public class DTOAdapter {

	/**
	 * Setting mutable content onto event
	 * 
	 * @param appDTO
	 * @param event
	 * @return
	 */
	private static <T extends BaseAppointmentDTO> T updateDTOFromEvent(T appDTO, IEvent event) {

		appDTO.setOrganizer(event.getOrganizer());

		appDTO.setAttendee(event.getAttendee());

		appDTO.setEventId(event.getUid().toString());

		appDTO.setTimestamp(event.getDateTimeStamp());
		
		return appDTO;
	}


	/**
	 * doing AppointmentDTO
	 * 
	 * @param event
	 * @return
	 */
	public static AppointmentDTO toAppointmentDTO(RequestType type, IEvent event) {

		ArgumentsChecker.checkNotNull(type, "RequestType");

		ArgumentsChecker.checkNotNull(event, "VEvent");

		final IDateRange range = event.createDateRange();

		final AppointmentDTO appDTO = DTOFactory.createAppointmentDTO(range, type);

		updateDTOFromEvent(appDTO, event);

		return appDTO;

	}

	/**
	 * Converting appointmentDTO into IEvent
	 * 
	 * @param appCreation
	 * @return
	 */
	public static IEvent toAppointmentEvent(IAppointmentDTO appCreation) {

		final ControlledEvent controlledEvent = new ControlledEvent();

		controlledEvent.setOrganizer(appCreation.getOrganizer());

		controlledEvent.setAttendee(appCreation.getAttendee());

		controlledEvent.setTimeStart(appCreation.getDateRange().getStart());

		controlledEvent.setTimeEnd(appCreation.getDateRange().getEnd());

		controlledEvent.setEventID(UUID.fromString(appCreation.getEventId()));

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
	public static IAppointmentDTO updateEvent(IEvent eventToChange, IAppointmentDTO appDTO) {

		eventToChange.setOrganizer(appDTO.getOrganizer());

		eventToChange.setAttendee(appDTO.getAttendee());

		eventToChange.setTimeStart(appDTO.getDateRange().getStart());

		eventToChange.setTimeEnd(appDTO.getDateRange().getEnd());

		LocalDateTime timestamp = LocalDateTime.now();
		
		eventToChange.setEventTimestamp(timestamp);
		
		appDTO.setTimestamp(timestamp);
		
		return appDTO;
	}

}