package se.markusmaga.lth.pt.helper.options;

import se.markusmaga.lth.pt.helper.menu.MenuLauncher;
import se.markusmaga.lth.pt.helper.IFunction;

public abstract class OptionsLauncher extends MenuLauncher {
	private OptionsHandler oh;

	public OptionsLauncher() {
		super();
		
		oh = new OptionsHandler();
		
		setupOptions();
		
		if(oh.hasOptions()) {
			super.addMenuAction("Ändra Inställningar", new IFunction() {
				public void execute() {
					changeIntOptions();
				}
			});
		}
	}
	
	private void changeIntOptions() {
		oh.changeIntOptions();
	}
	
	protected void addOption(String flag, String desc, int def) {
		Option<Integer> o = new Option<Integer>(flag, desc, def);
		oh.add(o);
	}
	
	protected int getIntValue(String flag) {
		return oh.getIntValue(flag);
	}
		
	/**
	 * Sets up the menu alternatives and their callbacks.
	 */
	protected abstract void setupOptions();
}