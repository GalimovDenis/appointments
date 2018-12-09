package appointer.commands;

import java.util.ArrayList;
import java.util.List;

// next best thing is to make static factory for appCalendar and event;
// you ask CmdAddEvent.class and get new CmdAddEvent(this.appCalendar, this.event)
/**
 *  A list of commands that behaves as command;
 */
public class CmdComposite implements AppCommand {

	protected List<AppCommand> appCommands = new ArrayList<>();
 
	public CmdComposite() {
	}
	
	public void add(AppCommand appCommand) {
		appCommands.add(appCommand);
	}

	public void remove(AppCommand appCommand) {
		appCommands.remove(appCommand);
	}

	public AppCommand getChild(int i) {
		return appCommands.get(i);
	}

	@Override
	public void execute() {
		for (int i = 0; i < appCommands.size(); i++) {
			appCommands.get(i).execute();
		}
	}

	@Override
	public void undo() {
		for (int i = appCommands.size() - 1; i >= 0; i--) {
			appCommands.get(i).undo();
		}
	}

}
