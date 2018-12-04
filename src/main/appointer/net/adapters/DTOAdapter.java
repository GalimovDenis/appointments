package appointer.net.adapters;

import java.time.LocalDateTime;

import appointer.calendar.facades.EventFacade;
import appointer.net.dto.AppointmentDTO;
import appointer.net.dto.BaseAppointmentDTO;
import appointer.net.dto.IAppointmentDTO;
import appointer.net.dto.RequestType;
import appointer.util.date.DateAdapter;
import appointer.util.date.range.DateRange;
import appointer.util.date.range.IDateRange;
import biweekly.component.VEvent;

public class DTOAdapter {
	
	/**
	 * Setting mutable content onto event
	 * @param appDTO
	 * @param event
	 * @return
	 */
	private static <T extends BaseAppointmentDTO> T fillEvent(T appDTO, VEvent event) {

		appDTO.setOrganizer(event.getOrganizer().getCommonName());

		appDTO.setAttendee(event.getAttendees().get(0).getCommonName());

		appDTO.setEventId(event.getUid().getValue());
		
		return appDTO;
	}
	
	/**
	 * extracting IDateRange range out of VEvent
	 * @param event
	 * @return
	 */
	private static IDateRange createDateRange(VEvent event) { // consider refactoring elsewhere
	
		LocalDateTime start = DateAdapter.asLocalDateTime(event.getDateStart().getValue());
		LocalDateTime end = DateAdapter.asLocalDateTime(event.getDateEnd().getValue());
		return new DateRange(start, end);
		
	}
	
	
	/**
	 * doing AppointmentDTO for CREATE 
	 * @param event
	 * @return
	 */
	public static AppointmentDTO toAppointmentCreation(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.CREATE);

		fillEvent(appEvent, event);
		
		return appEvent;

	}
	
	/**
	 * doing AppointmentDTO for UPDATE 
	 * @param event
	 * @return
	 */
	public static AppointmentDTO toAppointmentUpdate(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.UPDATE);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	/**
	 * doing AppointmentDTO for READ 
	 * @param event
	 * @return
	 */
	public static AppointmentDTO toAppointmentRead(VEvent event) {

		IDateRange range = createDateRange(event);
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.READ);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	/**
	 * doing AppointmentDTO for DELETE 
	 * @param event
	 * @return
	 */
	public static AppointmentDTO toAppointmentDelete(VEvent event) {

		IDateRange range = IDateRange.empty();
		
		AppointmentDTO appEvent = DTOFactory.createAppointmentDTO(range, RequestType.DELETE);

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	
	/**
	 * Converting appointmentDTO into VEvent
	 * @param appCreation
	 * @return
	 */
	public static VEvent toAppointmentEvent(IAppointmentDTO appCreation) {

		VEvent event = new VEvent();

		EventFacade.setOrganiser(event, appCreation.getOrganizer());

		EventFacade.addAttendee(event, appCreation.getAttendee());

		EventFacade.setEventStart(event, appCreation.getDateRange().getStart());
		
		EventFacade.setEventEnd(event, appCreation.getDateRange().getEnd());

		EventFacade.setEventID(event, appCreation.getEventId());

		return event;
	}

	/**
	 * updating an event based on the DTO;
	 * @param eventToChange
	 * @param appAnswerOrganizer
	 */
	public static void updateEvent(VEvent eventToChange, IAppointmentDTO appAnswerOrganizer) {
		
		EventFacade.setOrganiser(eventToChange, appAnswerOrganizer.getOrganizer());

		eventToChange.getAttendees().clear(); // refactor to EventFacade please - rep exposure
		
		EventFacade.addAttendee(eventToChange, appAnswerOrganizer.getAttendee());

		EventFacade.setEventStart(eventToChange, appAnswerOrganizer.getDateRange().getStart());

		EventFacade.setEventEnd(eventToChange, appAnswerOrganizer.getDateRange().getEnd());
		
	}





}