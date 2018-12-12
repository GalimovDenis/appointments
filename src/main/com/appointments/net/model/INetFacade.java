package com.appointments.net.model;

import java.util.UUID;

import com.appointments.calendar.event.IEventBuilder;

/**
 * Future higher-level abstraction over what is now in AppointerUtil
 *
 */
public interface INetFacade {
	
	public boolean createAppointment(IEventBuilder event); // only if event contains an Organizer
	
	public boolean readAppointment(UUID uid, String Organizer);
	
	public boolean readAppointment(UUID uid); 

	public boolean readAppointment(IEventBuilder event); // only if event contains an Organizer
	
	public boolean updateAppointment(IEventBuilder event); // only if event contains an Organizer
	
	public boolean deleteAppointment(IEventBuilder event);  // only if event contains an Organizer
	
	public boolean deleteAppointment(UUID uid, String Organizer);
	
	public boolean deleteAppointment(UUID uid); 
	
	
}
