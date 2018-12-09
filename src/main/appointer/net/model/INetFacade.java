package appointer.net.model;

import java.util.UUID;

import appointer.calendar.event.IEvent;

/**
 * Future higher-level abstraction over what is now in AppointerUtil
 *
 */
public interface INetFacade {
	
	public boolean createAppointment(IEvent event); // only if event contains an Organizer
	
	public boolean readAppointment(UUID uid, String Organizer);
	
	public boolean readAppointment(UUID uid); 

	public boolean readAppointment(IEvent event); // only if event contains an Organizer
	
	public boolean updateAppointment(IEvent event); // only if event contains an Organizer
	
	public boolean deleteAppointment(IEvent event);  // only if event contains an Organizer
	
	public boolean deleteAppointment(UUID uid, String Organizer);
	
	public boolean deleteAppointment(UUID uid); 
	
	
}
