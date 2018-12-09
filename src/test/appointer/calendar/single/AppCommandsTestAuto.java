package appointer.calendar.single;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import appointer.calendar.allcalendars.Calendars;
import appointer.calendar.allcalendars.ICalendars;
import appointer.calendar.facades.IEvent;
import appointer.commands.AppCommand;
import appointer.commands.CmdAddEvent;
import appointer.commands.CmdRemoveEvent;
import appointer.user.SingletonAppUser;

public class AppCommandsTestAuto {

	private static final String ALYSSA_P_HACKER = "Alyssa P. Hacker";

	private static Random gen = new Random();

	private final static int STARTINGEVENTS = 10;

	@Test
	public void addRemoveExecRedo() {

		SingletonAppUser.lazyGet(ALYSSA_P_HACKER);

		ICalendars appCalendar = new Calendars(ALYSSA_P_HACKER);

		addNEvents(appCalendar, STARTINGEVENTS);

		final int eventsInit = appCalendar.getUids().size();

		IEvent event = IEvent.create();

		List<AppCommand> commands = createTestCommandAddRemove(appCalendar, event);

		commands.stream().forEach(AppCommand::execute);

		Collections.reverse(commands);

		commands.stream().forEach(AppCommand::undo);

		final Set<UUID> eventsEnd = appCalendar.getUids();

//		System.out.println("Rolled number of commands: " + commands.size());
//
//		System.out.println("Events in collection: " + eventsEnd.size() + " Original events " + eventsInit);
//
//		commands.stream().forEach(c -> System.out.println(c.getClass()));

		assertTrue(eventsEnd.size() == eventsInit);
	}

	@Test
	public void randomExecRedo() {

		SingletonAppUser.lazyGet(ALYSSA_P_HACKER);

		ICalendars appCalendar = new Calendars(ALYSSA_P_HACKER);

		addNEvents(appCalendar, STARTINGEVENTS);

		final int eventsInit = appCalendar.getUids().size();
		// need an immutable event + event list in order to compare pre/post event
		// states;

		IEvent event = IEvent.create();

		List<AppCommand> commands = createTestCommandList(appCalendar, event);

		int size = commands.size();

		int testMaxCommands = gen.nextInt(size + 1); // gen.nexint upper is excusive;

		Collections.shuffle(commands);

		List<AppCommand> commandsTrimmed = commands.stream().limit(testMaxCommands)
				.collect(Collectors.toCollection(ArrayList::new));

		commandsTrimmed.stream().forEach(AppCommand::execute);

		Collections.reverse(commandsTrimmed);
		// Command stack is ADT by itself; we must preserve order of the commands
		// when doint execute() and redo();

		commandsTrimmed.stream().forEach(AppCommand::undo);

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
	 * Creates a list of commands for unit testing AppCommand;
	 * 
	 * @param appCalendar
	 * @param event
	 * @return
	 */
	private List<AppCommand> createTestCommandList(ICalendars appCalendar, IEvent event) {
		List<AppCommand> appCommands = new ArrayList<>();

		appCommands.add(new CmdAddEvent(appCalendar, event));

		appCommands.add(new CmdRemoveEvent(appCalendar, event));
//
//		appCommands.add(new CmdSetEventDuration(event, Duration.builder().hours(1).build()));
//
//		appCommands.add(new CmdSetEventDuration(event, Duration.builder().hours(1).build()));
//
//		appCommands.add(new CmdSetEventRepeats(event, Frequency.DAILY));
//
//		appCommands.add(new CmdSetEventRepeats(event, Frequency.DAILY));
//
//		appCommands.add(new CmdSetEventStart(event, LocalDate.now()));
//
//		appCommands.add(new CmdSetEventStart(event, LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))));

		return appCommands;
	}

	/**
	 * Creates a list of add/remove for unit testing AppCommand;
	 * 
	 * @param appCalendar
	 * @param event
	 * @return
	 */
	private List<AppCommand> createTestCommandAddRemove(ICalendars appCalendar, IEvent event) {

		List<AppCommand> appCommands = new ArrayList<>();

		appCommands.add(new CmdAddEvent(appCalendar, event));

		appCommands.add(new CmdRemoveEvent(appCalendar, event));

		return appCommands;
	}

}
