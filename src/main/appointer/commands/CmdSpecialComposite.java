package appointer.commands;

import java.util.ArrayList;
import java.util.List;

// next best thing is to make static factory for appCalendar and event;
// you ask CmdAddEvent.class and get new CmdAddEvent(this.appCalendar, this.event)
/**
 *  A list of commands that behaves as command;
 */
public class CmdSpecialComposite implements IAppCommand {

	protected List<IAppCommand> appCommands = new ArrayList<>();
 
	public CmdSpecialComposite() {
	}
	
	public void add(IAppCommand appCommand) {
		appCommands.add(appCommand);
	}

	public void remove(IAppCommand appCommand) {
		appCommands.remove(appCommand);
	}

	public IAppCommand getChild(int i) {
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
