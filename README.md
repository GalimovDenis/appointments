**Appointment client**

Uses biWeekly library for ical format compatibility;  
Carries a map of calendars to users; supports commands for event manipulation; talks to the appointment server.

**API:**

  src/main
  
com.appointments.calendar.repository.ICalendarsRepository holds all calendars;

com.appointments.calendar.calendars.ICalendars controls access to the calendar;

com.appointments.calendar.event.IEventBuilder is an appointment builder from event;

com.appointments.calendar.event.IAppointmentEvent is an immutable appointment;

com.appointments.net.adapters.DTOAdapter converts IAppointmentEvent into IAppointmentDTO and back  *Under construction:*

  src/test
  
com.appointments.util.TestUtil is the entry point into client-server operations *Under construction:*
com.appointments.integration holds tests that run the everything above.  *Under construction:*


**Examples:**

Creating a calendar:

  `Calendars.create(String userName);`
  
Creating an event:

  `IEventBuilder.create()`

Changing an existing appointment:

   `IEventBuilder builder = IEventBuilder.produce(IAppointmentEvent appEvent);
    
   builder.setAnything(....)
   
   IAppointmentEvent appEventChanged = builder.buildAppointment();`
    
Creating IAppointmentDTO from IAppointmentEvent

   `DTOAdapter.toAppointmentDTO(RequestType type, IAppointmentEvent event)`
   
Creating IAppointmentEvent from IAppointmentDTO

   `DTOAdapter.IAppointmentEvent(IAppointmentDTO appDTO)`

Sending register request (attendee): *Under construction:*

   `TestUtil.registerEvent(IAppointmentEvent appEvent)`

Responding to request (organizer): *Under construction:*

   `TestUtil.respondEvent(ICalendars organizerCalendar)`

Reporting processed request (organizer): *Under construction:*

   `TestUtil.reportEvent(IAppointmentDTO appAnswerOrganizer`

Reading request changes (attendee): *Under construction:*

   `TestUtil.resultsEvent(IAppointmentEvent attendeeEvent, ICalendars AttendeeCalendars`

Marking request as complete (attendee): *Under construction:*

   `TestUtil.completeEvent(IAppointmentDTO completeEvent`

Creating an appointment
   
   ```IEventBuilder builder = IEventBuilder.create();
   
    builder.setSequence(int sequence);	// the revision number of an appointment
		
    builder.setEventTimestamp(LocalDateTime timestamp);  // last revision timestamp 
		builder.setEventID(UUID uid); // the unique ID of an appointment
		
		builder.setTimeStart(LocalDateTime timestamp);
		builder.setTimeEnd(LocalDateTime timestamp);
		
		builder.setAttendee(String attendeeName);
		builder.setOrganizer(String attendeeName);
		
    builder.setEventRepeats(biweekly.util.Frequency frequency); // repeat rules;
    
    IAppointmentEvent appEvent = builder.buildAppointment(); // <- this is the final immutable appointment```
