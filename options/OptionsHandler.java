package se.markusmaga.lth.pt.helper.options;

import java.util.*;
import se.lth.cs.pt.io.Keyboard;

public class OptionsHandler implements IOptionsHandler {
	List<Option<Integer>> options;
	
	public OptionsHandler() {
		options = new ArrayList<Option<Integer>>();
	}
	
	public boolean add(Option<Integer> o) {
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
		for(Option<Integer> o : options) {
			int value = Keyboard.nextInt(o.getDesc() +": ");
			o.setValue(value);
		}
	}
	
	public boolean hasOptions() {
		return !options.isEmpty();
	}
}
