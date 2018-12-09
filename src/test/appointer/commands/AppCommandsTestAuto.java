package appointer.commands;

import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import appointer.calendar.calendars.ICalendars;
import appointer.calendar.event.IEvent;

public class AppCommandsTestAuto {

	private static final String ALYSSA_P_HACKER = "Alyssa P. Hacker";

	private static Random gen = new Random();

	private final static int STARTINGEVENTS = 10;

	@Test
	public void addRemoveExecRedo() {

		ICalendars appCalendar = ICalendars.create(ALYSSA_P_HACKER);

		addNEvents(appCalendar, STARTINGEVENTS);

		final int eventsInit = appCalendar.getUids().size();

		IEvent event = IEvent.create();

		List<IAppCommand> commands = createTestCommandAddRemove(appCalendar, event);

		commands.stream().forEach(IAppCommand::execute);

		final Set<UUID> eventsEnd = appCalendar.getUids();

		Collections.reverse(commands);

		commands.stream().forEach(IAppCommand::undo);

//		commands.stream().forEach(c -> System.out.println(c.getClass()));

		// System.out.println("Rolled number of commands: " + commands.size());
		//
		// System.out.println("Events in collection: " + eventsEnd.size() + " Original
		// events " + eventsInit);
		//
		// System.out.println(appCalendar);

		assertTrue(eventsEnd.size() == eventsInit);
	}

	@Test
	public void randomExecRedo() {

		ICalendars appCalendar = ICalendars.create(ALYSSA_P_HACKER);

		addNEvents(appCalendar, STARTINGEVENTS);

		final int eventsInit = appCalendar.getUids().size();

		IEvent event = IEvent.create();

		List<IAppCommand> commands = createTestCommandList(appCalendar, event);

		int size = commands.size();

		int testMaxCommands = gen.nextInt(size + 1); // gen.nexint upper is excusive;

		Collections.shuffle(commands);

		List<IAppCommand> commandsTrimmed = commands.stream().limit(testMaxCommands)
				.collect(Collectors.toCollection(ArrayList::new));

		commandsTrimmed.stream().forEach(IAppCommand::execute);

		Collections.reverse(commandsTrimmed);
		// Command stack is ADT by itself; we must preserve order of the commands
		// when doint execute() and redo();

		commandsTrimmed.stream().forEach(IAppCommand::undo);

		final Set<UUID> eventsEnd = appCalendar.getUids();

		assertTrue(eventsEnd.size() == eventsInit);
	}

	/**
	 * Adds n event to the calendar
	 * 
	 * @param appCalendar
	 * @param NEvents
	 */
	private void addNEvents(ICalendars appCalendar, int NEvents) {

		IEvent eventOne;

		for (int i = 0; i < NEvents; i++) {

			eventOne = IEvent.create();

			CmdAddEvent cae = new CmdAddEvent(appCalendar, eventOne);

			cae.execute();

		}

	}

	/**
	 * Creates a list of commands for unit testing IAppCommand;
	 * 
	 * @param appCalendar
	 * @param event
	 * @return
	 */
	private List<IAppCommand> createTestCommandList(ICalendars appCalendar, IEvent event) {
		List<IAppCommand> appCommands = new ArrayList<>();

		appCommands.add(new CmdAddEvent(appCalendar, event));

		appCommands.add(new CmdRemoveEvent(appCalendar, event));
		//
		// appCommands.add(new CmdSetEventDuration(event,
		// Duration.builder().hours(1).build()));
		//
		// appCommands.add(new CmdSetEventDuration(event,
		// Duration.builder().hours(1).build()));
		//
		// appCommands.add(new CmdSetEventRepeats(event, Frequency.DAILY));
		//
		// appCommands.add(new CmdSetEventRepeats(event, Frequency.DAILY));

		appCommands.add(new CmdSetEventStart(event, LocalDateTime.now()));

		appCommands.add(new CmdSetEventStart(event, LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))));

		return appCommands;
	}

	/**
	 * Creates a list of add/remove for unit testing IAppCommand;
	 * 
	 * @param appCalendar
	 * @param event
	 * @return
	 */
	private List<IAppCommand> createTestCommandAddRemove(ICalendars appCalendar, IEvent event) {

		List<IAppCommand> appCommands = new ArrayList<>();

		appCommands.add(new CmdAddEvent(appCalendar, event));

		appCommands.add(new CmdRemoveEvent(appCalendar, event));

		return appCommands;
	}

}
