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
					changeOptions();
				}
			});
		}
	}
	
	private void changeOptions() {
		oh.changeOptions();
	}
	
	protected <T> void addOption(String flag, String desc, T def) {
		Option<T> o = new Option<T>(flag, desc, def);
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