package appointer.net.model;

import java.util.UUID;

import appointer.calendar.event.IBuilderEvent;

/**
 * Future higher-level abstraction over what is now in AppointerUtil
 *
 */
public interface INetFacade {
	
	public boolean createAppointment(IBuilderEvent event); // only if event contains an Organizer
	
	public boolean readAppointment(UUID uid, String Organizer);
	
	public boolean readAppointment(UUID uid); 

	public boolean readAppointment(IBuilderEvent event); // only if event contains an Organizer
	
	public boolean updateAppointment(IBuilderEvent event); // only if event contains an Organizer
	
	public boolean deleteAppointment(IBuilderEvent event);  // only if event contains an Organizer
	
	public boolean deleteAppointment(UUID uid, String Organizer);
	
	public boolean deleteAppointment(UUID uid); 
	
	
}
