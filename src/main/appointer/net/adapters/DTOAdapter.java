package appointer.net.adapters;

import java.util.UUID;

import appointer.calendar.facades.EventFacade;
import appointer.net.dto.AppointmentCreate;
import appointer.util.date.DateAdapter;
import biweekly.component.VEvent;
import biweekly.property.Uid;

public class DTOAdapter {

	public static AppointmentCreate toAppointmentCreation(VEvent event) {

		AppointmentCreate appEvent = new AppointmentCreate();

		appEvent.setOrganizer(event.getOrganizer().getCommonName());

		appEvent.setAttendee(event.getAttendees().get(0).getCommonName());

		appEvent.setStart(DateAdapter.asLocalDateTime(event.getDateStart().getValue()));

		appEvent.setEventId(event.getUid().getValue());
		
		// appEvent.setEnd(DateAdapter.asLocalDateTime(event.getDateEnd().getValue()));
		// TODO: NPE ^
		UUID uid = UUID.randomUUID();
		
		appEvent.setRequestId(uid);

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