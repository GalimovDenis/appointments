package appointer.net.adapters;

import appointer.calendar.facades.EventFacade;
import appointer.net.dto.AppointmentCreate;
import appointer.net.dto.AppointmentDelete;
import appointer.net.dto.AppointmentRead;
import appointer.net.dto.AppointmentUpdate;
import appointer.net.dto.BaseAppointmentDTO;
import appointer.net.dto.TimedAppointmentDTO;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;
import biweekly.property.Uid;

public class DTOAdapter {
	
	private static <T extends BaseAppointmentDTO> T fillEvent(T appDTO, VEvent event) {

		appDTO.setOrganizer(event.getOrganizer().getCommonName());

		appDTO.setAttendee(event.getAttendees().get(0).getCommonName());

		appDTO.setEventId(event.getUid().getValue());
		
		return appDTO;
	}
	
	private static <T extends TimedAppointmentDTO> T fillTimedEvent(T appDTO, VEvent event) {

		fillEvent(appDTO, event);
		
		appDTO.setStart(DateAdapter.asLocalDateTime(event.getDateStart().getValue()));
		
		return appDTO;
	}
	
	
	public static AppointmentCreate toAppointmentCreation(VEvent event) {

		AppointmentCreate appEvent = DTOFactory.appointmentCreate();

		fillTimedEvent(appEvent, event);

		return appEvent;

	}
	
	public static AppointmentUpdate toAppointmentUpdate(VEvent event) {

		AppointmentUpdate appEvent = DTOFactory.appointmentUpdate();

		fillTimedEvent(appEvent, event);

		return appEvent;

	}
	
	public static AppointmentRead toAppointmentRead(VEvent event) {

		AppointmentRead appEvent = DTOFactory.appointmentRead();

		fillTimedEvent(appEvent, event);

		return appEvent;

	}
	
	public static AppointmentDelete toAppointmentDelete(VEvent event) {

		AppointmentDelete appEvent = DTOFactory.appointmentDelete();

		fillEvent(appEvent, event);

		return appEvent;

	}
	
	

	public static VEvent toAppointmentEvent(AppointmentCreate appCreation) {

		VEvent event = new VEvent();

		EventFacade.setOrganiser(event, appCreation.getOrganizer());

		EventFacade.addAttendee(event, appCreation.getAttendee());

		EventFacade.setEventStart(event, appCreation.getStart());

		EventFacade.setEventID(event, new Uid(appCreation.getEventId()));

		return event;
	}





}