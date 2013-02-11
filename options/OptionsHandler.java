package se.markusmaga.lth.pt.helper.options;

import java.util.*;
import se.lth.cs.pt.io.Keyboard;

public class OptionsHandler implements IOptionsHandler {
	List<Option> options;
	
	public OptionsHandler() {
		options = new ArrayList<Option>();
	}
	
	public boolean add(Option o) {
		return options.add(o);
	}
	
	public int getIntValue(String flag) {
		for(Option o : options) {
			if(o.getKey().equals(flag))
				return (int)o.getValue();
		}
		
		return 0;
	}
	
	public void changeIntOptions() {
		for(Option<int> o : options) {
			int value = Keyboard.nextInt(o.getDesc() +": ");
			o.setValue(value);
		}
	}
	
	public boolean hasOptions() {
		return !options.isEmpty();
	}
}