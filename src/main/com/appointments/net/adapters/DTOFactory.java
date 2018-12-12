package com.appointments.net.adapters;

import com.appointments.net.dto.AppointmentDTO;
import com.appointments.net.dto.RequestType;
import com.appointments.util.date.range.IDateRange;

public class DTOFactory {
		
	public static  AppointmentDTO createAppointmentDTO(Integer id, IDateRange range, RequestType requestType) {
		return new AppointmentDTO(id, range, requestType);
	}


}
