package appointer.commands;

/**
 * Sentinel pattern: created to get rid out of nulls;
 */
public abstract class CmdSpecialLeaf implements IAppCommand {

	@Override
	public void add(IAppCommand appCommand) {
	}

	@Override
	public void remove(IAppCommand appCommand) {
	}

	@Override
	public IAppCommand getChild(int i) {
		return new CmdSpecialEmpty();
	}

}
